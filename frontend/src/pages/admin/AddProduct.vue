<script setup lang="ts"></script>
<template>
    <div class="card">
        <div class="card-header bg-white d-flex justify-content-between align-items-center">
            <h3>Thêm sản phẩm</h3>
        </div>
        <div class="card-body">
            <form method="post" action="#" enctype="multipart/form-data" class="needs-validation" novalidate>
                <div class="row g-3">
                    <!-- Thông tin sản phẩm -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="productName" class="form-label">Tên sản phẩm <span
                                    class="text-danger">*</span></label>
                            <input type="text" class="form-control" id="productName" name="name" required>
                        </div>

                        <div class="mb-3">
                            <label for="productCategory" class="form-label">Danh mục <span
                                    class="text-danger">*</span></label>
                            <select class="form-select" id="productCategory" name="category" required>
                                <option value="" selected disabled>Chọn danh mục</option>
                                <option value="cake">Bánh ngọt</option>
                                <option value="cookie">Bánh quy</option>
                                <option value="mooncake">Bánh trung thu</option>
                                <option value="candy">Kẹo</option>
                                <option value="snack">Snack</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="productImage" class="form-label">Hình ảnh <span
                                    class="text-danger">*</span></label>
                            <input type="file" class="form-control" id="productImage" name="image" accept="image/*"
                                required>
                        </div>


                    </div>

                    <!-- Giá, số lượng và hình ảnh -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="productPrice" class="form-label">Giá bán (VNĐ) <span
                                    class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="productPrice" name="price" min="0"
                                    required>
                                <span class="input-group-text">₫</span>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="productQuantity" class="form-label">Số lượng <span
                                    class="text-danger">*</span></label>
                            <input type="number" class="form-control" id="productQuantity" name="quantity" min="0"
                                value="0" required>
                        </div>




                    </div>

                    <!-- Mô tả -->
                    <div class="col-12">
                        <div class="mb-3">
                            <label for="productDescription" class="form-label">Mô tả sản phẩm</label>
                            <textarea class="form-control" id="productDescription" name="description"
                                rows="3"></textarea>
                        </div>
                    </div>


                </div>

                <div class="d-flex justify-content-end gap-2 mt-4">
                    <router-link to="/admin/products" class="btn btn-outline-secondary">Quay lại</router-link>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</template>