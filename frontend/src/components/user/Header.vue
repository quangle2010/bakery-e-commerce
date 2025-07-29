<script setup lang="ts">
import { useAuthStore } from '../../store/auth';
import LogoutModal from '../common/LogoutModal.vue';
const auth = useAuthStore();

</script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-light shadow-sm" style="background-color: #fff8f0;">
      <div class="container py-2">
        <router-link class="navbar-brand d-flex align-items-center" to="/home">
          <span class="rounded-circle bg-gradient-primary p-2 me-2 d-flex align-items-center justify-content-center"
            style="background: linear-gradient(45deg, #f8a5c2, #fd868c); width: 40px; height: 40px;">
            <img src="https://cdn-icons-png.flaticon.com/512/2718/2718224.png" alt="Logo" width="24" height="24"
              class="logo-img">
          </span>
          <span>
            <span class="fw-bold h5 mb-0 gradient-text">Fshop</span>
            <div class="text-muted small d-none d-sm-block">Bánh kẹo ngọt ngào mỗi ngày</div>
          </span>
        </router-link>

        <div class="d-flex d-lg-none ms-auto me-2">
          <button class="btn btn-sm btn-outline-secondary border-0" type="button" data-bs-toggle="collapse"
            data-bs-target="#searchCollapse" aria-expanded="false" aria-controls="searchCollapse">
            <i class="bi bi-search"></i>
          </button>
        </div>

        <router-link to="/cart"
          class="btn btn-sm gradient-button position-relative me-2 d-flex d-lg-none align-items-center">
          <i class="bi bi-cart"></i>
          <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"> {{ auth.cartCount }}</span>
        </router-link>

        <button class="navbar-toggler border-0 shadow-none" type="button" data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse w-100 mt-2 d-lg-none" id="searchCollapse">
          <form class="d-flex w-100 position-relative">
            <div class="input-group">
              <input class="form-control border rounded-pill ps-4 pe-5" type="search" placeholder="Tìm bánh kẹo..."
                aria-label="Search" style="border-color: #e0e0e0 !important;">
              <button class="btn position-absolute end-0 top-0 bottom-0 text-primary bg-transparent border-0"
                type="submit">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </form>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
            <li class="nav-item mx-2">
              <router-link class="nav-link fw-medium position-relative" to="/products">
                Sản phẩm
                <span class="nav-link-indicator"></span>
              </router-link>
            </li>
            <li class="nav-item mx-2">
              <router-link class="nav-link fw-medium position-relative" to="/about-us">
                Về chúng tôi
                <span class="nav-link-indicator"></span>
              </router-link>
            </li>
            <li class="nav-item mx-2">
              <router-link class="nav-link fw-medium position-relative" to="/contact">
                Liên hệ
                <span class="nav-link-indicator"></span>
              </router-link>
            </li>
          </ul>

          <div class="d-none d-lg-flex">
            <form class="d-flex me-3 position-relative">
              <div class="input-group">
                <input class="form-control border rounded-pill ps-4 pe-5" type="search" placeholder="Tìm bánh kẹo..."
                  aria-label="Search" style="border-color: #e0e0e0 !important;">
                <button class="btn position-absolute end-0 top-0 bottom-0 text-primary bg-transparent border-0"
                  type="submit">
                  <i class="bi bi-search"></i>
                </button>
              </div>
            </form>

            <router-link to="/cart" class="btn gradient-button position-relative me-3 d-flex align-items-center">
              <i class="bi bi-cart"></i>
              <span class="ms-2 d-none d-md-inline">Giỏ hàng</span>
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"> {{ auth.cartCount }}</span>
            </router-link>

            <div class="dropdown">
              <button class="btn gradient-button dropdown-toggle d-flex align-items-center" type="button"
                id="accountDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="bi bi-person-circle fs-5 me-md-2"></i>
                <span class="d-none d-md-inline">Tài khoản</span>
              </button>
              <ul class="dropdown-menu dropdown-menu-end shadow border-0 rounded-3 py-2"
                aria-labelledby="accountDropdown">
                <li v-if="!auth.isAuthenticated"><router-link
                    class="dropdown-item py-2 px-3 rounded-3 mb-1 mx-1 transition-all" to="/login"><i
                      class="bi bi-box-arrow-in-right me-2 text-primary"></i> Đăng nhập</router-link></li>
                <li v-if="!auth.isAuthenticated"><router-link
                    class="dropdown-item py-2 px-3 rounded-3 mb-1 mx-1 transition-all" to="/register"><i
                      class="bi bi-person-plus me-2 text-primary"></i> Đăng ký</router-link></li>
                <li v-if="auth.isAuthenticated"><router-link
                    class="dropdown-item py-2 px-3 rounded-3 mb-1 mx-1 transition-all" to="/user/profile"><i
                      class="bi bi-person-vcard me-2 text-primary"></i> Thông tin tài
                    khoản</router-link></li>
                <li v-if="auth.isAuthenticated"><router-link
                    class="dropdown-item py-2 px-3 rounded-3 mb-1 mx-1 transition-all" to="/user/orders"><i
                      class="bi bi-list-ul me-2 text-primary"></i> Đơn hàng của tôi</router-link>
                </li>
                <li v-if="auth.isAuthenticated">
                  <button type="button" class="dropdown-item py-2 px-3 rounded-3 mb-1 mx-1 transition-all text-danger"
                    data-bs-toggle="modal" data-bs-target="#logoutModal">
                    <i class="bi bi-box-arrow-right me-2"></i> Đăng xuất
                  </button>
                </li>
              </ul>
            </div>
          </div>
          <div class="d-lg-none mt-3">
            <div class="d-flex flex-column">
              <router-link class="btn btn-outline-primary mb-2 d-flex align-items-center justify-content-center"
                to="/login">
                <i class="bi bi-box-arrow-in-right me-2"></i> Đăng nhập
              </router-link>
              <router-link class="btn btn-outline-primary mb-3 d-flex align-items-center justify-content-center"
                to="/register">
                <i class="bi bi-person-plus me-2"></i> Đăng ký
              </router-link>
              <div class="list-group">
                <router-link to="/user/profile" class="list-group-item list-group-item-action border-0 rounded mb-1">
                  <i class="bi bi-person-vcard me-2 text-primary"></i> Thông tin tài khoản
                </router-link>
                <router-link to="/user/orders" class="list-group-item list-group-item-action border-0 rounded mb-1">
                  <i class="bi bi-list-ul me-2 text-primary"></i> Đơn hàng của tôi
                </router-link>
                <button type="button" class="list-group-item list-group-item-action border-0 rounded text-danger"
                  data-bs-toggle="modal" data-bs-target="#logoutModal">
                  <i class="bi bi-box-arrow-right me-2"></i> Đăng xuất
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <LogoutModal />
  </header>
