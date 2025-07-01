<template>
<div class="card mb-4 border w-100">
    <div class="d-flex align-items-center p-4 bg-light border-bottom ">
        <h6 class="fw-bold">Đổi mật khẩu</h6>
    </div>
    <div class="card-body flex-grow-1">
        <form th:action="@{/user/change-password}" method="post">
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-lock-fill text-muted"></i>
                    </span>
                    <input type="password" class="form-control border-start-0" name="oldPassword"
                        placeholder="Mật khẩu cũ" >
                </div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-lock-fill text-muted"></i>
                    </span>
                    <input type="password" class="form-control border-start-0" name="password"
                        placeholder="Mật khẩu mới" >
                </div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <span class="input-group-text bg-light border-end-0">
                        <i class="bi bi-lock-fill text-muted"></i>
                    </span>
                    <input type="password" class="form-control border-start-0" name="confirmPassword"
                        placeholder="Xác nhận mật khẩu mới" >
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-sm shadow">
                <i class="bi bi-save me-2"></i>Đổi mật khẩu
            </button>
        </form>
    </div>
</div>

</template>
<script lang="ts" setup></script>