<script setup lang="ts">
import { ref, computed, onMounted, watch } from "vue";
import axiosClient from "../../util/axiosClient";
import { showError, showSuccess } from "../../util/useAlert";
import axios from "axios";
import { useAuthStore } from "../../store/auth";
import { useRouter } from "vue-router";
// Cấu trúc dữ liệu giỏ hàng theo API
interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    image: string;
    quantity: number;
    weight: number;
    isfavorite: boolean;
    category: { id: number; name: string }[];
    createAt: string;
}
interface CartItem {
    id: number;
    product: Product;
    quantity: number;
}

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

const addressDetail = ref<Address | null>(null);

const cartItems = ref<CartItem[]>([]);
const showDeleteModal = ref(false);
const selectedItem = ref<CartItem | null>(null);
const auth = useAuthStore();
const router = useRouter();

const provinces = ref<Province[]>([]);
const districtsCache = ref(new Map<number, District[]>());
const wardsCache = ref(new Map<number, Ward[]>());

const data = async () => {
    try {
        const reps = await axiosClient.get("/user/cart");
        if (reps.data.status === true) {
            cartItems.value = reps.data.data;
        } else {
            cartItems.value = [];
        }
    } catch (error) {
        cartItems.value = [];
    }
};

const fetchAddresses = async (overrideAddressId?: number): Promise<void> => {
    try {
        const addressId = overrideAddressId ?? auth.user?.addressId;
        if (!addressId) {
            addressDetail.value = null;
            return;
        }

        const response = await axiosClient.get(`/user/addresses/${addressId}`);

        if (response.data.status === true && response.data.data) {
            const addr = response.data.data;
            addressDetail.value = addr;

            // Load district & ward cache
            await fetchDistricts(addr.provinceId);
            await fetchWards(addr.districtId);
        } else {
            addressDetail.value = null;
        }
    } catch (error) {
        console.error("Error fetching address:", error);
        addressDetail.value = null;
    }
};

const fetchProvinces = async () => {
    try {
        const response = await axios.get(
            "/api/viettel/v2/categories/listProvinceById?provinceId=-1"
        );
        if (response.data.status === 200 && response.data.data) {
            provinces.value = response.data.data;
        }
    } catch (error) {
        console.error("Error fetching provinces:", error);
    }
};

// Fetch districts by provinceId, cache results
const fetchDistricts = async (provinceId: number) => {
    if (districtsCache.value.has(provinceId)) return; // Already cached
    try {
        const response = await axios.get(
            `/api/viettel/v2/categories/listDistrict?provinceId=${provinceId}`
        );
        if (response.data.status === 200 && response.data.data) {
            districtsCache.value.set(provinceId, response.data.data);
        }
    } catch (error) {
        console.error(
            `Error fetching districts for provinceId ${provinceId}:`,
            error
        );
    }
};

// Fetch wards by districtId, cache results
const fetchWards = async (districtId: number) => {
    if (wardsCache.value.has(districtId)) return; // Already cached
    try {
        const response = await axios.get(
            `/api/viettel/v2/categories/listWards?districtId=${districtId}`
        );
        if (response.data.status === 200 && response.data.data) {
            wardsCache.value.set(districtId, response.data.data);
        }
    } catch (error) {
        console.error(`Error fetching wards for districtId ${districtId}:`, error);
    }
};

// Helper: get district by id from cache
function getDistrictById(districtId: number): District | undefined {
    for (const districts of districtsCache.value.values()) {
        const found = districts.find((d) => d.DISTRICT_ID === districtId);
        if (found) return found;
    }
    return undefined;
}

// Helper: get ward by id from cache
function getWardById(wardId: number): Ward | undefined {
    for (const wards of wardsCache.value.values()) {
        const found = wards.find((w) => w.WARDS_ID === wardId);
        if (found) return found;
    }
    return undefined;
}

// Construct full address string
function setAddressStreet(
    provinceId: number,
    districtId: number,
    wardId: number,
    street: string
): string {
    const province = provinces.value.find((p) => p.PROVINCE_ID === provinceId);
    const district = getDistrictById(districtId);
    const ward = getWardById(wardId);

    // Return fallback if missing
    if (!province && !district && !ward) {
        return street || "";
    }

    // Compose array of address parts, filter empty
    const parts = [
        street,
        ward?.WARDS_NAME,
        district?.DISTRICT_NAME,
        province?.PROVINCE_NAME,
    ].filter(Boolean);
    return parts.join(", ");
}