</template>


<style scoped>
.gradient-text {
  background: linear-gradient(45deg, #f8a5c2, #fd868c);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
  -webkit-text-fill-color: transparent;
}

.gradient-button {
  background: linear-gradient(45deg, #f8a5c2, #fd868c);
  color: white;
  border-radius: 50px;
  padding: 8px 16px;
}

.nav-link-indicator {
  position: absolute;
  width: 100%;
  height: 2px;
  bottom: -2px;
  left: 0;
  background: linear-gradient(45deg, #f8a5c2, #fd868c);
  transform: scaleX(0);
  transition: transform 0.3s ease;
  transform-origin: left;
}

.transition-all {
  transition: all 0.3s ease;
}

.dropdown-item:hover {
  background-color: rgba(248, 165, 194, 0.1);
  transform: translateX(5px);
}

.nav-link:hover .nav-link-indicator {
  transform: scaleX(1);
}

.category-icon-container {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 12px;
  background: linear-gradient(45deg, rgba(248, 165, 194, 0.1),
      rgba(253, 134, 140, 0.1));
  color: #f8a5c2;
  font-size: 1.2rem;
  transition: all 0.3s ease;
}

.category-item:hover .category-icon-container {
  background: linear-gradient(45deg, #f8a5c2, #fd868c);
  color: white;
  transform: scale(1.1);
}

.category-item {
  border-radius: 8px;
  margin: 4px 8px;
  transition: all 0.3s ease;
}

.animate__animated {
  animation-duration: 0.4s;
}

.animate__fadeIn {
  animation-name: fadeIn;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Responsive styles */
@media (max-width: 992px) {
  .gradient-button {
    padding: 6px 12px;
  }

  .list-group-item {
    transition: all 0.3s ease;
  }

  .list-group-item:hover {
    background-color: rgba(248, 165, 194, 0.1);
    transform: translateX(5px);
  }
}

@media (max-width: 576px) {
  .logo-img {
    width: 20px;
    height: 20px;
  }
}
</style>
