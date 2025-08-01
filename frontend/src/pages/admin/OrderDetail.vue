<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import axios from 'axios';

export interface Order {
    id: number;
    userId: number;
    fullName: string;
    phone: string;
    address: string;
    cancelOrder: string;
    totalPrice: number;
    createAt: string;
    status: string;
    statusId: number;
    paymentStatus: string;
    paymentMethod: string;
    orderItems: {
        id: number;
        quantity: number;
        price: number;
        product: {
            id: number;
            name: string;
            description: string;
            image: string;
            price: number;
            weight: number;
            quantity: number;
            createAt: string;
            category: { id: number; name: string }[];
        };
    }[];
}
const route = useRoute();
const orderDetail = ref<Order | null>(null);

const fetchOrderDetail = async () => {
    try {
        const id = route.params.id;
        const response = await axiosClient.get(`/admin/order/${id}`);
        if (response.data.status === true) {
            orderDetail.value = response.data.data;
        } else {
            orderDetail.value = null;
        }
    } catch (error) {
        console.error('Error fetching order detail:', error);
        orderDetail.value = null;
    }
};
const cancelOrder = ref('Đặt nhầm sản phẩm');
const showCancelModal = ref(false);
const showUpdateStatusModal = ref(false);
const selectedItem = ref<Order | null>(null);

const confirmCancel = (item: Order) => {
    selectedItem.value = item;
    showCancelModal.value = true;
};
const confirmUpdateStatus = (item: Order) => {
    selectedItem.value = item;
    showUpdateStatusModal.value = true;
};
onMounted(() => {
    fetchOrderDetail();
});


const updateOrder = async (statusId: number) => {
    if (!selectedItem.value) return;

    try {
        const response = await axiosClient.post(`/admin/order/update-status/${selectedItem.value.id}`, null, {
            params: {
                statusId: statusId,
                cancelReason: cancelOrder.value,
            },
        });
        if (response.data.status === true) {
            showSuccess(response.data.message);

            selectedItem.value = null;
            showCancelModal.value = false;
            showUpdateStatusModal.value = false;
            fetchOrderDetail();
        } else {
            showError(response.data.message);
        }
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error('API error:', error.response?.data || error.message);
            showError(error.response?.data.message || 'Đã xảy ra lỗi ');
        } else {
            console.error('Unexpected error:', error);
            showError('Đã xảy ra lỗi không xác định');
        }
    }
};
const stringStatus = (statusId: number): string => {
    switch (statusId) {
        case -1: return 'Đã hủy';
        case 0: return 'Chờ xác nhận';
        case 1: return 'Đã xác nhận';
        case 2: return 'Đang giao';
        case 3: return 'Đã giao';
        default: return 'Không xác định';
    }
};

function formatPrice(price: number) {
    return price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}

function formatDateTime(dateStr: string) {
    const date = new Date(dateStr);
    return date.toLocaleString('vi-VN');
}

function statusColor(statusId: number): string {
    switch (statusId) {
        case -1: return 'bg-warning text-dark';      // Đã hủy
        case 0: return 'bg-secondary text-light';    // Chờ xác nhận
        case 1: return 'bg-info text-dark';          // Đã xác nhận
        case 2: return 'bg-primary text-light';      // Đang giao
        case 3: return 'bg-success text-light';      // Đã giao
        default: return 'bg-danger text-light';      // Không xác định
    }
}


function badgeColor(statusId: number) {
    return statusColor(statusId)
}

function statusIcon(statusId: number): string {
    switch (statusId) {
        case -1: return 'bi-x-circle';         // Đã hủy
        case 0: return 'bi-clock';             // Chờ xác nhận
        case 1: return 'bi-check-circle';      // Đã xác nhận
        case 2: return 'bi-truck';             // Đang giao
        case 3: return 'bi-box-seam';          // Đã giao
        default: return 'bi-question-circle';  // Không xác định
    }
}


</script>

