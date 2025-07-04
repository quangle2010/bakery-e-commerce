<template>
    <div class="card mb-4  w-100">
        <div class="d-flex align-items-center p-4 bg-light border-bottom ">
            <h6 class="fw-bold">Danh sách đơn hàng</h6>
        </div>
        <div class="card-body p-3">
            <!-- Filter section -->
            <div class="row mb-3">
                <div class="col-md-12">
                    <div class="card bg-light border-0 shadow-sm">
                        <div class="card-body p-2">
                            <div class="d-flex align-items-center mb-2 small">
                                <i class="bi bi-funnel text-primary me-1"></i>
                                <span class="fw-semibold">Lọc đơn hàng</span>
                            </div>
                            <div class="row g-2">
                                <div class="col-md-4">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-text bg-white">Từ</span>
                                        <input type="date" class="form-control" id="fromDate">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-text bg-white">Đến</span>
                                        <input type="date" class="form-control" id="toDate">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <button class="btn btn-sm btn-primary w-100">
                                        <i class="bi bi-search me-1"></i>Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Status filter tabs -->
            <div class="nav nav-pills nav-fill mb-3 small">
                <a href="#all" class="nav-link active py-1 px-2">
                    <i class="bi bi-list-ul me-1"></i>Tất cả
                </a>
                <a href="#pending" class="nav-link py-1 px-2 text-warning">
                    <i class="bi bi-clock me-1"></i>Chờ xác nhận
                </a>
                <a href="#processing" class="nav-link py-1 px-2 text-info">
                    <i class="bi bi-gear me-1"></i>Đang xử lý
                </a>
                <a href="#shipped" class="nav-link py-1 px-2 text-primary">
                    <i class="bi bi-truck me-1"></i>Đang giao
                </a>
                <a href="#completed" class="nav-link py-1 px-2 text-success">
                    <i class="bi bi-check-circle me-1"></i>Đã giao
                </a>
                <a href="#cancelled" class="nav-link py-1 px-2 text-danger">
                    <i class="bi bi-x-circle me-1"></i>Đã hủy
                </a>
            </div>

            <!-- Orders list -->
            <!-- Order 1 -->
            <div class="card  card_item mb-4 overflow-hidden">
                <div class="card-header bg-white py-2 px-3 d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <div class="order-status-indicator bg-warning me-2"></div>
                        <div>
                            <div class="fw-semibold small">Mã đơn: #ORD12345</div>
                            <div class="d-flex align-items-center text-muted small">
                                <i class="bi bi-calendar3 me-1"></i>
                                <span>15/07/2023</span>
                                <span class="mx-1">•</span>
                                <i class="bi bi-person me-1"></i>
                                <span>Nguyễn Văn A</span>
                            </div>
                        </div>
                    </div>
                    <span class="badge bg-warning rounded-pill px-2 py-1 small">
                        <i class="bi bi-clock me-1"></i>Chờ xác nhận
                    </span>
                </div>
                <div class="card-body py-2 px-3">
                    <div class="row align-items-center">
                        <div class="col-7">
                            <div class="small">
                                <div class="mb-1">
                                    <span class="text-muted">Tổng tiền:</span>
                                    <span class="fw-bold text-danger ms-1">350.000₫</span>
                                </div>
                                <div class="text-muted">
                                    <i class="bi bi-box me-1"></i>2 sản phẩm
                                </div>
                            </div>
                        </div>
                        <div class="col-5 text-end">
                            <button class="btn btn-sm btn-outline-danger me-1">
                                <i class="bi bi-x-circle me-1"></i>Hủy
                            </button>
                            <router-link  to="/admin/order/1" class="btn btn-sm btn-primary me-1">
                                <i class="bi bi-eye me-1"></i>Chi tiết
                            </router-link>
                            <button class="btn btn-sm btn-outline-success">
                                <i class="bi bi-check-circle me-1"></i>Chuyển sang trạng thái
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Order 2 -->
            <div class="card card_item mb-4 overflow-hidden">
                <div class="card-header bg-white py-2 px-3 d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <div class="order-status-indicator bg-primary me-2"></div>
                        <div>
                            <div class="fw-semibold small">Mã đơn: #ORD12346</div>
                            <div class="d-flex align-items-center text-muted small">
                                <i class="bi bi-calendar3 me-1"></i>
                                <span>10/07/2023</span>
                                <span class="mx-1">•</span>
                                <i class="bi bi-person me-1"></i>
                                <span>Trần Thị B</span>
                            </div>
                        </div>
                    </div>
                    <span class="badge bg-primary rounded-pill px-2 py-1 small">
                        <i class="bi bi-truck me-1"></i>Đang giao
                    </span>
                </div>
                <div class="card-body py-2 px-3">
                    <div class="row align-items-center">
                        <div class="col-7">
                            <div class="small">
                                <div class="mb-1">
                                    <span class="text-muted">Tổng tiền:</span>
                                    <span class="fw-bold text-danger ms-1">280.000₫</span>
                                </div>
                                <div class="text-muted">
                                    <i class="bi bi-box me-1"></i>1 sản phẩm
                                </div>
                            </div>
                        </div>
                        <div class="col-5 text-end">
                            <a th:href="@{/user/order-detail}" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye me-1"></i>Chi tiết
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Order 3 -->
            <div class="card  card_item mb-4 overflow-hidden">
                <div class="card-header bg-white py-2 px-3 d-flex justify-content-between align-items-center">
                    <div class="d-flex align-items-center">
                        <div class="order-status-indicator bg-success me-2"></div>
                        <div>
                            <div class="fw-semibold small">Mã đơn: #ORD12340</div>
                            <div class="d-flex align-items-center text-muted small">
                                <i class="bi bi-calendar3 me-1"></i>
                                <span>05/07/2023</span>
                                <span class="mx-1">•</span>
                                <i class="bi bi-person me-1"></i>
                                <span>Lê Văn C</span>
                            </div>
                        </div>
                    </div>
                    <span class="badge bg-success rounded-pill px-2 py-1 small">
                        <i class="bi bi-check-circle me-1"></i>Đã giao
                    </span>
                </div>
                <div class="card-body py-2 px-3">
                    <div class="row align-items-center">
                        <div class="col-7">
                            <div class="small">
                                <div class="mb-1">
                                    <span class="text-muted">Tổng tiền:</span>
                                    <span class="fw-bold text-danger ms-1">180.000₫</span>
                                </div>
                                <div class="text-muted">
                                    <i class="bi bi-box me-1"></i>6 sản phẩm
                                </div>
                            </div>
                        </div>
                        <div class="col-5 text-end">
                            <a th:href="@{/user/order-detail}" class="btn btn-sm btn-primary">
                                <i class="bi bi-eye me-1"></i>Chi tiết
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup></script>

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

.order-status-indicator {
    width: 3px;
    height: 30px;
    border-radius: 1px;
}

.nav-pills .nav-link {
    border-radius: 4px;
    font-weight: 500;
    font-size: 0.85rem;
    margin: 0 2px;
}

.nav-pills .nav-link.active {
    background-color: var(--bs-primary);
}
</style>