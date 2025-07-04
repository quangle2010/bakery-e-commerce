<template>
  <div class="admin-sidebar" :class="{ 'collapsed': collapsed }">
    <div class="logo-container">
      <div class="logo-wrapper">
        <img src="https://cdn-icons-png.flaticon.com/512/2718/2718224.png" alt="Logo" class="logo-img">
        <h2 v-if="!collapsed">Fshop</h2>
      </div>
    </div>
    <div class="menu">
      <div class="menu-item" v-for="item in sidebars" :key="item.id" :class="{ active: isActive(item.path) }">
        <router-link :to="item.path">
          <i :class="item.icon"></i>
          <span v-if="!collapsed">{{ item.label }}</span>
        </router-link>
      </div>
   
    </div>
  </div>
</template>

<script lang="ts" setup>
import { useRoute } from 'vue-router';

defineProps<{
  collapsed?: boolean;
}>();

const route = useRoute();

const isActive = (path: string) => {
  return route.path.startsWith(path);
};
import { ref } from 'vue'

const sidebars = ref([
    { id: 1, path: '/admin/dashboard', icon: "bi bi-layout-text-window ", label: 'Dashboard' },
    { id: 2, path: '/admin/products', icon: "bi bi-box", label: 'Sản phẩm' },
    { id: 3, path: '/admin/categories', icon: "bi bi-list-ul", label: 'Danh mục' },
    { id: 4, path: '/admin/orders', icon: "bi bi-cart", label: 'Đơn hàng' },
    { id: 5, path: '/admin/profile', icon: "bi bi-person-circle", label: 'Thông tin' },

])

</script>

<style scoped>
.admin-sidebar {
  height: 100%;
  display: flex;
  flex-direction: column;
  width: 200px;
  transition: width 0.3s ease, transform 0.3s ease;
}

.admin-sidebar.collapsed {
  width: 70px;
}

.logo-container {
  padding: 16px;
  text-align: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  display: flex;
  justify-content: center;
}

.logo-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}

.logo-img {
  width: 28px;
  height: 28px;
  object-fit: contain;
}

.logo-container h2 {
  color: white;
  margin: 0;
  font-size: 1.5rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.menu {
  padding: 20px 0;
}

.menu-item {
  padding: 10px 20px;
  transition: all 0.3s;
}

.menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.menu-item.active {
  background-color: rgba(255, 255, 255, 0.2);
  border-left: 4px solid #42b983;
}

.menu-item a {
  color: white;
  text-decoration: none;
  display: flex;
  align-items: center;
}

.menu-item i {
  margin-right: 10px;
  width: 20px;
  text-align: center;
}

.collapsed .menu-item i {
  margin-right: 0;
}

.menu-item span {
  font-size: 0.9rem;
  white-space: nowrap;
}
</style> 