<template>
  <div class="admin-navbar">
    <div class="navbar-left">
      <button class="toggle-btn" @click="toggleSidebar">
        <i class="bi bi-list"></i>
      </button>

    </div>
    <div class="navbar-right">
      <div class="search-box d-none d-md-block">
        <input type="text" placeholder="Tìm kiếm..." />
        <i class="bi bi-search"></i>
      </div>

      <div class="user-dropdown">
        <div class="user-info">
          <i class="bi bi-person-circle fs-4"></i>
          <span class="d-none d-md-block">Admin</span>
        </div>
        <div class="dropdown-menu">
          <router-link to="/admin/profile" class="dropdown-item">
            <i class="bi bi-person"></i>
            <span>Thông tin</span>
          </router-link>

          <div class="dropdown-divider"></div>
          <div class="dropdown-item">
            <button type="button" class="btn btn-danger w-100 text-start" data-bs-toggle="modal"
              data-bs-target="#logoutModal">
              <i class="bi bi-box-arrow-right"></i>
              <span>Đăng xuất</span>
            </button>
          </div>
        </div>
      </div>
    </div>
    <LogoutModal />
  </div>

</template>

<script lang="ts" setup>
import LogoutModal from '../common/LogoutModal.vue';


const emit = defineEmits(['toggle-sidebar']);



const toggleSidebar = () => {
  emit('toggle-sidebar');
};



</script>

<style scoped>
.admin-navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
}

.navbar-left {
  display: flex;
  align-items: center;
}

.toggle-btn {
  background: none;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  margin-right: 15px;
  color: #555;
}

.breadcrumb {
  font-size: 1.1rem;
  color: #555;
  font-weight: 500;
}

.navbar-right {
  display: flex;
  align-items: center;
}

.search-box {
  position: relative;
  margin-right: 20px;
  width: 300px;
}

.search-box input {
  padding: 8px 30px 8px 10px;
  border-radius: 4px;
  border: 1px solid #ddd;
  outline: none;
  width: 100%;
}

.search-box i {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  color: #777;
}


.badge {
  position: absolute;
  top: -5px;
  right: -5px;
  background-color: #e74c3c;
  color: white;
  border-radius: 50%;
  width: 18px;
  height: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 0.7rem;
}

.user-dropdown {
  position: relative;
  cursor: pointer;
}

.user-dropdown:hover .dropdown-menu {
  display: block;
  transition: all 0.3s ease;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.user-info i {
  color: #555;
}

.user-info img {
  width: 35px;
  height: 35px;
  border-radius: 50%;
  margin-right: 10px;
}

.dropdown-menu {
  display: none;
  position: absolute;
  right: 0;
  top: 100%;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  width: 200px;
  z-index: 100;
}

.dropdown-item {
  padding: 10px 15px;
  display: flex;
  align-items: center;
  transition: background-color 0.3s;
}

.dropdown-item:hover {
  background-color: #f5f5f5;
}

.dropdown-item i {
  margin-right: 10px;
  width: 20px;
  text-align: center;
}

.dropdown-divider {
  height: 1px;
  background-color: #eee;
  margin: 5px 0;
}

.logout {
  color: #e74c3c;
}



/* Responsive classes */
.d-none {
  display: none !important;
}

@media (min-width: 768px) {
  .d-md-block {
    display: block !important;
  }
}
</style>