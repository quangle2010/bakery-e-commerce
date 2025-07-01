<template>
<div class="card mb-4 border w-100">
    <div class="d-flex align-items-center p-4 bg-light border-bottom ">
        <h6 class="fw-bold">Thông tin tài khoản</h6>
    </div>
    <div class="card-body flex-grow-1">

        <form method="post">
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-person text-muted"></i>
                    </span>
                    <input type="text" class="form-control border-start-0" name="fullName" placeholder="Họ và tên">
                </div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-envelope text-muted"></i>
                    </span>
                    <input type="email" class="form-control border-start-0" name="email" placeholder="Email">
                </div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-telephone text-muted"></i>
                    </span>
                    <input type="tel" class="form-control border-start-0" name="phoneNumber"
                        placeholder="Số điện thoại">
                </div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-geo-alt text-muted"></i>
                    </span>
                    <input type="text" class="form-control border-start-0" name="address" placeholder="Địa chỉ">
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-sm shadow">
                <i class="bi bi-save me-2"></i>Lưu thông tin
            </button>
        </form>
    </div>
</div>

</template>
<script lang="ts" setup></script>