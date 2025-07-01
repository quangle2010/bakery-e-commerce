<template>
    <div class="payment-page container py-4">
        <h4 class="mb-4 fw-bold text-primary">Thanh toán đơn hàng</h4>
        
        <div class="row g-4">
            <div class="col-lg-8">
                <!-- Thông tin sản phẩm -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-bag-check me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Thông tin sản phẩm</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <!-- Item 1 -->
                        <div class="product-item d-flex mb-4 pb-4 border-bottom">
                            <div class="product-img position-relative">
                                <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a" class="rounded-3 me-3" style="width: 100px; height: 100px; object-fit: cover;" alt="Sản phẩm">
                                <span class="badge bg-primary position-absolute top-0 start-0 m-2">Mới</span>
                            </div>
                            <div class="flex-grow-1 d-flex flex-column justify-content-between">
                                <div>
                                    <div class="d-flex justify-content-between align-items-start">
                                        <h6 class="mb-1 fw-bold">Bánh Croissant</h6>
                                        <button class="btn btn-sm text-danger border-0 p-0" title="Xóa sản phẩm">
                                            <i class="bi bi-x-lg"></i>
                                        </button>
                                    </div>
                                    <p class="mb-2 text-muted small">Loại: Bánh ngọt Pháp</p>
                                </div>
                                <div class="d-flex justify-content-between align-items-end">
                                    <div class="d-flex align-items-center">
                                        <span class="me-2 text-muted small">Số lượng:</span>
                                        <div class="input-group input-group-sm quantity-control" style="width: 110px;">
                                            <button class="btn btn-outline-primary rounded-start" type="button">
                                                <i class="bi bi-dash"></i>
                                            </button>
                                            <input type="text" class="form-control text-center bg-white" value="2" readonly>
                                            <button class="btn btn-outline-primary rounded-end" type="button">
                                                <i class="bi bi-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <p class="mb-0 fw-bold text-primary fs-5">35.000 ₫</p>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Item 2 -->
                        <div class="product-item d-flex mb-3">
                            <div class="product-img position-relative">
                                <img src="https://images.unsplash.com/photo-1571877227200-a0d98ea607e9" class="rounded-3 me-3" style="width: 100px; height: 100px; object-fit: cover;" alt="Sản phẩm">
                                <span class="badge bg-danger position-absolute top-0 start-0 m-2">-10%</span>
                            </div>
                            <div class="flex-grow-1 d-flex flex-column justify-content-between">
                                <div>
                                    <div class="d-flex justify-content-between align-items-start">
                                        <h6 class="mb-1 fw-bold">Bánh Tiramisu</h6>
                                        <button class="btn btn-sm text-danger border-0 p-0" title="Xóa sản phẩm">
                                            <i class="bi bi-x-lg"></i>
                                        </button>
                                    </div>
                                    <p class="mb-2 text-muted small">Loại: Bánh Ý</p>
                                </div>
                                <div class="d-flex justify-content-between align-items-end">
                                    <div class="d-flex align-items-center">
                                        <span class="me-2 text-muted small">Số lượng:</span>
                                        <div class="input-group input-group-sm quantity-control" style="width: 110px;">
                                            <button class="btn btn-outline-primary rounded-start" type="button">
                                                <i class="bi bi-dash"></i>
                                            </button>
                                            <input type="text" class="form-control text-center bg-white" value="1" readonly>
                                            <button class="btn btn-outline-primary rounded-end" type="button">
                                                <i class="bi bi-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="text-end">
                                        <p class="mb-0 text-decoration-line-through text-muted small">50.000 ₫</p>
                                        <p class="mb-0 fw-bold text-primary fs-5">45.000 ₫</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Tổng tiền -->
                        <div class="fee-details mt-4 pt-3 border-top">
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted">Tạm tính:</span>
                                <span>115.000 ₫</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted">Phí vận chuyển:</span>
                                <span>30.000 ₫</span>
                            </div>
                            <div class="d-flex justify-content-between fw-bold mt-3 pt-3 border-top">
                                <span>Tổng thanh toán:</span>
                                <span class="text-danger fs-4">140.000 ₫</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-4">
                <!-- Địa chỉ nhận hàng -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-geo-alt me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Địa chỉ nhận hàng</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <div class="mb-3 p-3 border rounded-3 bg-light">
                            <p class="mb-1 fw-bold">Nguyễn Văn A</p>
                            <p class="mb-1"><i class="bi bi-telephone me-2 text-primary"></i>0987654321</p>
                            <p class="mb-0"><i class="bi bi-house me-2 text-primary"></i>123 Đường Lê Lợi, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh</p>
                        </div>
                        <div class="d-grid">
                            <button class="btn btn-outline-primary d-flex align-items-center justify-content-center">
                                <i class="bi bi-pencil me-2"></i>Thay đổi địa chỉ
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Phương thức thanh toán -->
                <div class="card mb-4 border-0 shadow-sm">
                    <div class="card-header bg-white border-bottom border-2 border-primary py-3">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-credit-card me-2 text-primary"></i>
                            <h5 class="mb-0 fw-bold">Phương thức thanh toán</h5>
                        </div>
                    </div>
                    <div class="card-body p-4">
                        <div class="payment-methods">
                            <div class="form-check payment-method-item mb-3 p-3 border rounded-3">
                                <input class="form-check-input" type="radio" name="paymentMethod" id="cod" value="cod" checked>
                                <label class="form-check-label d-flex align-items-center" for="cod">
                                    <span class="payment-icon me-3 bg-warning text-white rounded-circle d-flex align-items-center justify-content-center" style="width: 40px; height: 40px;">
                                        <i class="bi bi-cash"></i>
                                    </span>
                                    <div>
                                        <span class="d-block fw-bold">Thanh toán khi nhận hàng (COD)</span>
                                        <small class="text-muted">Thanh toán bằng tiền mặt khi nhận hàng</small>
                                    </div>
                                </label>
                            </div>
                            
                            <div class="form-check payment-method-item p-3 border rounded-3">
                                <input class="form-check-input" type="radio" name="paymentMethod" id="banking" value="banking">
                                <label class="form-check-label d-flex align-items-center" for="banking">
                                    <span class="payment-icon me-3 bg-primary text-white rounded-circle d-flex align-items-center justify-content-center" style="width: 40px; height: 40px;">
                                        <i class="bi bi-bank"></i>
                                    </span>
                                    <div>
                                        <span class="d-block fw-bold">Chuyển khoản ngân hàng</span>
                                        <small class="text-muted">Thanh toán qua tài khoản ngân hàng</small>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Nút đặt hàng -->
                <div class="d-flex gap-2">
                 <button class="btn btn-sm btn-outline-secondary rounded-3 flex-grow-1">
                        <i class="bi bi-arrow-left me-2"></i>Quay lại giỏ hàng
                    </button>
                    <button class="btn btn-primary btn-sm py-3 rounded-3 fw-bold flex-grow-1">
                        <i class="bi bi-check2-circle me-2"></i>Xác nhận đặt hàng
                    </button>
                   
                </div>
            </div>
        </div>

       
    </div>
</template>

<script setup lang="ts">
// Không có logic, chỉ có giao diện
</script>

<style scoped>
.card {
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
    overflow: hidden;
}

.card-header {
    background-color: #fff;
}

.product-item:last-child {
    border-bottom: none !important;
    padding-bottom: 0 !important;
    margin-bottom: 0 !important;
}

.quantity-control .form-control {
    border-left: 0;
    border-right: 0;
}

.quantity-control .btn {
    min-width: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.payment-method-item {
    cursor: pointer;
    transition: all 0.2s;
}

.payment-method-item:hover {
    background-color: #f8f9fa;
}

.form-check-input:checked ~ .form-check-label .payment-icon {
    transform: scale(1.1);
}

.payment-icon {
    transition: all 0.2s;
}

.btn {
    transition: all 0.3s;
}

.btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn:active {
    transform: translateY(0);
}
</style>