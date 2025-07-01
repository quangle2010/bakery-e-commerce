<template>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mb-4 border rounded-4">
                <div class="card-header text-center text-white bg-primary py-4 border-0 rounded-top-4">
                    <h2 class="fw-bold mb-2">Fshop</h2>
                    <p class="mb-3">Cửa hàng bánh kẹo, bánh ngọt và quà tặng ngọt ngào hàng đầu</p>
                </div>
                <div class="card-body p-4">
                    <div class="text-center mb-4">
                        <h3 class="fw-bold">Đăng ký</h3>
                        <p class="text-muted">Tạo tài khoản mới tại Fshop!</p>
                    </div>
                    <form th:action="@{/register}" method="post">
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-person text-muted"></i>
                                </span>
                                <input type="text" class="form-control border-start-0" name="fullName" placeholder="Họ và tên" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-envelope text-muted"></i>
                                </span>
                                <input type="email" class="form-control border-start-0" name="email" placeholder="Email" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock text-muted"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" name="password" placeholder="Mật khẩu" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock text-muted"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" name="confirmPassword" placeholder="Xác nhận mật khẩu" required>
                            </div>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-sm shadow">
                                <i class="bi bi-person-plus me-2"></i>Đăng ký
                            </button>
                        </div>

                        <div class="mt-4 text-center">
                            <p>
                                Đã có tài khoản?
                                <router-link to="/login" class="text-decoration-none fw-bold">Đăng nhập</router-link>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</template>
<script lang="ts" setup>
</script>