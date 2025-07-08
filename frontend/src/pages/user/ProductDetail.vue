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
                    <div class="input-group" style="width: 120px;">
                        <button class="btn btn-outline-primary" type="button">-</button>
                        <input type="number" class="form-control text-center fw-bold" value="1" min="1">
                        <button class="btn btn-outline-primary" type="button">+</button>
                    </div>
                </div>

                <div class="d-flex flex-wrap gap-2 mb-4">
                    <button class="btn btn-primary px-4 py-2">
                        <i class="bi bi-cart me-2"></i>Thêm vào giỏ hàng
                    </button>

                    <button class="btn btn-outline-danger px-4 py-2">
                        <i :class="data.isfavorite ? 'bi bi-heart-fill' : 'bi bi-heart'"></i>
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


<script lang="ts" setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

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
const productDetail = async () => {
    try {
        const reps = await axios.get(`http://localhost:8080/product/${id.value}`)
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
onMounted(() => {
    productDetail();
});
</script>

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