<template>
    <div class="card mb-4  w-100 h-100">
        <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom ">
            <div class="fw-bold">Danh sách sản phẩm yêu thích {{ displayText }}</div>
        </div>
        <div class="card-body">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-2 row-cols-lg-3 g-2">
                <!-- Sản phẩm 1 -->
                <div class="col" v-for="product in arrays" :key="product.id">
                    <CardProduct :product="product" />
                </div>
            </div>
          
        </div>
          <div v-if="arrays.length > 0">
                <Pagination :currentPage="page" :totalPages="totalPages" @update:currentPage="page = $event" />
            </div>
    </div>
</template>
<script lang="ts" setup>
import { computed, onMounted, ref, watch } from 'vue';
import CardProduct from '../../components/user/CardProduct.vue';
import { useRoute, useRouter } from 'vue-router';
import axiosClient from '../../util/axiosClient';
import Pagination from '../../components/common/Pagination.vue';

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

const products = async () => {
    try {
        const resp = await axiosClient.get(`/user/favorites`, {
            params: {
                page: page.value,
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

watch([keyword, page], () => {
    router.push({
        query: {
            page: page.value !== 1 ? page.value : 1,
        },
    });
    products();
});
const displayText = computed(() => {
    if (totalItems.value === 0) return 'Không có sản phẩm nào được yêu thích';
    if (totalItems.value === 1) return 'Hiển thị 1 sản phẩm';

    const start = (page.value - 1) * limit + 1;
    const end = Math.min(start + arrays.value.length - 1, totalItems.value);

    if (totalItems.value === arrays.value.length)
        return `Hiển thị ${arrays.value.length} sản phẩm`;

    return `Hiển thị sản phẩm ${start} đến ${end} trên tổng ${totalItems.value} sản phẩm`;
});
onMounted(() => {
    products();
});
</script>