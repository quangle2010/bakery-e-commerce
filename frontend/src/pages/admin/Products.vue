<template>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h4 class="fw-bold mb-0">Quản lý sản phẩm</h4>
            <small class="text-muted">Danh sách các sản phẩm</small>
        </div>
        <div class="d-flex gap-2">
            <router-link to="/admin/add-product" class="btn btn-sm btn-primary">
                <i class="bi bi-plus-circle me-1"></i>Thêm sản phẩm
            </router-link>
        </div>
    </div>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <p class="mb-0 text-muted">
            {{ displayText }}
        </p>
        <div class="w-50"> <input type="text" class="form-control" v-model="keyword" placeholder="Nhập keyword" /></div>
        <div class="d-flex align-items-center">
            <select class="form-select" aria-label="Sắp xếp theo giá" v-model="option">
                <option :selected="option === 'default'" value="default">Mặc định</option>
                <option :selected="option === 'asc'" value="asc">Giá tăng dần</option>
                <option :selected="option === 'desc'" value="desc">Giá giảm dần</option>
            </select>

        </div>
    </div>
    <div class="card">
        <div class="card-body p-1">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th scope="col">Hình</th>
                            <th scope="col">Sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Cân nặng</th>
                            <th style="width: 10%;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="product in arrays" :key="product.id">
                            <td>
                                <div class="d-flex align-items-center">
                                    <img :src="product.image" alt="ảnh sản phẩm"
                                        style="width: 60px; height: 60px; object-fit: cover;" />

                                </div>
                            </td>
                            <td>
                                <div class="d-flex align-items-center">

                                    <div>
                                        <router-link :to="`/product-detail/${product.id}`" class="text-decoration-none">
                                            <p class="mb-0 small">{{ product.name }}</p>
                                        </router-link>
                                        <span class="badge bg-primary me-1" v-for="(cat, index) in product?.category"
                                            :key="index">{{
                                                cat.name }}</span>
                                    </div>
                                </div>
                            </td>
                            <td>{{ product.quantity }}</td>
                            <td>{{ formatPrice(product.price) }}</td>
                            <td>{{ product.weight }}</td>
                            <td>
                                <div class="d-flex gap-2">
                                    <router-link :to="`/admin/edit-product/${product.id}`" title="chỉnh sửa"
                                        class="btn btn-sm btn-outline-primary">
                                        <i class="bi bi-pencil"></i>
                                    </router-link>
                                    <button class="btn btn-sm btn-outline-danger" title="xóa">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div v-if="arrays.length > 0">
                <Pagination :currentPage="page" :totalPages="totalPages" @update:currentPage="page = $event" />
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import { computed, onMounted, ref, watch } from 'vue';
import axios from 'axios';
import Pagination from '../../components/common/Pagination.vue';
import { useRoute, useRouter } from 'vue-router';

interface Product {
    id: number;
    name: string;
    price: number;
    quantity: number;
    image: string;
    weight: number;
    isfavorite: boolean;
    category: [
        {
            id: number;
            name: string;
        }
    ];
    description: string;
    createAt: string;
}


const arrays = ref<Product[]>([]);
const totalPages = ref(0);
const totalItems = ref(0);
const limit = 12;

const router = useRouter();
const route = useRoute();

const keyword = ref(route.query.keyword?.toString() || '');
const page = ref(Number(route.query.page) || 1);
const option = ref(route.query.option?.toString() || 'default');
const products = async () => {
    try {
        const resp = await axios.get(`http://localhost:8080/products/search`, {
            params: {
                keyword: keyword.value || '',
                page: page.value !== 1 ? page.value : 1,
                option: option.value || 'default',
            },
        });
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
        console.warn('API trả về lỗi:', error);
    }
};

watch([keyword, page, option], () => {
    router.push({
        query: {
            keyword: keyword.value || '',
            page: page.value !== 1 ? page.value : 1,
            option: option.value || 'default',
        },
    });
    products();
});

onMounted(() => {
    products();
});

const displayText = computed(() => {
    if (totalItems.value === 0) return 'Không có sản phẩm';
    if (totalItems.value === 1) return 'Hiển thị 1 sản phẩm';

    const start = (page.value - 1) * limit + 1;
    const end = Math.min(start + arrays.value.length - 1, totalItems.value);

    if (totalItems.value === arrays.value.length)
        return `Hiển thị ${arrays.value.length} sản phẩm`;

    return `Hiển thị sản phẩm ${start} đến ${end} trên tổng ${totalItems.value} sản phẩm`;
});

function formatPrice(price: number) {
    return price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}
</script>
