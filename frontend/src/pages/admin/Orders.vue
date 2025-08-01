<script lang="ts" setup>

import { computed, onMounted, ref, watch } from 'vue';
import axiosClient from '../../util/axiosClient';
import { useRoute, useRouter } from 'vue-router';
import Pagination from '../../components/common/Pagination.vue';
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
const arrays = ref<Order[]>([]);
const totalPages = ref(0);
const totalItems = ref(0);
const limit = 12;
const router = useRouter();
const route = useRoute();

const page = ref(Number(route.query.page) || 1);
const startDate = ref('');
const endDate = ref('');

const fetchOrders = async () => {
    try {
        const resp = await axiosClient.get('/admin/orders',
            {
                params: {
                    page: page.value,
                    startDate: startDate.value,
                    endDate: endDate.value,
                },
            }
        ); // Adjust the URL as needed
        if (resp.data.status === true) {
            arrays.value = resp.data.data.array;
            totalItems.value = resp.data.data.size;
            totalPages.value = Math.ceil(totalItems.value / limit);
        } else {
            arrays.value = [];
            totalItems.value = 0;
            totalPages.value = 0;
            console.warn('API trả về lỗi:', resp.data.message);
        }
    } catch (error) {
        arrays.value = [];
        totalItems.value = 0;
        totalPages.value = 0;
        console.error('Error fetching orders:', error);
    }
};
function formatDate(dateStr: string) {
    return new Date(dateStr).toLocaleDateString('vi-VN')
}

function formatPrice(price: number) {
    return price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })
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


watch([startDate, endDate, page], () => {
    router.push({
        query: {
            startDate: startDate.value || '',
            endDate: endDate.value || '',
            page: page.value !== 1 ? page.value : 1,
        },
    });
    fetchOrders();
});
onMounted(() => {
    fetchOrders();
});
const displayText = computed(() => {
    if (totalItems.value === 0) return 'Không có đơn hàng';
    if (totalItems.value === 1) return 'Hiển thị 1 đơn hàng';

    const start = (page.value - 1) * limit + 1;
    const end = Math.min(start + arrays.value.length - 1, totalItems.value);

    if (totalItems.value === arrays.value.length)
        return `Hiển thị ${arrays.value.length} đơn hàng`;

    return `Hiển thị đơn hàng ${start} đến ${end} trên tổng ${totalItems.value} đơn hàng`;
});

const showCancelModal = ref(false);
const selectedItem = ref<Order | null>(null);
const cancelOrder = ref('Đặt nhầm sản phẩm');
const confirmCancel = (item: Order) => {
    selectedItem.value = item;
    showCancelModal.value = true;
};
const deleteItem = async () => {
    if (!selectedItem.value) return;

    try {
        const response = await axiosClient.post(`/admin/order/update-status/${selectedItem.value.id}`, null, {
            params: {
                statusId: (selectedItem.value.statusId - 1),
                cancelReason: cancelOrder.value,
            },
        });
        if (response.data.status === true) {
            showSuccess(response.data.message);

            selectedItem.value = null;
            showCancelModal.value = false;
            fetchOrders();
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
</script>

<template>
    <div class="card mb-4 h-100 w-100">
        <div class="d-flex align-items-center p-4 bg-light border-bottom ">
            <h6 class="fw-bold">Đơn hàng  {{ displayText }}</h6>
        </div>
        <div class="card-body">
            <!-- Filter section -->
            <div class="row mb-3" v-if="(arrays || []).length > 0" >
                <div class="col-md-12">
                    <div class="card bg-light border-0 shadow-sm">
                        <div class="card-body p-2">
                            <div class="d-flex align-items-center mb-2 small">
                                <i class="bi bi-funnel text-primary me-1"></i>
                                <span class="fw-semibold">Lọc đơn hàng</span>
                            </div>
                            <div class="row g-2">
                                <div class="col">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-text bg-white">Từ</span>
                                        <input type="date" class="form-control" id="fromDate" v-model="startDate">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-text bg-white">Đến</span>
                                        <input type="date" class="form-control" id="toDate" v-model="endDate">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-for="order in arrays" :key="order.id" class="card card_item mb-4 overflow-hidden">

                <div class="card-header bg-white py-2 px-3 d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <div :class="['order-status-indicator me-2', statusColor(order.statusId)]"></div>
                        <div>
                            <div class="fw-semibold small">Mã đơn: #ORD{{ order.id }}</div>
                            <div class="d-flex align-items-center text-muted small">
                                <i class="bi bi-calendar3 me-1"></i>
                                <span>{{ formatDate(order.createAt) }}</span>
                                <span class="mx-1">•</span>
                                <i class="bi bi-person me-1"></i>
                                <span>{{ order.fullName }}</span>
                            </div>
                        </div>
                    </div>
                    <span :class="['badge rounded-pill px-2 py-1 small', badgeColor(order.statusId)]">
                        <i class="bi me-1" :class="statusIcon(order.statusId)"></i>{{ order.status }}
                    </span>
                </div>

                <div class="card-body py-2 px-3">
                    <div class="row align-items-center">
                        <div class="col-7">
                            <div class="small">
                                <div class="mb-1">
                                    <span class="text-muted">Tổng tiền:</span>
                                    <span class="fw-bold text-danger ms-1">{{ formatPrice(order.totalPrice)
                                        }}</span>
                                </div>
                                <div class="text-muted">
                                    <i class="bi bi-box me-1"></i>{{ order.orderItems.length }} sản phẩm
                                </div>
                            </div>
                        </div>
                        <div class="col-5 text-end">
                            <button class="btn btn-sm btn-outline-danger me-1" v-if="order.statusId === 0"
                                @click="confirmCancel(order)">
                                <i class="bi bi-x-circle me-1"></i>Hủy
                            </button>
                            <router-link :to="`/admin/order/${order.id}`" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye me-1"></i>Chi tiết
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="arrays.length > 0">
                <Pagination :currentPage="page" :totalPages="totalPages" @update:currentPage="page = $event" />
            </div>
        </div>
        <div class="modal fade" :class="{ show: showCancelModal }" id="deleteModal" tabindex="-1"
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
                        <button type="button" class="btn btn-danger rounded-pill px-4" @click="deleteItem">
                            <i class="bi bi-trash me-1"></i> Xác nhận hủy
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-backdrop fade" :class="{ show: showCancelModal }"
            :style="{ display: showCancelModal ? 'block' : 'none' }"></div>
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

.order-status-indicator {
    width: 3px;
    height: 30px;
    border-radius: 1px;
}

.nav-pills .nav-link {
    border-radius: 4px;
    font-weight: 500;
    font-size: 0.85rem;
    margin: 0 2px;
}

.nav-pills .nav-link.active {
    background-color: var(--bs-primary);
}
</style>