<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card mb-4 border rounded-4">
                    <div class="card-header text-center text-white bg-primary py-4 border-0 rounded-top-4">
                        <h2 class="fw-bold mb-2">Fshop</h2>
                        <p class="mb-3">Thế giới bánh ngọt, kẹo ngon và bánh quy tuyệt vời</p>
                       
                    </div>
                    <div class="card-body p-4">
                        <div class="text-center mb-4">
                            <h3 class="fw-bold">Đăng nhập</h3>
                            <p class="text-muted">Chào mừng bạn quay lại tiệm bánh Fshop!</p>
                        </div>
                        <form th:action="@{/login}" method="post">
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text bg-light border-end-0">
                                        <i class="bi bi-envelope text-muted"></i>
                                    </span>
                                    <input type="text" class="form-control border-start-0" name="username"
                                           placeholder="Email">
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text bg-light border-end-0">
                                        <i class="bi bi-lock text-muted"></i>
                                    </span>
                                    <input type="password" class="form-control border-start-0" name="password"
                                           placeholder="Mật khẩu" >
                                </div>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary btn-sm shadow">
                                    <i class="fas fa-sign-in-alt me-2"></i>Đăng nhập
                                </button>
                            </div>

                            <div class="my-4 d-flex justify-content-between align-items-center">
                                <div>
                                    <p>Chưa có tài khoản? <router-link to="/register" class="text-decoration-none fw-bold">Đăng ký ngay</router-link></p>
                                </div>
                                <div>
                                    <p><router-link to="/forgot-password" class="text-decoration-none fw-bold">Quên mật khẩu?</router-link></p>
                                </div>
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