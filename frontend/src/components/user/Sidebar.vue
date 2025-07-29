<script lang="ts" setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/auth';

const auth = useAuthStore();
const route = useRoute();

const isActive = (path: string) => {
  return route.path.startsWith(path);
};
const sidebars = ref([
    { id: 1, path: '/user/profile', icon: "bi bi-person-circle me-3 text-primary fs-5", label: 'Thông tin tài khoản' },
    { id: 2, path: '/user/orders', icon: "bi bi-bag me-3 text-primary fs-5", label: 'Đơn hàng' },
    { id: 3, path: '/user/favorites', icon: "bi bi-heart me-3 text-primary fs-5", label: 'Sản phẩm yêu thích' },
    { id: 4, path: '/user/change-password', icon: "bi bi-key me-3 text-primary fs-5", label: 'Đổi mật khẩu' },
    { id: 5, path: '/user/address', icon: "bi bi-geo-alt me-3 text-primary fs-5", label: 'Địa chỉ' },
])

</script>
<style scoped>
.menu-item.active {
    background-color: rgba(255, 255, 255, 0.2);
    border-left: 4px solid #42b983;
}
</style>
<template>
    <div class="card mb-4 w-100 ">
        <div class="d-flex align-items-center p-3 bg-light border-bottom">
            <div class="flex-shrink-0">
                <span class="bg-primary text-white rounded-circle d-flex align-items-center justify-content-center"
                    style="width: 45px; height: 45px;">
                    <i class="bi bi-person-circle fs-4"></i>
                </span>
            </div>
            <div class="flex-grow-1 ms-3">
                <h6 class="mb-0 fw-bold">{{ auth?.user?.fullName }}</h6>
                <p class="text-muted small mb-0">{{ auth?.user?.email }}</p>
            </div>
        </div>
        <div class="card-body p-0">
            <ul class="list-group list-group-flush">
                <li class="list-group-item menu-item py-3 " v-for="sidebar in sidebars" :key="sidebar.id"
                    :class="{ active: isActive(sidebar.path) }">
                    <router-link :to="sidebar.path" class="text-decoration-none d-flex align-items-center">
                        <i :class="sidebar.icon"></i>
                        <span>{{ sidebar.label }}</span>
                    </router-link>
                </li>
            </ul>
        </div>
    </div>

</template>