const addressFullMap = computed(() => {
    const map = new Map<number, string>();
    if (addressDetail.value) {
        map.set(
            addressDetail.value.id,
            setAddressStreet(
                addressDetail.value.provinceId,
                addressDetail.value.districtId,
                addressDetail.value.wardId,
                addressDetail.value.street
            )
        );
    }
    return map;
});

const getFullAddress = (id?: number): string => {
    return id && addressFullMap.value.has(id)
        ? addressFullMap.value.get(id)!
        : "Không xác định";
};

// Tính tổng tiền
const totalAmount = computed(() => {
    return cartItems.value.reduce(
        (total, item) => total + item.product.price * item.quantity,
        0
    );
});

// Định dạng giá tiền
const formatPrice = (price: number): string => {
    return new Intl.NumberFormat("vi-VN").format(price) + " ₫";
};

const addAndUpdateCart = async (item: CartItem, quantity: number) => {
    try {
        const response = await axiosClient.post("/user/cart", null, {
            params: {
                productId: item.product.id,
                quantity: quantity,
            },
        });
        if (response.data.status === true) {
            showSuccess(response.data.message);
            data();
            const cart = response.data.data.length;
            auth.$patch({
                user: { ...auth.user!, cart: cart },
            });
        } else {
            showError(response.data.message);
        }
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error("API error:", error.response?.data || error.message);
            showError(
                error.response?.data.message || "Đã xảy ra lỗi khi cập nhật giỏ hàng"
            );
        }
    }
};

// Mở modal xác nhận xóa
const confirmDelete = (item: CartItem) => {
    selectedItem.value = item;
    showDeleteModal.value = true;
};

// Xóa sản phẩm khỏi giỏ hàng
const deleteItem = async () => {
    if (!selectedItem.value) return;

    try {
        const response = await axiosClient.delete(
            `/user/cart/${selectedItem.value.product.id}`
        );
        if (response.data.status === true) {
            showSuccess(response.data.message);
            showDeleteModal.value = false;
            selectedItem.value = null;
            data();
            const cart = Array.isArray(response.data.data)
                ? response.data.data.length
                : 0;
            auth.$patch({
                user: { ...auth.user!, cart: cart },
            });
        } else {
            showError(response.data.message);
        }
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error("API error:", error.response?.data || error.message);
            showError(
                error.response?.data.message || "Đã xảy ra lỗi khi xóa sản phẩm"
            );
        } else {
            console.error("Unexpected error:", error);
            showError("Đã xảy ra lỗi không xác định khi xóa sản phẩm");
        }
    }
};

const goToConfirmAddress = () => {
    router.push("/confirm-address");
};

onMounted(() => {
    data();
    fetchProvinces();
    fetchAddresses();
});
watch(
    () => auth.user?.addressId,
    (newVal: number | undefined, oldVal: number | undefined) => {
        if (newVal !== oldVal) {
            fetchAddresses(newVal);
        }
    }
);


