<template>
    <div class="row">
        <!-- Product Images and Info -->
        <div class="row mb-2">
            <!-- Product Images -->
            <div class="col-md-5">
                <div class="product-image-container border rounded p-2 bg-white mb-3">
                    <img src="https://images.unsplash.com/photo-1578985545062-69928b1d9587" class="img-fluid rounded"
                        alt="Hình ảnh sản phẩm">
                </div>
            </div>

            <!-- Product Info -->
            <div class="col-md-7">
                <h2 class="mb-2 fw-bold">Bánh kem Chocolate Delight</h2>
                <div class="mb-3">
                    <span class="badge bg-primary me-1">SweetieChocolate</span>
                </div>

                <div class="mb-4">
                    <h3 class="text-primary fw-bold">20.490.000₫</h3>
                </div>

                <div class="mb-4">
                    <div class="d-flex align-items-center">
                        <div class="me-3">
                            <label class="form-label fw-bold mb-2">Số lượng</label>
                            <div class="input-group" style="width: 120px;">
                                <button class="btn btn-outline-primary" type="button">-</button>
                                <input type="number" class="form-control text-center fw-bold" value="1" min="1">
                                <button class="btn btn-outline-primary" type="button">+</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="d-flex flex-wrap gap-2 mb-4">
                    <button class="btn btn-primary px-4 py-2">
                        <i class="bi bi-cart me-2"></i>Thêm vào giỏ hàng
                    </button>
                    <button class="btn btn-outline-danger px-4 py-2">
                        <i class="bi bi-heart me-2"></i>Yêu thích
                    </button>
                </div>

            </div>
        </div>
        <!-- Product Details Accordion -->
        <div class="row mb-5">
            <div class="col-12">
                <div class="accordion" id="productAccordion">
                    <!-- Chi tiết sản phẩm -->
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
                                <h4>Bánh kem Chocolate Delight</h4>
                                <p>Bánh kem Chocolate Delight là một trong những sản phẩm nổi bật của tiệm bánh Sweetie,
                                    được làm từ những nguyên liệu tươi ngon nhất.</p>

                                <h5>Đặc điểm nổi bật:</h5>
                                <ul>
                                    <li>Lớp bánh bông lan mềm mịn, xốp nhẹ</li>
                                    <li>Lớp kem tươi mát, không quá ngọt</li>
                                    <li>Trang trí với dâu tây tươi và socola cao cấp</li>
                                    <li>Không sử dụng chất bảo quản</li>
                                    <li>Thích hợp cho 6-8 người dùng</li>
                                </ul>

                                <h5>Hướng dẫn bảo quản:</h5>
                                <p>Bảo quản trong tủ lạnh ở nhiệt độ 2-5°C. Nên sử dụng trong vòng 48 giờ sau khi mua để
                                    đảm bảo hương vị tốt nhất.</p>

                                <h5>Lưu ý:</h5>
                                <p>Sản phẩm có thể chứa các thành phần gây dị ứng như: trứng, sữa, gluten, hạt. Vui lòng
                                    kiểm tra kỹ thành phần trước khi sử dụng.</p>
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <th scope="row" width="30%">Tên sản phẩm</th>
                                            <td>Bánh kem Chocolate Delight</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Thương hiệu</th>
                                            <td>Sweetie</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Khối lượng</th>
                                            <td>500g</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Kích thước</th>
                                            <td>15cm / 20cm / 25cm</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Thành phần chính</th>
                                            <td>Bột mì, trứng, đường, sữa, kem tươi, dâu tây, socola</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Hạn sử dụng</th>
                                            <td>3 ngày kể từ ngày sản xuất (bảo quản lạnh)</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Xuất xứ</th>
                                            <td>Việt Nam</td>
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
</template>

<script lang="ts" setup>
// Chỉ giao diện mẫu, không có logic
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