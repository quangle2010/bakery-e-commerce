<template>
    <div class="card h-100 product-card ">
        <img :src="product.image" class="card-img-top" alt="Cookies Chocolate"
                style="height: 200px; object-fit: cover;">
        <div class="card-body">
            <h5 class="card-title product_name mb-0" :title="product.name">{{ product.name }}</h5>
            <span class="badge bg-primary my-1">{{ product.category }}</span>
            <p class="card-text text-danger fw-bold fs-5">
              {{ formatPrice(product.price) }}
            </p>
        </div>
        <div class="card-footer bg-white border-top-0 p-3">
            <div class="d-flex gap-2">
                <button class="btn btn-outline-primary flex-grow-1" title="Xem chi tiết sản phẩm">
                    <i class="bi bi-info-circle me-1"></i> Chi tiết
                </button>
                <button class="btn btn-outline-danger btn-icon" title="Xóa khỏi yêu thích"  v-if="product.isfavorite">
                    <i class="bi bi-heartbreak "></i>
                </button>
                <button class="btn btn-outline-danger btn-icon" title="Thêm vào yêu thích" v-else>
                    <i class="bi bi-heart"></i>
                </button>
                <button class="btn btn-primary flex-grow-1" title="Thêm vào giỏ hàng">
                    <i class="bi bi-cart-plus me-1"></i> Thêm
                </button>
            </div>
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
.product-card {
    transition: all 0.3s ease;
    overflow: hidden;
}

.product-card:hover {
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

.btn-icon {
    width: 40px;
    height: 40px;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
}

.btn {
    transition: all 0.2s ease;
}

.btn:hover {
    transform: translateY(-2px);
}
</style>