const paymentMethod = ref<number>(0);
const payment = async () => {
    try {
        const pay = {
            fullName: addressDetail.value?.fullName || '',
            phone: addressDetail.value?.phone || '',
            address: getFullAddress(addressDetail.value?.id),
            paymentMethod: Number(paymentMethod.value),
        }
        console.log(pay);
        const reps = await axiosClient.post('/user/payment', null, {
            params: pay
        });
        if (reps.data.status == true) {
            localStorage.setItem('orderId', reps.data.data);
            console.log(reps.data.data)
            if (Number(paymentMethod.value) == 1) {
                try {
                    const orderId = Math.floor(new Date().getTime() / 1000) + 10;
                    const total = cartItems.value.reduce(
                        (total, item) => total + item.product.price * item.quantity,
                        0
                    )
                    const paymentData = {
                        orderId: Number(orderId),
                        orderInfor: "Thanh toán đơn hàng " + orderId,
                        total: total,
                    };
                    const paymentOnline = await axiosClient.get('/user/payment-online', {
                        params: paymentData
                    })
                    console.log(paymentOnline)
                    if (paymentOnline.status === 200 && paymentOnline.data.status) {
                        window.location.href = paymentOnline.data.data;
                    } else {
                        showError("Có lỗi khi thanh toán online");
                    }
                } catch (error) {
                    console.log(error)
                }
            } else {
                showSuccess(reps.data.message);
                router.push('/user/orders');
            }

            auth.$patch({
                user: { ...auth.user!, cart: 0 },
            });
            await auth.fetchUserInfo();

        } else {
            showError(reps.data.message);
        }

    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error('API error:', error.response?.data || error.message);
            showError(error.response?.data.message || 'Đã xảy ra lỗi khi đặt hàng');
        } else {
            console.error('Unexpected error:', error);
            showError('Đã xảy ra lỗi không xác định khi đặt hàng');
        }
    }
};
</script>
<template>
    <div class="payment-page container py-4">
        <h4 class="mb-4 fw-bold text-primary">Thanh toán đơn hàng</h4>

        <div class="row g-4">
            <div class="col-lg-8">
                <!-- Thông tin sản phẩm -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-bag-check me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Thông tin sản phẩm</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <!-- Danh sách sản phẩm -->
                        <div v-if="cartItems.length > 0">
                            <div v-for="item in cartItems" :key="item.id"
                                class="product-item d-flex mb-4 pb-4 border-bottom">
                                <div class="product-img position-relative">
                                    <img :src="item.product.image" class="rounded-3 me-3"
                                        style="width: 100px; height: 100px; object-fit: cover" alt="Sản phẩm" />
                                </div>
                                <div class="flex-grow-1 d-flex flex-column justify-content-between">
                                    <div>
                                        <div class="d-flex justify-content-between align-items-start">
                                            <h6 class="mb-1 fw-bold">{{ item.product.name }}</h6>
                                            <button class="btn btn-sm text-danger border-0 p-0"
                                                @click="confirmDelete(item)" title="Xóa sản phẩm">
                                                <i class="bi bi-x-lg"></i>
                                            </button>
                                        </div>
                                        <p class="text-muted small m-0">
                                            <span class="badge bg-primary me-1"
                                                v-for="(cat, index) in item.product?.category" :key="index">{{ cat.name
                                                }}</span>
                                        </p>
                                    </div>
                                    <div class="d-flex justify-content-between align-items-end">
                                        <div class="d-flex align-items-center">
                                            <span class="me-2 text-muted small">Số lượng:</span>
                                            <div class="input-group input-group-sm quantity-control"
                                                style="width: 110px">
                                                <button class="btn btn-outline-primary rounded-start" type="button"
                                                    @click="
                                                        addAndUpdateCart(item, item.quantity > 1 ? -1 : 1)
                                                        ">
                                                    <i class="bi bi-dash"></i>
                                                </button>
                                                <input type="text" class="form-control text-center bg-white"
                                                    :value="item.quantity" readonly />
                                                <button class="btn btn-outline-primary rounded-end" type="button"
                                                    @click="addAndUpdateCart(item, 1)">
                                                    <i class="bi bi-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="fw-bold text-primary fs-5">
                                            Giá: {{ formatPrice(item.product.price) }} x
                                            {{ item.quantity }}
                                        </div>
                                        <p class="mb-0 fw-bold text-primary fs-5">
                                            {{ formatPrice(item.product.price * item.quantity) }}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div v-else class="text-center text-muted">Giỏ hàng trống.</div>

                        <!-- Tổng tiền -->
                        <div class="fee-details mt-4 border-top">
                            <div class="d-flex justify-content-between fw-bold mt-3">
                                <span>Tổng thanh toán:</span>
                                <span class="text-danger fs-4">{{
                                    formatPrice(totalAmount)
                                    }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bên phải -->
            <div class="col-lg-4">
                <!-- Địa chỉ nhận hàng -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-geo-alt me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Địa chỉ nhận hàng</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <div class="mb-3 p-3 border rounded-3 bg-light" v-if="addressDetail">
                            <p class="mb-1 fw-bold">{{ addressDetail?.fullName }}</p>
                            <p class="mb-1">
                                <i class="bi bi-telephone me-2 text-primary"></i>{{ addressDetail?.phone }}
                            </p>
                            <p class="mb-0">
                                <i class="bi bi-house me-2 text-primary"></i>{{ getFullAddress(addressDetail?.id) }}
                            </p>
                        </div>
                        <div class="d-grid">
                            <button class="btn btn-outline-primary d-flex align-items-center justify-content-center"
                                @click="goToConfirmAddress" v-if="addressDetail">
                                <i class="bi bi-pencil me-2"></i>Thay đổi địa chỉ
                            </button>
                            <router-link to="/user/add-address" v-if="!addressDetail" class="btn  btn-outline-primary d-flex align-items-center justify-content-center"
                                title="Thêm địa chỉ">
                                <i class="bi bi-plus-lg me-1"></i>Thêm địa chỉ
                            </router-link>
                        </div>
                    </div>
                </div>

                <!-- Phương thức thanh toán -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-credit-card me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Phương thức thanh toán</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <div class="payment-methods">
                            <div class="form-check payment-method-item mb-3 p-3 border rounded-3">
                                <input class="form-check-input" type="radio" v-model="paymentMethod" id="cod"
                                    value="0" />
                                <label class="form-check-label d-flex align-items-center" for="cod">
                                    <span
                                        class="payment-icon me-3 bg-warning text-white rounded-circle d-flex align-items-center justify-content-center"
                                        style="width: 40px; height: 40px">
                                        <i class="bi bi-cash"></i>
                                    </span>
                                    <div>
                                        <span class="d-block fw-bold">Thanh toán khi nhận hàng (COD)</span>
                                        <small class="text-muted">Thanh toán bằng tiền mặt khi nhận hàng</small>
                                    </div>
                                </label>
                            </div>

                            <div class="form-check payment-method-item p-3 border rounded-3">
                                <input class="form-check-input" type="radio" v-model="paymentMethod" id="banking"
                                    value="1" />
                                <label class="form-check-label d-flex align-items-center" for="banking">
                                    <span
                                        class="payment-icon me-3 bg-primary text-white rounded-circle d-flex align-items-center justify-content-center"
                                        style="width: 40px; height: 40px">
                                        <i class="bi bi-bank"></i>
                                    </span>
                                    <div>
                                        <span class="d-block fw-bold">Chuyển khoản ngân hàng</span>
                                        <small class="text-muted">Thanh toán qua tài khoản ngân hàng</small>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Nút đặt hàng -->
                <div class="d-flex gap-2">
                    <button class="btn btn-sm btn-outline-secondary rounded-3 flex-grow-1"
                        @click="$router.push('/cart')">
                        <i class="bi bi-arrow-left me-2"></i>Quay lại giỏ hàng
                    </button>
                    <button class="btn btn-primary btn-sm py-3 rounded-3 fw-bold flex-grow-1" v-on:click="payment">
                        <i class="bi bi-check2-circle me-2"></i>Xác nhận đặt hàng
                    </button>
                </div>
            </div>
        </div>

        <!-- Modal xác nhận xóa sản phẩm -->
        <div class="modal fade" :class="{ show: showDeleteModal }" id="deleteModal" tabindex="-1"
            aria-labelledby="deleteModalLabel" aria-hidden="true"
            :style="{ display: showDeleteModal ? 'block' : 'none' }">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content border-0 shadow-sm">
                    <div class="modal-header border-0 pb-0">
                        <h5 class="modal-title fw-bold" id="deleteModalLabel">
                            Xác nhận xóa sản phẩm
                        </h5>
                        <button type="button" class="btn-close" @click="showDeleteModal = false"
                            aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-center py-4">
                        <div class="mb-3">
                            <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem"></i>
                        </div>
                        <p class="mb-0">
                            Bạn có chắc chắn muốn xóa sản phẩm
                            <strong>{{ selectedItem?.product.name }}</strong> khỏi giỏ hàng?
                        </p>
                        <p class="text-muted small mt-2">
                            Hành động này không thể hoàn tác.
                        </p>
                    </div>
                    <div class="modal-footer border-0 justify-content-center pt-0">
                        <button type="button" class="btn btn-danger rounded-pill px-4" @click="deleteItem">
                            <i class="bi bi-trash me-1"></i> Xóa sản phẩm
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Backdrop cho modal -->
        <div class="modal-backdrop fade" :class="{ show: showDeleteModal }"
            :style="{ display: showDeleteModal ? 'block' : 'none' }"></div>
    </div>
</template>

<style scoped>
.card {
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
    overflow: hidden;
}

.card-header {
    background-color: #fff;
}

.product-item:last-child {
    border-bottom: none !important;
    padding-bottom: 0 !important;
    margin-bottom: 0 !important;
}

.quantity-control .form-control {
    border-left: 0;
    border-right: 0;
}

.quantity-control .btn {
    min-width: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.payment-method-item {
    cursor: pointer;
    transition: all 0.2s;
}

.payment-method-item:hover {
    background-color: #f8f9fa;
}

.form-check-input:checked~.form-check-label .payment-icon {
    transform: scale(1.1);
}

.payment-icon {
    transition: all 0.2s;
}

.btn {
    transition: all 0.3s;
}

.btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn:active {
    transform: translateY(0);
}
</style>
