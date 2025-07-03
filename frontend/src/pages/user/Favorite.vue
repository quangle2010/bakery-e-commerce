<template>
    <div class="card mb-4  w-100">
        <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom ">
            <div class="fw-bold">Danh sách sản phẩm yêu thích</div>
        </div>
        <div class="card-body">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-2 row-cols-lg-3 g-2">
                <!-- Sản phẩm 1 -->
                <div class="col" v-for="product in products" :key="product.id">
                    <CardProduct :product="product" />
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
import CardProduct from '../../components/user/CardProduct.vue';
const products = ref([
    { 
        id: 1, 
        name: 'Bánh Croissant', 
        price: 45000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/1/300/200', 
        category: 'bread', 
        isfavorite: true, 
        description: 'Bánh Croissant thơm ngon, giòn xốp với hương bơ đặc trưng.'
    },
    { 
        id: 2, 
        name: 'Bánh Mì Đặc Biệt', 
        price: 35000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/2/300/200', 
        category: 'bread', 
        isfavorite: true, 
        description: 'Bánh Mì Đặc Biệt với nhân thịt và rau củ tươi ngon.'
    },
    { 
        id: 3, 
        name: 'Bánh Kem Dâu', 
        price: 320000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/3/300/200', 
        category: 'cake', 
        isfavorite: true, 
        description: 'Bánh Kem Dâu với lớp kem tươi mát và dâu tươi ngon.'
    },
    { 
        id: 4, 
        name: 'Cookies Chocolate', 
        price: 25000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/4/300/200', 
        category: 'cake', 
        isfavorite: true, 
        description: 'Bánh Cookies Chocolate giòn tan với nhiều miếng chocolate đắng.'
    },
    { 
        id: 5, 
        name: 'Bánh Cupcake Vanilla', 
        price: 30000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/5/300/200', 
        category: 'cake', 
        isfavorite: true, 
        description: 'Bánh Cupcake Vanilla với lớp kem tươi mát và hương vani thơm ngon.'
    },
    { 
        id: 6, 
        name: 'Bánh Tiramisu', 
        price: 55000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/6/300/200', 
        category: 'cake', 
        isfavorite: true, 
        description: 'Bánh Tiramisu thơm ngon với vị cà phê đậm đà.'
    }
]);
</script>