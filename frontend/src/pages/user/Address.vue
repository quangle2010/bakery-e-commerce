<script lang="ts" setup>
import { onMounted, ref, computed } from 'vue';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import axios from 'axios';
import { useAuthStore } from '../../store/auth';

interface Address {
  id: number;
  fullName: string;
  phone: string;
  street: string;
  provinceId: number;
  districtId: number;
  wardId: number;
  isDefault: boolean;
}

interface Province {
  PROVINCE_ID: number;
  PROVINCE_CODE: string;
  PROVINCE_NAME: string;
}

interface District {
  DISTRICT_ID: number;
  DISTRICT_NAME: string;
  PROVINCE_ID: number;
}

interface Ward {
  WARDS_ID: number;
  WARDS_NAME: string;
  DISTRICT_ID: number;
}

const addresses = ref<Address[]>([]);
const showDeleteModal = ref(false);
const showDefaultModal = ref(false);
const selectedItem = ref<Address | null>(null);
const auth=useAuthStore();
const provinces = ref<Province[]>([]);

// Use Maps to cache districts and wards per province/district id
const districtsCache = ref(new Map<number, District[]>());
const wardsCache = ref(new Map<number, Ward[]>());

// Fetch provinces once
const fetchProvinces = async () => {
  try {
    const response = await axios.get('/api/viettel/v2/categories/listProvinceById?provinceId=-1');
    if (response.data.status === 200 && response.data.data) {
      provinces.value = response.data.data;
    }
  } catch (error) {
    console.error('Error fetching provinces:', error);
  }
};

// Fetch districts by provinceId, cache results
const fetchDistricts = async (provinceId: number) => {
  if (districtsCache.value.has(provinceId)) return; // Already cached
  try {
    const response = await axios.get(`/api/viettel/v2/categories/listDistrict?provinceId=${provinceId}`);
    if (response.data.status === 200 && response.data.data) {
      districtsCache.value.set(provinceId, response.data.data);
    }
  } catch (error) {
    console.error(`Error fetching districts for provinceId ${provinceId}:`, error);
  }
};

// Fetch wards by districtId, cache results
const fetchWards = async (districtId: number) => {
  if (wardsCache.value.has(districtId)) return; // Already cached
  try {
    const response = await axios.get(`/api/viettel/v2/categories/listWards?districtId=${districtId}`);
    if (response.data.status === 200 && response.data.data) {
      wardsCache.value.set(districtId, response.data.data);
    }
  } catch (error) {
    console.error(`Error fetching wards for districtId ${districtId}:`, error);
  }
};

const fetchAddresses = async () => {
  try {
    const response = await axiosClient.get('/user/addresses');
    if (response.data.status === true && response.data.data) {
      addresses.value = response.data.data;
      
      // After addresses are loaded, prefetch districts and wards caches on demand
      const provinceIds = new Set(addresses.value.map(addr => addr.provinceId));
      for (const pid of provinceIds) {
        await fetchDistricts(pid);
      }
      const districtIds = new Set(addresses.value.map(addr => addr.districtId));
      for (const did of districtIds) {
        await fetchWards(did);
      }
    }
  } catch (error) {
    console.error('Error fetching addresses:', error);
  }
};

const confirmDefault = (item: Address) => {
  selectedItem.value = item;
  showDefaultModal.value = true;
};

// Delete address modal logic
const confirmDelete = (item: Address) => {
  selectedItem.value = item;
  showDeleteModal.value = true;
};


const defaultItem = async () => {
  if (!selectedItem.value) return;

  try {
    const response = await axiosClient.put(`/user/addresses/${selectedItem.value.id}/default`);
    if (response.data.status === true) {
      showSuccess(response.data.message);
      showDefaultModal.value = false;
      selectedItem.value = null;
      await fetchAddresses();
      await auth.fetchUserInfo();
    } else {
      showError(response.data.message);
    }
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('API error:', error.response?.data || error.message);
      showError(error.response?.data.message || 'Đã xảy ra lỗi khi đặt làm địa chỉ mặc định');
    } else {
      console.error('Unexpected error:', error);
      showError('Đã xảy ra lỗi không xác định khi đặt làm địa chỉ mặc định');
    }
  }
};

const deleteItem = async () => {
  if (!selectedItem.value) return;

  try {
    const response = await axiosClient.delete(`/user/addresses/${selectedItem.value.id}`);
    if (response.data.status === true) {
      showSuccess(response.data.message);
    
      selectedItem.value = null;
        showDeleteModal.value = false;
      await fetchAddresses();
    } else {
      showError(response.data.message);
    }
  } catch (error) {
    if (axios.isAxiosError(error)) {
      console.error('API error:', error.response?.data || error.message);
      showError(error.response?.data.message || 'Đã xảy ra lỗi khi xóa địa chỉ');
    } else {
      console.error('Unexpected error:', error);
      showError('Đã xảy ra lỗi không xác định khi xóa địa chỉ');
    }
  }
};

// Helper: get district by id from cache
function getDistrictById(districtId: number): District | undefined {
  for (const districts of districtsCache.value.values()) {
    const found = districts.find(d => d.DISTRICT_ID === districtId);
    if (found) return found;
  }
  return undefined;
}

// Helper: get ward by id from cache
function getWardById(wardId: number): Ward | undefined {
  for (const wards of wardsCache.value.values()) {
    const found = wards.find(w => w.WARDS_ID === wardId);
    if (found) return found;
  }
  return undefined;
}

