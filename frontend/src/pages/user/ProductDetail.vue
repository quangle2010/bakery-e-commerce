<script lang="ts" setup>
import { onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import axios from 'axios';
import { useAuthStore } from '../../store/auth';

const route = useRoute();

interface Product {
    id: number;
    name: string;
    price: number;
    quantity: number;
    image: string;
    weight: number;
    isfavorite: boolean;
    category: string;
    description: string;
    createAt: string;
}
const data = ref<Product | null>(null);
const id = ref(route.params.id);
const quantity = ref(Number(route.query.quantity) || 1);
const productDetail = async () => {
    try {
        const reps = await axiosClient.get(`/product/${id.value}`)
        if (reps.data.status === true) {
            data.value = reps.data.data;
        } else {
            data.value = null;
        }
    } catch (error) {
        data.value = null;
        console.log(error);
    }

}

const addAndRemoveFavorite = async () => {
    if (data.value) {
        try {
            const response = await axiosClient.post(`/user/favorite?productId=${data.value.id}`);
            if (response.data.status === true) {
                showSuccess(response.data.message);
                productDetail();
            } else {
                showError(response.data.message);
            }
        } catch (error) {
            console.error('API error:', error);
        }
    }
};



const auth = useAuthStore();

const addAndUpdateCart = async () => {
    if (data.value) {
        try {
            const response = await axiosClient.post(`/user/cart?productId=${data.value.id}&&quantity=${quantity.value}`);
            if (response.data.status === true) {
                showSuccess(response.data.message);
                productDetail();
                const cart = Array.isArray(response.data.data) ? response.data.data.length : 0;
                auth.$patch({
                    user: { ...auth.user!, cart: cart }
                });
            } else {
                showError(response.data.message);
                console.warn('Error toggling cart:', response.data.message);
            }
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.error('API error:', error.response?.data || error.message);
                showError(error.response?.data.message || 'Đã xảy ra lỗi khi cập nhật giỏ hàng');
            } else {
                console.error('Unexpected error:', error);
                showError('Đã xảy ra lỗi không xác định khi cập nhật giỏ hàng');
            }
        }
    }
};

watch(quantity, (val) => {
    const max = data.value?.quantity ?? 1;

    if (val < 1) {
        quantity.value = 1;
    } else if (val > max) {
        quantity.value = max;
    }
});
onMounted(() => {
    productDetail();
  
});
</script>


<template>
    <div class="row" v-if="data">
        <div class="row mb-2">
            <!-- Hình ảnh -->
            <div class="col-md-5">
                <div class="product-image-container border rounded p-2 bg-white mb-3">
                    <img :src="data.image" class="img-fluid rounded" :alt="data.name">
                </div>
            </div>

            <!-- Thông tin sản phẩm -->
            <div class="col-md-7">
                <h2 class="mb-2 fw-bold">{{ data.name }}</h2>
                <div class="mb-3">
                    <span class="badge bg-primary me-1">{{ data.category }}</span>
                </div>

                <div class="mb-4">
                    <h3 class="text-primary fw-bold">{{ data.price.toLocaleString() }}₫</h3>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold mb-2">Số lượng</label>
                    <div class="input-group" style="width: 150px;">
                        <button class="btn btn-outline-primary" type="button"
                            @click="quantity = Math.max(1, quantity - 1)">-</button>
                        <input type="number" class="form-control text-center fw-bold" v-model="quantity" min="1"
                            :disabled="data.quantity <= 1" :max="data.quantity" />
                        <button class="btn btn-outline-primary" type="button"
                            @click="quantity = Math.min(data.quantity, quantity + 1)">+</button>
                    </div>
                </div>

                <div class="d-flex flex-wrap gap-2 mb-4">
                    <button class="btn btn-primary px-4 py-2" @click="addAndUpdateCart">
                        <i class="bi bi-cart me-2"></i>Thêm vào giỏ hàng
                    </button>

                    <button class="btn btn-outline-danger px-4 py-2" @click="addAndRemoveFavorite">
                        <i :class="data.isfavorite ? 'bi bi-heartbreak' : 'bi bi-heart'"></i>
                        {{ data.isfavorite ? 'Bỏ yêu thích' : 'Yêu thích' }}
                    </button>
                </div>
            </div>
        </div>

        <!-- Accordion mô tả -->
        <div class="row mb-5">
            <div class="col-12">
                <div class="accordion" id="productAccordion">
                    <div class="accordion-item border mb-3 rounded">
                        <h2 class="accordion-header" id="headingDescription">
                            <button class="accordion-button fw-bold" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseDescription" aria-expanded="true"
                                aria-controls="collapseDescription">
                                Chi tiết sản phẩm
                            </button>
                        </h2>
                        <div id="collapseDescription" class="accordion-collapse collapse show"
                            aria-labelledby="headingDescription" data-bs-parent="#productAccordion">
                            <div class="accordion-body">
                                <h4>{{ data.name }}</h4>
                                <p>{{ data.description }}</p>

                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <th width="30%">Tên sản phẩm</th>
                                            <td>{{ data.name }}</td>
                                        </tr>
                                        <tr>
                                            <th>Danh mục</th>
                                            <td>{{ data.category }}</td>
                                        </tr>
                                        <tr>
                                            <th>Khối lượng</th>
                                            <td>{{ data.weight }}g</td>
                                        </tr>
                                        <tr>
                                            <th>Giá</th>
                                            <td>{{ data.price.toLocaleString() }}₫</td>
                                        </tr>

                                        <tr>
                                            <th>Ngày tạo</th>
                                            <td>{{ new Date(data.createAt).toLocaleDateString() }}</td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Loading / Error fallback -->
    <div v-else>
        <p class="text-center text-muted">Đang tải thông tin sản phẩm...</p>
    </div>
</template>



<style scoped>
.accordion-button:not(.collapsed) {
    background-color: rgba(13, 110, 253, 0.1);
    color: #0d6efd;
    box-shadow: none;
}

.accordion-button:focus {
    box-shadow: none;
    border-color: rgba(13, 110, 253, 0.25);
}

.accordion-item {
    transition: all 0.3s ease;
}

.accordion-item:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
}
</style>