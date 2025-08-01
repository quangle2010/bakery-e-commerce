<template>
  <div>
    <!-- Banner -->
    <div class="bg-primary bg-opacity-10 py-5 mb-5">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-6 text-md-start text-center mb-4 mb-md-0">
            <h1 class="fw-bold text-primary mb-3">Bánh Ngọt Tươi Mới Mỗi Ngày</h1>
            <p class="lead mb-4">Khám phá thế giới bánh ngọt thơm ngon với công thức đặc biệt từ đầu bếp hàng đầu của
              chúng tôi.</p>

          </div>
          <div class="col-md-6">
            <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a" class="img-fluid rounded-3 shadow"
              alt="Bánh ngọt">
          </div>
        </div>
      </div>
    </div>

    <!-- Danh mục sản phẩm -->
    <div class="container mb-5">
      <h2 class="text-center fw-bold mb-4">Danh Mục Sản Phẩm</h2>
      <div class="row g-4">
        <div class="col-md-3">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1557925923-cd4648e211a0" class="card-img-top" alt="Bánh kem">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Kem</h4>
              <p class="card-text">Bánh kem tươi ngon cho mọi dịp đặc biệt</p>

            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1603532648955-039310d9ed75" class="card-img-top"
              alt="Bánh ngọt">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Ngọt</h4>
              <p class="card-text">Các loại bánh ngọt thơm ngon hấp dẫn</p>

            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1587248720327-8eb72564be1e" class="card-img-top" alt="Bánh mì">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Mì</h4>
              <p class="card-text">Bánh mì tươi mới nướng mỗi ngày</p>

            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1587248720327-8eb72564be1e" class="card-img-top" alt="Bánh mì">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Mì</h4>
              <p class="card-text">Bánh mì tươi mới nướng mỗi ngày</p>

            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Sản phẩm nổi bật -->
    <div class="container mb-5">
      <h2 class="text-center fw-bold mb-4">Sản Phẩm</h2>
      <div class="row  row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
        <div class="col" v-for="product in arrays" :key="product.id">
          <CardProduct :product="product" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import CardProduct from '../../components/user/CardProduct.vue';
import axios from 'axios';
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
</script>