<template>
    <div class="card mb-4 h-100 w-100">
        <div v-if="orderDetail">
            <div class="d-flex align-items-center p-3 bg-light border-bottom justify-content-between">
                <h6 class="mb-0 fw-bold">Chi tiết đơn hàng #ORD{{ orderDetail.id }}</h6>
                <span :class="['badge rounded-pill px-2 py-1 small', badgeColor(orderDetail.statusId)]">
                    <i class="bi me-1" :class="statusIcon(orderDetail.statusId)"></i>{{
                        orderDetail.status }}
                </span>
            </div>

            <div class="card-body flex-grow-1">
                <!-- Thông tin người đặt -->
                <div class="row mb-4">
                    <div class="col-md-6 mb-3">
                        <div class="card h-100">
                            <div class="card-header bg-light py-2">
                                <h6 class="mb-0 small">Thông tin người đặt</h6>
                            </div>
                            <div class="card-body">
                                <p class="mb-1 small"><strong>Họ tên:</strong> {{ orderDetail.fullName }}</p>
                                <p class="mb-1 small"><strong>Số điện thoại:</strong> {{ orderDetail.phone }}</p>
                                <p class="mb-0 small"><strong>Địa chỉ:</strong> {{ orderDetail.address }}</p>
                            </div>
                        </div>
                    </div>

                    <!-- Thông tin đơn hàng -->
                    <div class="col-md-6 mb-3">
                        <div class="card h-100">
                            <div class="card-header bg-light py-2">
                                <h6 class="mb-0 small">Thông tin đơn hàng</h6>
                            </div>
                            <div class="card-body">
                                <p class="mb-1 small">
                                    <strong>Ngày đặt hàng:</strong> {{ formatDateTime(orderDetail.createAt) }}
                                </p>
                                <p class="mb-1 small">
                                    <strong>Trạng thái:</strong>
                                    <span
                                        :class="['badge rounded-pill px-2 py-1 small', badgeColor(orderDetail.statusId)]">
                                        <i class="bi me-1" :class="statusIcon(orderDetail.statusId)"></i>{{
                                            orderDetail.status }}
                                    </span>
                                </p>
                                <p class="mb-1 small">
                                    <strong>Phương thức thanh toán:</strong> {{ orderDetail.paymentMethod }}
                                </p>
                                <p class="mb-0 small">
                                    <strong>Trạng thái thanh toán:</strong>
                                    <span class="badge"
                                        :class="orderDetail.paymentStatus === 'Đã thanh toán' ? 'bg-success' : 'bg-danger'">
                                        {{ orderDetail.paymentStatus }}
                                    </span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div v-if="orderDetail.cancelOrder">
                        <div class="card ">
                            <div class="card-header bg-light py-2">
                                <h6 class="mb-0 small">Lý do hủy đơn</h6>
                            </div>
                            <div class="card-body">
                                <p class="mb-0 small">{{ orderDetail.cancelOrder }}</p>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Danh sách sản phẩm -->
                <div class="card border mb-4">
                    <div class="card-header bg-light py-2">
                        <h6 class="mb-0 small">Danh sách sản phẩm</h6>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table mb-0 table-hover">
                                <thead class="table-light">
                                    <tr>
                                        <th scope="col" class="ps-3">Sản phẩm</th>
                                        <th scope="col" class="text-center">Giá</th>
                                        <th scope="col" class="text-center">Số lượng</th>
                                        <th scope="col" class="text-end pe-3">Thành tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="item in orderDetail.orderItems" :key="item.id">
                                        <td class="ps-3">
                                            <div class="d-flex align-items-center">
                                                <img :src="item.product.image" alt="ảnh sản phẩm" class="me-2"
                                                    style="width: 50px; height: 50px; object-fit: cover;" />
                                                <div>
                                                    <router-link :to="`/admin/edit-product/${item.product.id}`"
                                                        class="text-decoration-none">
                                                        <p class="mb-0 small">{{ item.product.name }}</p>
                                                    </router-link>
                                                    <span class="badge bg-primary me-1"
                                                        v-for="(cat, index) in item.product?.category" :key="index">{{
                                                            cat.name }}</span>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-center align-middle small">{{ formatPrice(item.price) }}</td>
                                        <td class="text-center align-middle small">{{ item.quantity }}</td>
                                        <td class="text-end pe-3 align-middle fw-bold small">
                                            {{ formatPrice(item.price * item.quantity) }}
                                        </td>
                                    </tr>
                                    <tr class="table-light">
                                        <td colspan="3" class="text-end fw-bold pe-3">Tổng cộng:</td>
                                        <td class="text-end pe-3 fw-bold text-danger">
                                            {{ formatPrice(orderDetail.totalPrice) }}
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Nút thao tác -->
                <div class="d-flex justify-content-end gap-2">
                    <router-link to="/admin/orders" class="btn btn-sm btn-outline-secondary">
                        <i class="bi bi-arrow-left me-1"></i>Quay lại
                    </router-link>
                    <button class="btn btn-sm btn-outline-danger" v-if="orderDetail.statusId === 0"
                        @click="confirmCancel(orderDetail)">
                        <i class="bi bi-x-circle me-1"></i>Hủy đơn hàng
                    </button>
                    <button class="btn btn-sm btn-outline-success me-1"
                        v-if="orderDetail.statusId >= 0 && orderDetail.statusId < 3"
                        @click="confirmUpdateStatus(orderDetail)">
                        <i class="bi bi-check me-1"></i>Chuển sang trạng thái tiếp theo
                    </button>
                </div>
            </div>
        </div>

        <div v-else class="text-center py-5">
            <div class="spinner-border text-primary mb-3" role="status"></div>
            <p class="text-muted">Đang tải chi tiết đơn hàng...</p>
        </div>
        <div class="modal fade" v-if="selectedItem" :class="{ show: showCancelModal }" id="deleteModal" tabindex="-1"
            aria-labelledby="deleteModalLabel" aria-hidden="true"
            :style="{ display: showCancelModal ? 'block' : 'none' }">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content border-0 shadow-sm">
                    <div class="modal-header border-0 pb-0">
                        <h5 class="modal-title fw-bold" id="deleteModalLabel">Xác nhận hủy đơn hàng</h5>
                        <button type="button" class="btn-close" @click="showCancelModal = false"
                            aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-center py-4">
                        <div class="mb-3">
                            <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                        </div>

                        <p class="text-muted small mt-2">Hành động này không thể hoàn tác.</p>
                        <div class="form-group text-start">
                            <label class="form-label fw-bold">Lý do hủy đơn:</label>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" v-model="cancelOrder"
                                    value="Sản phẩm đã hết hàng" id="reason6">
                                <label class="form-check-label" for="reason6">Sản phẩm đã hết hàng</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" v-model="cancelOrder" value="Lý do khác"
                                    id="reason7">
                                <label class="form-check-label" for="reason7">Lý do khác</label>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer border-0 justify-content-center pt-0">
                        <button type="button" class="btn btn-danger rounded-pill px-4"
                            @click="updateOrder((selectedItem.statusId - 1))">
                            <i class="bi bi-trash me-1"></i> Xác nhận hủy
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-backdrop fade" :class="{ show: showCancelModal }"
            :style="{ display: showCancelModal ? 'block' : 'none' }"></div>

        <div class="modal fade" v-if="selectedItem" :class="{ show: showUpdateStatusModal }" id="updateStatusModal"
            tabindex="-1" aria-labelledby="updateStatusModalLabel" aria-hidden="true"
            :style="{ display: showUpdateStatusModal ? 'block' : 'none' }">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content border-0 shadow-sm">
                    <div class="modal-header border-0 pb-0">
                        <h5 class="modal-title fw-bold" id="updateStatusModalLabel">Xác nhận chuyển trạng thái đơn hàng
                            #ORD{{ selectedItem?.id }}</h5>
                        <button type="button" class="btn-close" @click="showUpdateStatusModal = false"
                            aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-center py-4">
                        <div class="mb-3">
                            <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
                        </div>

                        <p class="text-muted small mt-2">Hành động này không thể hoàn tác.</p>
                        <div>
                            Xác nhận chuyển trạng thái đơn hàng từ
                            <p class="fs-5">
                                <span
                                    :class="['badge rounded-pill px-2 py-1 small', badgeColor(selectedItem?.statusId)]">

                                    {{ stringStatus(selectedItem?.statusId) }}

                                </span>
                                ->
                                <span
                                    :class="['badge rounded-pill px-2 py-1 small', badgeColor((selectedItem?.statusId + 1))]">
                                    {{ stringStatus((selectedItem?.statusId + 1)) }}

                                </span>
                            </p>


                        </div>

                    </div>
                    <div class="modal-footer border-0 justify-content-center pt-0">
                        <button type="button" class="btn btn-success rounded-pill px-4"
                            @click="updateOrder((selectedItem?.statusId + 1))">
                            <i class="bi bi-check me-1"></i> Xác nhận chuyển
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-backdrop fade" :class="{ show: showUpdateStatusModal }"
            :style="{ display: showUpdateStatusModal ? 'block' : 'none' }"></div>
    </div>
</template>
