<template>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card  mb-4 border rounded-4">
                <div class="card-header text-center text-white bg-primary py-4 border-0 rounded-top-4">
                    <h2 class="fw-bold mb-2">FShop</h2>
                    <p class="mb-3">Thế giới bánh kẹo ngọt ngào cho mọi lứa tuổi</p>
                   
                </div>
                <div class="card-body p-4 p-md-5">
                    <div class="text-center mb-4">
                        <h3 class="fw-bold">Quên mật khẩu</h3>
                        <p class="text-muted">Nhập email để khôi phục mật khẩu của bạn!</p>
                    </div>
                    <form th:action="@{/forgot-password}" method="post">
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                   <i class="bi bi-envelope-at"></i>
                                </span>
                                <input type="text" class="form-control border-start-0" name="email" placeholder="Email"
                                    required>
                            </div>
                        </div>


                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-sm shadow">
                                <i class="bi bi-paper-plane me-2"></i>Gửi
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</template>
<script lang="ts" setup></script>