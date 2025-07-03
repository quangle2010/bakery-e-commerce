<template>
    <div class="card h-100 card_item" @click="$router.push(`/product-detail/${product.id}`)">
        <img :src="product.image" class="card-img-top" alt="Cookies Chocolate"
                style="height: 200px; object-fit: cover;">
        <div class="card-body">
            <h5 class="card-title product_name mb-0" :title="product.name">{{ product.name }}</h5>
            <span class="badge bg-primary my-1">{{ product.category }}</span>
            <p class="card-text text-danger fw-bold fs-5">
              {{ formatPrice(product.price) }}
            </p>
        </div>
    </div>
</template>

<script lang="ts" setup>
defineProps<{
    product: {
        id: number;
        name: string;
        price: number;
        quantity: number;
        description: string;
        image: string;
        isfavorite: boolean;
        category: string;
    };
}>();
const formatPrice = (price: number) =>
    price.toLocaleString("vi-VN", { style: "currency", currency: "VND" });

</script>

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


.product_name {
    display: -webkit-box;
    -webkit-line-clamp: 2;
    line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
    height: 48px;
}
</style>