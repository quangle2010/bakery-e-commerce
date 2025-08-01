<script lang="ts" setup>
import axios from 'axios';
import { onMounted, reactive, ref, watch } from 'vue';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import Loading from '../../components/common/Loading.vue';
import router from '../../router';
import { useAuthStore } from '../../store/auth';

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

const provinces = ref<Province[]>([]);
const districts = ref<District[]>([]);
const wards = ref<Ward[]>([]);

const selectedProvinceId = ref<number | null>(null);
const selectedDistrictId = ref<number | null>(null);
const selectedWardId = ref<number | null>(null);

const formSubmit = reactive({
  fullName: '',
  phone: '',
  street: '',
  selectedProvinceId: 0,
  selectedDistrictId: 0,
  selectedWardId: 0,
  isDefault: false
});

const errors = reactive({
  fullName: '',
  phone: '',
  street: '',
  selectedProvinceId: '',
  selectedDistrictId: '',
  selectedWardId: '',
  isDefault: '',
  general: ''
});

const isLoading = ref(false);

// Fetch province list
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

// Fetch district list
const fetchDistricts = async (provinceId: number) => {
  try {
    const response = await axios.get(`/api/viettel/v2/categories/listDistrict?provinceId=${provinceId}`);
    if (response.data.status === 200 && response.data.data) {
      districts.value = response.data.data;
    }
  } catch (error) {
    console.error('Error fetching districts:', error);
  }
};

// Fetch ward list
const fetchWards = async (districtId: number) => {
  try {
    const response = await axios.get(`/api/viettel/v2/categories/listWards?districtId=${districtId}`);
    if (response.data.status === 200 && response.data.data) {
      wards.value = response.data.data;
    }
  } catch (error) {
    console.error('Error fetching wards:', error);
  }
};

// Province selection handler
const onProvinceChange = () => {
  if (selectedProvinceId.value !== null) {
    fetchDistricts(selectedProvinceId.value);
    selectedDistrictId.value = null;
    wards.value = [];
    selectedWardId.value = null;
  }
};

// District selection handler
const onDistrictChange = () => {
  if (selectedDistrictId.value !== null) {
    fetchWards(selectedDistrictId.value);
    selectedWardId.value = null;
  }
};

const goback = () => {
  window.history.back();
};

onMounted(() => {
  fetchProvinces();
});

// --- Sync dropdowns with formSubmit ---
watch(selectedProvinceId, (newVal) => {
  formSubmit.selectedProvinceId = newVal ?? 0;
  errors.selectedProvinceId = '';
});

watch(selectedDistrictId, (newVal) => {
  formSubmit.selectedDistrictId = newVal ?? 0;
  errors.selectedDistrictId = '';
});

watch(selectedWardId, (newVal) => {
  formSubmit.selectedWardId = newVal ?? 0;
  errors.selectedWardId = '';
});

// --- Validation logic ---
const validateForm = () => {
  let isValid = true;
  errors.fullName = '';
  errors.phone = '';
  errors.street = '';
  errors.selectedProvinceId = '';
  errors.selectedDistrictId = '';
  errors.selectedWardId = '';
  errors.general = '';

  if (!formSubmit.fullName.trim()) {
    errors.fullName = "Vui lòng nhập Họ và tên";
    isValid = false;
  } else if (/\d/.test(formSubmit.fullName)) {
    errors.fullName = "Họ và tên không được chứa số";
    isValid = false;
  } else if (!/^[a-zA-ZÀ-ỹ\s]+$/u.test(formSubmit.fullName)) {
    errors.fullName = "Họ và tên không được chứa ký tự đặc biệt";
    isValid = false;
  }

  if (!formSubmit.phone || !formSubmit.phone.trim()) {
    errors.phone = 'Vui lòng nhập số điện thoại';
    isValid = false;
  } else if (!/^0\d{9}$/.test(formSubmit.phone)) {
    errors.phone = 'Số điện thoại phải gồm 10 số và bắt đầu bằng số 0';
    isValid = false;
  }

  if (!formSubmit.street.trim()) {
    errors.street = 'Vui lòng nhập địa chỉ';
    isValid = false;
  }
  if (!formSubmit.selectedProvinceId) {
    errors.selectedProvinceId = 'Vui lòng chọn Tỉnh/Thành phố';
    isValid = false;
  }
  if (!formSubmit.selectedDistrictId) {
    errors.selectedDistrictId = 'Vui lòng chọn Quận/Huyện';
    isValid = false;
  }
  if (!formSubmit.selectedWardId) {
    errors.selectedWardId = 'Vui lòng chọn Phường/Xã';
    isValid = false;
  }

  return isValid;
};
const auth = useAuthStore();
// --- Submit handler ---
const handleSubmit = async () => {
  if (!validateForm()) return;
  try {
    isLoading.value = true;
    const reqBody = {
      fullName: formSubmit.fullName,
      phone: formSubmit.phone,
      street: formSubmit.street,
      provinceId: formSubmit.selectedProvinceId,
      districtId: formSubmit.selectedDistrictId,
      wardId: formSubmit.selectedWardId,
      isDefault: formSubmit.isDefault
    };
    const { data } = await axiosClient.post("/user/addresses", reqBody);
    if (data.status === true) {
      showSuccess("Cập nhật thông tin", data.message);
      if (auth?.user?.addressId===null) {
        auth.updateAddressId(data.data);
      }
      router.back();

    } else {
      showError("Cập nhật thất bại");
    }
  } catch (error) {
    if (axios.isAxiosError(error)) {
      const errorMessage = error.response?.data?.message || "Lỗi không xác định";
      showError(errorMessage);
    }
  } finally {
    isLoading.value = false;
  }
};
</script>


