<template>
<div class="card mb-4 border w-100 h-100">
    <div class="d-flex align-items-center p-3 bg-light border-bottom">
        <div class="flex-shrink-0">
            <span class="bg-primary text-white rounded-circle d-flex align-items-center justify-content-center"
                style="width: 45px; height: 45px;">
                <i class="bi bi-person-circle fs-4"></i>
            </span>
        </div>
        <div class="flex-grow-1 ms-3">
            <h6 class="mb-0 fw-bold">User</h6>
            <p class="text-muted small mb-0">user@gmail.com</p>
        </div>
    </div>
    <div class="card-body p-0">
        <ul class="list-group list-group-flush">
        
            <li class="list-group-item py-3 " v-for="sidebar in sidebars" :key="sidebar.id">
                <router-link :to="sidebar.path" class="text-decoration-none d-flex align-items-center">
                    <i :class="sidebar.icon"></i>
                    <span>{{ sidebar.label }}</span>
                </router-link>
            </li>
        </ul>
    </div>
</div>

</template>
<script lang="ts" setup>
import { ref } from 'vue'

const sidebars = ref([
  { id: 1, path: '/user/profile', icon: "bi bi-person-circle me-3 text-primary fs-5", label: 'Thông tin tài khoản' },
  { id: 2, path: '/user/orders', icon: "bi bi-bag me-3 text-primary fs-5", label: 'Đơn hàng' },
  { id: 3, path: '/user/favorites', icon: "bi bi-heart me-3 text-primary fs-5", label: 'Sản phẩm yêu thích' },
  { id: 4, path: '/user/change-password', icon: "bi bi-key me-3 text-primary fs-5", label: 'Đổi mật khẩu' }
])

</script>