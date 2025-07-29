<template>
    <div class="products-page">
        <div class="container">
            <!-- Bộ lọc và Sản phẩm -->
            <div class="row">

                <!-- Danh sách sản phẩm -->
                <div class="col">
                    <!-- Hiển thị số lượng sản phẩm và kết quả tìm kiếm -->
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <p class="mb-0 text-muted">
                            {{ displayText }}
                        </p>
                        <div class="w-50"> <input type="text" class="form-control" v-model="keyword"
                                placeholder="Nhập keyword" /></div>
                        <div class="d-flex align-items-center">
                            <select class="form-select" aria-label="Sắp xếp theo giá" v-model="option">
                                <option :selected="option === 'default'" value="default">Mặc định</option>
                                <option :selected="option === 'asc'" value="asc">Giá tăng dần</option>
                                <option :selected="option === 'desc'" value="desc">Giá giảm dần</option>
                            </select>

                        </div>
                    </div>



                    <div class="row  row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
                        <div class="col" v-for="product in arrays" :key="product.id">
                            <CardProduct :product="product" />
                        </div>
                    </div>

                  
                </div>
               <div v-if="arrays.length>0" >
                   <Pagination  :currentPage="page" :totalPages="totalPages" @update:currentPage="page = $event" />
               </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref, watch } from 'vue';
import CardProduct from '../../components/user/CardProduct.vue';
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
    category: string;
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
</script>


<style scoped>
.banner-section {
    background-color: #f8f9fa;
    padding: 3rem 0;
    border-radius: 0.5rem;
}

.product-card {
    transition: all 0.3s ease;
    overflow: hidden;
}

.product-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.product-overlay {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.3);
    opacity: 0;
    transition: all 0.3s ease;
}

.product-card:hover .product-overlay {
    opacity: 1;
}

.product-overlay button {
    margin: 0 5px;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    transform: translateY(20px);
    transition: all 0.3s ease;
}

.product-card:hover .product-overlay button {
    transform: translateY(0);
}

.ratings {
    display: inline-flex;
}

.pagination .page-link {
    color: #0d6efd;
    border-radius: 0.25rem;
    margin: 0 3px;
}

.pagination .active .page-link {
    background-color: #0d6efd;
    border-color: #0d6efd;
    color: white;
}

/* Dropdown sắp xếp */
.sort-dropdown {
    background-color: #f8f9fa;
    border-radius: 20px;
    padding: 5px 15px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
}

.sort-dropdown select {
    font-size: 0.9rem;
    font-weight: 500;
    cursor: pointer;
    background-color: transparent;
}

.sort-dropdown select:focus {
    box-shadow: none;
    outline: none;
}

/* Responsive adjustments */
@media (max-width: 767.98px) {
    .banner-section {
        padding: 2rem 0;
    }

    .search-filter-section {
        padding: 1rem;
    }
}
</style>