<template>

  <div class="card mb-4 w-100 h-100">
    <div v-if="isLoading">
      <Loading />
    </div>
    <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom">
      <div class="fw-bold">Thêm địa chỉ</div>
      <button @click="goback" class="btn btn-sm btn-outline-primary ms-auto" title="Quay lại">
        <i class="bi bi-arrow-return-left me-1"></i>Quay lại
      </button>
    </div>
    <div class="card-body">
      <form @submit.prevent="handleSubmit">
        <div class="row">
          <div class="col-6">
            <div class="mb-3">
              <label for="name" class="form-label">Họ và tên</label>
              <input type="text" class="form-control" id="name" placeholder="Nhập họ và tên"
                v-model="formSubmit.fullName" />
              <small class="text-danger fw-bold ">
                {{ errors?.fullName }}
              </small>
            </div>
          </div>


          <div class="col-6">
            <div class="mb-3">
              <label for="phone" class="form-label">Số điện thoại</label>
              <input type="text" class="form-control" id="phone" placeholder="Nhập số điện thoại"
                v-model="formSubmit.phone" />
              <small class="text-danger fw-bold ">
                {{ errors?.phone }}
              </small>
            </div>

          </div>




          <div class="col-4">
            <div class="mb-3">
              <label for="city" class="form-label">Tỉnh/Thành phố</label>
              <select id="city" class="form-select" v-model="selectedProvinceId" @change="onProvinceChange">
                <option v-for="(item, index) in provinces" :key="index" :value="item.PROVINCE_ID">
                  {{ item.PROVINCE_NAME }}
                </option>
              </select>
              <small class="text-danger fw-bold ">
                {{ errors?.selectedProvinceId }}
              </small>
            </div>
          </div>
          <div class="col-4">
            <div class="mb-3">
              <label for="district" class="form-label">Quận huyện</label>
              <select id="district" class="form-select" v-model="selectedDistrictId" @change="onDistrictChange">
                <option v-for="(item, index) in districts" :key="index" :value="item.DISTRICT_ID">
                  {{ item.DISTRICT_NAME }}
                </option>
              </select>
              <small class="text-danger fw-bold ">
                {{ errors?.selectedDistrictId }}
              </small>
            </div>
          </div>
          <div class="col-4">
            <div class=" mb-3">
              <label for="ward" class="form-label">Phường xã</label>
              <select id="ward" class="form-select" v-model="selectedWardId">
                <option v-for="(item, index) in wards" :key="index" :value="item.WARDS_ID">
                  {{ item.WARDS_NAME }}
                </option>
              </select>
              <small class="text-danger fw-bold ">
                {{ errors?.selectedWardId }}
              </small>
            </div>
          </div>





          <div class="col-12">
            <div class="mb-3">
              <label for="address" class="form-label">Địa chỉ</label>
              <textarea class="form-control" id="address" rows="3" placeholder="Nhập địa chỉ"
                v-model="formSubmit.street"></textarea>
              <small class="text-danger fw-bold ">
                {{ errors?.street }}
              </small>
            </div>
          </div>


         

          <div>
            <button type="submit" class="btn btn-primary btn-sm">Thêm địa chỉ</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>
