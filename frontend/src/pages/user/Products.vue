<template>
    <div class="products-page">
        <div class="container">
            <!-- Bộ lọc và Sản phẩm -->
            <div class="row">

                <!-- Danh sách sản phẩm -->
                <div class="col">
                    <!-- Hiển thị số lượng sản phẩm và kết quả tìm kiếm -->
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <p class="mb-0 text-muted">Hiển thị <span class="fw-bold">6</span> sản phẩm</p>
                        <div class="d-flex align-items-center">
                            <!-- Nút lọc mới -->
                            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal"
                                data-bs-target="#filterModal">
                                <i class="bi bi-funnel-fill me-1"></i> Lọc
                            </button>

                        </div>
                    </div>

                    <!-- Modal lọc sản phẩm -->
                    <div class="modal fade" id="filterModal" tabindex="-1" aria-labelledby="filterModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="filterModalLabel">Lọc sản phẩm</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Danh mục sản phẩm - nằm dọc -->
                                    <div class="mb-4">
                                        <h6 class="fw-bold">Danh mục</h6>
                                        <div class="d-flex flex-column">
                                            <div class="form-check mb-2">
                                                <input class="form-check-input" type="checkbox" value="" id="cat-all"
                                                    checked>
                                                <label class="form-check-label" for="cat-all">
                                                    Tất cả
                                                </label>
                                            </div>
                                            <div class="form-check mb-2">
                                                <input class="form-check-input" type="checkbox" value="" id="cat-bread">
                                                <label class="form-check-label" for="cat-bread">
                                                    Bánh mì
                                                </label>
                                            </div>
                                            <div class="form-check mb-2">
                                                <input class="form-check-input" type="checkbox" value="" id="cat-cake">
                                                <label class="form-check-label" for="cat-cake">
                                                    Bánh ngọt
                                                </label>
                                            </div>
                                            <div class="form-check mb-2">
                                                <input class="form-check-input" type="checkbox" value=""
                                                    id="cat-pastry">
                                                <label class="form-check-label" for="cat-pastry">
                                                    Bánh ngọt Pháp
                                                </label>
                                            </div>
                                            <div class="form-check mb-2">
                                                <input class="form-check-input" type="checkbox" value=""
                                                    id="cat-cookies">
                                                <label class="form-check-label" for="cat-cookies">
                                                    Bánh quy
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Sắp xếp theo giá - combobox -->
                                    <div class="mb-4">
                                        <h6 class="fw-bold">Sắp xếp theo giá</h6>
                                        <select class="form-select" aria-label="Sắp xếp theo giá">
                                            <option selected value="default">Mặc định</option>
                                            <option value="asc">Giá tăng dần</option>
                                            <option value="desc">Giá giảm dần</option>
                                            <option value="newest">Mới nhất</option>
                                            <option value="popular">Phổ biến nhất</option>
                                        </select>
                                    </div>

                                    <!-- Khoảng giá - radio -->
                                    <div class="mb-4">
                                        <h6 class="fw-bold">Khoảng giá</h6>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange"
                                                id="price-all" checked>
                                            <label class="form-check-label" for="price-all">
                                                Tất cả giá
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange"
                                                id="price-under-50">
                                            <label class="form-check-label" for="price-under-50">
                                                Dưới 50.000₫
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange"
                                                id="price-50-100">
                                            <label class="form-check-label" for="price-50-100">
                                                50.000₫ - 100.000₫
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange"
                                                id="price-100-200">
                                            <label class="form-check-label" for="price-100-200">
                                                100.000₫ - 200.000₫
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange"
                                                id="price-over-200">
                                            <label class="form-check-label" for="price-over-200">
                                                Trên 200.000₫
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Đóng</button>
                                    <button type="button" class="btn btn-primary">Áp dụng</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Hiển thị sản phẩm dạng lưới -->
                    <div class="row  row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-2">
                        <!-- Sản phẩm 1 -->
                        <div class="col" v-for="product in products" :key="product.id">
                            <CardProduct :product="product" />
                        </div>
                    </div>

                    <!-- Phân trang -->
                    <div class="d-flex justify-content-center mt-5">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1">
                                        <i class="bi bi-chevron-left"></i>
                                    </a>
                                </li>
                                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#">
                                        <i class="bi bi-chevron-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
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
        isfavorite: false, 
        description: 'Bánh Croissant thơm ngon, giòn xốp với hương bơ đặc trưng.'
    },
    { 
        id: 2, 
        name: 'Bánh Mì Đặc Biệt', 
        price: 35000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/2/300/200', 
        category: 'bread', 
        isfavorite: false, 
        description: 'Bánh Mì Đặc Biệt với nhân thịt và rau củ tươi ngon.'
    },
    { 
        id: 3, 
        name: 'Bánh Kem Dâu', 
        price: 320000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/3/300/200', 
        category: 'cake', 
        isfavorite: false, 
        description: 'Bánh Kem Dâu với lớp kem tươi mát và dâu tươi ngon.'
    },
    { 
        id: 4, 
        name: 'Cookies Chocolate', 
        price: 25000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/4/300/200', 
        category: 'cake', 
        isfavorite: false, 
        description: 'Bánh Cookies Chocolate giòn tan với nhiều miếng chocolate đắng.'
    },
    { 
        id: 5, 
        name: 'Bánh Cupcake Vanilla', 
        price: 30000, 
        quantity: 1,
        image: 'https://picsum.photos/seed/5/300/200', 
        category: 'cake', 
        isfavorite: false, 
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