<template>
    <div class="card mb-4  w-100">
        <div class="d-flex align-items-center justify-content-between p-4 bg-light border-bottom ">
            <div class="fw-bold">Danh sách địa chỉ</div>
            <router-link to="/user/add-address"  class="btn btn-sm btn-outline-primary ms-auto" title="Thêm địa chỉ"><i class="bi bi-plus-lg me-1"></i>Thêm địa chỉ</router-link>
        </div>
        <div class="card-body">
            <div class="card card_item mb-4">
                <div class="card-header d-flex align-items-center justify-content-between">
                    <div class="fw-bold">
                        Nguyen van a
                        <span class="badge bg-success"><i class="bi bi-check-lg me-1"></i>Địa chỉ mặc định</span>
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <router-link to="/user/edit-address/1" class="btn btn-sm btn-outline-info" title="Chi tiết"><i class="bi bi-eye me-1"></i>Chi tiết</router-link>
                    </div>
                </div>
                <div class="card-body">
                    <p class="mb-0"><strong>Điện thoại:</strong> 077777777777</p>
                    <p class="mb-0"><strong>Địa chỉ:</strong> 123 Đường ABC, Phường 1, Quận 1, TP. HCM</p>
                </div>
            </div>
              <div class="card card_item mb-4">
                <div class="card-header d-flex align-items-center justify-content-between">
                    <div class="fw-bold">
                        Nguyen van a
                    </div>
                    <div class="d-flex align-items-center gap-2">
                        <a class="btn btn-sm btn-outline-info" title="Chi tiết"><i class="bi bi-eye me-1"></i>Chi tiết</a>
                        <button class="btn btn-sm btn-outline-danger" title="Xóa"><i class="bi bi-x-circle me-1"></i>Xóa</button>
                    </div>
                </div>
                <div class="card-body">
                    <p class="mb-0"><strong>Điện thoại:</strong> 077777777777</p>
                    <p class="mb-0"><strong>Địa chỉ:</strong> 123 Đường ABC, Phường 1, Quận 1, TP. HCM</p>
                </div>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import router from '../../router';

</script>
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

</style>