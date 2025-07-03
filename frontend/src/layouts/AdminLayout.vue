<template>
  <div class="admin-layout">
    <div
      class="sidebar-overlay"
      v-if="!sidebarCollapsed && isMobile"
      @click="toggleSidebar"
    ></div>

    <AdminSidebar
      class="sidebar"
      :class="{
        collapsed: sidebarCollapsed,
        mobile: isMobile,
        'mobile-open': isMobile && !sidebarCollapsed
      }"
      :collapsed="sidebarCollapsed"
      :is-mobile="isMobile"
      @toggle-sidebar="toggleSidebar"
    />

    <div
      class="main-content"
      :class="{ 'sidebar-collapsed': sidebarCollapsed || isMobile }"
    >
      <AdminNavbar class="navbar" @toggle-sidebar="toggleSidebar" />
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import AdminSidebar from '../components/admin/AdminSidebar.vue'
import AdminNavbar from '../components/admin/AdminNavbar.vue'

const sidebarCollapsed = ref(false)
const isMobile = ref(false)

const toggleSidebar = () => {
  sidebarCollapsed.value = !sidebarCollapsed.value
}

const checkScreenSize = () => {
  if (typeof window !== 'undefined') {
    const wasMobile = isMobile.value
    isMobile.value = window.innerWidth < 768

    if (!wasMobile && isMobile.value) {
      sidebarCollapsed.value = true
    }
  }
}

onMounted(() => {
  if (typeof window !== 'undefined') {
    checkScreenSize()
    window.addEventListener('resize', checkScreenSize)
  }
})

onUnmounted(() => {
  if (typeof window !== 'undefined') {
    window.removeEventListener('resize', checkScreenSize)
  }
})
</script>

<style scoped>
.admin-layout {
  display: flex;
  min-height: 100vh;
  position: relative;
}

.sidebar-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: white;
  position: fixed;
  height: 100vh;
  overflow-y: auto;
  transition: all 0.3s ease;
  z-index: 1000;
}

.sidebar.collapsed {
  width: 70px;
}

.sidebar.mobile {
  transform: translateX(-100%);
}

.sidebar.mobile-open {
  transform: translateX(0);
}

.main-content {
  flex: 1;
  margin-left: 250px;
  display: flex;
  flex-direction: column;
  transition: all 0.3s ease;
}

.main-content.sidebar-collapsed {
  margin-left: 70px;
}

@media (max-width: 767px) {
  .main-content {
    margin-left: 0;
    width: 100%;
  }

  .main-content.sidebar-collapsed {
    margin-left: 0;
  }
}

.navbar {
  height: 60px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.content {
  padding: 20px;
  background-color: #f5f5f5;
  min-height: calc(100vh - 60px);
}
</style>