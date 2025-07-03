<template>
    <div class="card mb-4  w-100">
        <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom ">
            <div class="fw-bold">Cập nhật địa chỉ</div>
            <a class="btn btn-sm btn-outline-primary ms-auto" title="Quay lại"><i class="bi bi-plus-lg me-1"></i>Quay lại</a>
        </div>
        <div class="card-body">
            <form>
                <div class="mb-3">
                    <label for="name" class="form-label">Họ và tên</label>
                    <input type="text" class="form-control" id="name" placeholder="Nhập họ và tên" />
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" placeholder="Nhập số điện thoại" />
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">Tỉnh/Thành phố</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="4">Four</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="district" class="form-label">Quận huyện</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="4">Four</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ward" class="form-label">Phường xã</label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="4">Four</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Địa chỉ</label>
                    <textarea class="form-control" id="address" rows="3" placeholder="Nhập địa chỉ"></textarea>
                </div>
                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Đặt làm địa chỉ mặc định
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">Cập nhật địa chỉ</button>
            </form>
        </div>
    </div>    
</template>
<script lang="ts" setup></script>