// Construct full address string
function setAddressStreet(provinceId: number, districtId: number, wardId: number, street: string): string {
  const province = provinces.value.find(p => p.PROVINCE_ID === provinceId);
  const district = getDistrictById(districtId);
  const ward = getWardById(wardId);

  // Return fallback if missing
  if (!province && !district && !ward) {
    return street || '';
  }

  // Compose array of address parts, filter empty
  const parts = [street, ward?.WARDS_NAME, district?.DISTRICT_NAME, province?.PROVINCE_NAME].filter(Boolean);
  return parts.join(', ');
}

// Map of addressId -> full address string for efficient rendering
const addressFullMap = computed(() => {
  const map = new Map<number, string>();
  for (const addr of addresses.value) {
    map.set(addr.id, setAddressStreet(addr.provinceId, addr.districtId, addr.wardId, addr.street));
  }
  return map;
});
const getFullAddress = (id?: number): string => {
  if (!id) return 'Không xác định';
  return addressFullMap.value.get(id) || 'Không xác định';
};

onMounted(async () => {
  await fetchProvinces();
  await fetchAddresses();
});
</script>

<template>
  <div class="card mb-4 w-100 h-100">
    <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom">
      <div class="fw-bold">Danh sách địa chỉ</div>
      <router-link to="/user/add-address" class="btn btn-sm btn-outline-primary ms-auto" title="Thêm địa chỉ">
        <i class="bi bi-plus-lg me-1"></i>Thêm địa chỉ
      </router-link>
    </div>
    <div class="card-body">
      <div class="card card_item mb-4" v-for="address in addresses" :key="address.id">
        <div class="card-header d-flex align-items-center justify-content-between">
          <div class="fw-bold">
            {{ address.fullName }}
            <span class="badge bg-success" v-if="address.isDefault">
              <i class="bi bi-check-lg me-1"></i>Địa chỉ mặc định
            </span>
          </div>
          <div class="d-flex align-items-center gap-2">
            <router-link :to="`/user/edit-address/${address.id}`" class="btn btn-sm btn-outline-info" title="Chi tiết">
              <i class="bi bi-eye me-1"></i>Chi tiết
            </router-link>

            <button v-if="!address.isDefault" class="btn btn-sm btn-outline-danger rounded-circle" title="Xóa sản phẩm" @click="confirmDelete(address)">
              <i class="bi bi-trash"></i>
            </button>
            <button v-if="!address.isDefault" class="btn btn-sm btn-outline-primary rounded-circle" title="Đặt làm địa chỉ mặc định" @click="confirmDefault(address)">
              <i class="bi bi-check2"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
          <p class="mb-0"><strong>Điện thoại:</strong> {{ address.phone }}</p>
          <p class="mb-0"><strong>Địa chỉ:</strong> {{ addressFullMap.get(address.id) }}</p>
        </div>
      </div>
    </div>

    <div class="modal fade" :class="{ show: showDeleteModal }" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true" :style="{ display: showDeleteModal ? 'block' : 'none' }">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-sm">
          <div class="modal-header border-0 pb-0">
            <h5 class="modal-title fw-bold" id="deleteModalLabel">Xác nhận xóa sản phẩm</h5>
            <button type="button" class="btn-close" @click="showDeleteModal = false" aria-label="Close"></button>
          </div>
          <div class="modal-body text-center py-4">
            <div class="mb-3">
              <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
            </div>
            <p class="mb-0">Bạn có chắc chắn muốn xóa địa chỉ <strong>{{ getFullAddress(selectedItem?.id) }}</strong>?</p>
            <p class="text-muted small mt-2">Hành động này không thể hoàn tác.</p>
          </div>
          <div class="modal-footer border-0 justify-content-center pt-0">
            <button type="button" class="btn btn-danger rounded-pill px-4" @click="deleteItem">
              <i class="bi bi-trash me-1"></i> Xóa địa chỉ
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" :class="{ show: showDefaultModal }" id="defaultModal" tabindex="-1" aria-labelledby="defaultModalLabel" aria-hidden="true" :style="{ display: showDefaultModal ? 'block' : 'none' }">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content border-0 shadow-sm">
          <div class="modal-header border-0 pb-0">
            <h5 class="modal-title fw-bold" id="deleteModalLabel">Xác nhận đặt làm địa chỉ mặc định</h5>
            <button type="button" class="btn-close" @click="showDefaultModal = false" aria-label="Close"></button>
          </div>
          <div class="modal-body text-center py-4">
            <div class="mb-3">
              <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
            </div>
            <p class="mb-0">Bạn có chắc chắn muốn đặt địa chỉ <strong>{{ getFullAddress(selectedItem?.id) }}</strong> làm địa chỉ mặc định?</p>
            <p class="text-muted small mt-2">Hành động này không thể hoàn tác.</p>
          </div>
          <div class="modal-footer border-0 justify-content-center pt-0">
            <button type="button" class="btn btn-success rounded-pill px-4" @click="defaultItem">
              <i class="bi bi-check me-1"></i> Đặt làm địa chỉ mặc định
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Backdrop cho modal -->
    <div class="modal-backdrop fade" :class="{ show: showDeleteModal }" :style="{ display: showDeleteModal ? 'block' : 'none' }"></div>
    <div class="modal-backdrop fade" :class="{ show: showDefaultModal }" :style="{ display: showDefaultModal ? 'block' : 'none' }"></div>
  </div>
</template>

<style scoped>
.card_item {
  transition: all 0.3s ease;
  overflow: hidden;
}

.card_item:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
  cursor: pointer;
}
</style>
