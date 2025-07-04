<template>
    <div class="row">
        <div class="col-md-6 mb-4">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-header bg-light py-3 border-bottom">
                    <h6 class="fw-bold mb-0"><i class="bi bi-person-vcard me-2"></i>Thông tin cá nhân</h6>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Họ và tên</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-person text-primary"></i>
                                </span>
                                <input type="text" class="form-control border-start-0" placeholder="Họ và tên">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Email</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-envelope text-primary"></i>
                                </span>
                                <input type="email" class="form-control border-start-0" placeholder="Email">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Số điện thoại</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-telephone text-primary"></i>
                                </span>
                                <input type="tel" class="form-control border-start-0" placeholder="Số điện thoại">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Địa chỉ</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-geo-alt text-primary"></i>
                                </span>
                                <input type="text" class="form-control border-start-0" placeholder="Địa chỉ">
                            </div>
                        </div>

                        <button type="button" class="btn btn-primary shadow-sm">
                            <i class="bi bi-save me-2"></i>
                            Lưu thông tin
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6 mb-4">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-header bg-light py-3 border-bottom">
                    <h6 class="fw-bold mb-0"><i class="bi bi-shield-lock me-2"></i>Đổi mật khẩu</h6>
                </div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Mật khẩu cũ</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock-fill text-primary"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" placeholder="Mật khẩu cũ">
                                <button class="btn input-group-text border" type="button">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Mật khẩu mới</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock-fill text-primary"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" placeholder="Mật khẩu mới">
                                <button class="btn input-group-text border" type="button">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label small fw-bold">Xác nhận mật khẩu mới</label>
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock-fill text-primary"></i>
                                </span>
                                <input type="password" class="form-control border-start-0"
                                    placeholder="Xác nhận mật khẩu mới">
                                <button class="btn input-group-text border" type="button">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </div>
                        </div>

                        <button type="button" class="btn btn-primary shadow-sm">
                            <i class="bi bi-shield-check me-2"></i>
                            Đổi mật khẩu
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
// Không có logic, chỉ hiển thị giao diện
</script>
