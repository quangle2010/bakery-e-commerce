<template>
 <div class="card mb-4 border w-100">
    <div class="d-flex align-items-center p-3 bg-light border-bottom justify-content-between">
        <h6 class="mb-0 fw-bold">Chi tiết đơn hàng #ORD12345</h6>
        <span class="badge bg-warning text-dark">
            <i class="bi bi-clock me-1"></i>Chờ xác nhận
        </span>
    </div>
    <div class="card-body flex-grow-1">
        <!-- Thông tin đơn hàng -->
        <div class="row mb-4">
            <div class="col-md-6 mb-3">
                <div class="card h-100">
                    <div class="card-header bg-light py-2">
                        <h6 class="mb-0 small">Thông tin người đặt</h6>
                    </div>
                    <div class="card-body">
                        <p class="mb-1 small"><strong>Họ tên:</strong> Nguyễn Văn A</p>
                        <p class="mb-1 small"><strong>Số điện thoại:</strong> 0912345678</p>
                        <p class="mb-0 small"><strong>Địa chỉ:</strong> Số 123, Đường ABC, Phường XYZ, Quận 1, TP Hồ Chí Minh</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card h-100">
                    <div class="card-header bg-light py-2">
                        <h6 class="mb-0 small">Thông tin đơn hàng</h6>
                    </div>
                    <div class="card-body">
                        <p class="mb-1 small"><strong>Ngày đặt hàng:</strong> 15/07/2023 14:30</p>
                        <p class="mb-1 small"><strong>Trạng thái:</strong> 
                            <span class="badge bg-warning text-dark">
                                <i class="bi bi-clock me-1"></i>Chờ xác nhận
                            </span>
                        </p>
                        <p class="mb-1 small"><strong>Phương thức thanh toán:</strong> Thanh toán khi nhận hàng</p>
                        <p class="mb-0 small"><strong>Trạng thái thanh toán:</strong> 
                            <span class="badge bg-danger">Chưa thanh toán</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Danh sách sản phẩm -->
        <div class="card border mb-4">
            <div class="card-header bg-light py-2">
                <h6 class="mb-0 small">Danh sách sản phẩm</h6>
            </div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table mb-0 table-hover">
                        <thead class="table-light">
                            <tr>
                                <th scope="col" class="ps-3">Sản phẩm</th>
                                <th scope="col" class="text-center">Giá</th>
                                <th scope="col" class="text-center">Số lượng</th>
                                <th scope="col" class="text-end pe-3">Thành tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="ps-3">
                                    <div class="d-flex align-items-center">
                                        <img src="https://picsum.photos/seed/2/300/200" alt="Bánh kem" class="me-2"
                                            style="width: 40px; height: 40px; object-fit: cover;" />
                                        <div>
                                            <p class="mb-0 small">Bánh kem socola </p>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center align-middle small">150.000₫</td>
                                <td class="text-center align-middle small">1</td>
                                <td class="text-end pe-3 align-middle fw-bold small">150.000₫</td>
                            </tr>
                            <tr>
                                <td class="ps-3">
                                    <div class="d-flex align-items-center">
                                        <img src="https://picsum.photos/seed/2/300/200" alt="Bánh kem" class="me-2"
                                            style="width: 40px; height: 40px; object-fit: cover;" />
                                        <div>
                                            <p class="mb-0 small">Bánh kem dâu tây </p>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center align-middle small">200.000₫</td>
                                <td class="text-center align-middle small">1</td>
                                <td class="text-end pe-3 align-middle fw-bold small">200.000₫</td>
                            </tr>
                            <tr class="table-light">
                                <td colspan="3" class="text-end fw-bold pe-3">Tổng cộng:</td>
                                <td class="text-end pe-3 fw-bold text-danger">350.000₫</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Nút thao tác -->
        <div class="d-flex justify-content-end gap-2">
            <router-link to="/admin/orders" class="btn btn-sm btn-outline-secondary">
                <i class="bi bi-arrow-left me-1"></i>Quay lại
            </router-link>
            <button class="btn btn-sm btn-outline-danger">
                <i class="bi bi-x-circle me-1"></i>Hủy đơn hàng
            </button>
        </div>
    </div>
</div>

</template>
<script lang="ts" setup>

</script>