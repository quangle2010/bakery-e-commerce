<template>
  <div>
    <!-- Banner -->
    <div class="bg-primary bg-opacity-10 py-5 mb-5">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-6 text-md-start text-center mb-4 mb-md-0">
            <h1 class="fw-bold text-primary mb-3">Bánh Ngọt Tươi Mới Mỗi Ngày</h1>
            <p class="lead mb-4">Khám phá thế giới bánh ngọt thơm ngon với công thức đặc biệt từ đầu bếp hàng đầu của chúng tôi.</p>
            <div class="d-flex gap-3 justify-content-md-start justify-content-center">
              <button class="btn btn-primary fw-medium">Đặt hàng ngay</button>
              <button class="btn btn-outline-primary fw-medium">Xem thực đơn</button>
            </div>
          </div>
          <div class="col-md-6">
            <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a" class="img-fluid rounded-3 shadow" alt="Bánh ngọt">
          </div>
        </div>
      </div>
    </div>

    <!-- Danh mục sản phẩm -->
    <div class="container mb-5">
      <h2 class="text-center fw-bold mb-4">Danh Mục Sản Phẩm</h2>
      <div class="row g-4">
        <div class="col-md-4">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1557925923-cd4648e211a0" class="card-img-top" alt="Bánh kem">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Kem</h4>
              <p class="card-text">Bánh kem tươi ngon cho mọi dịp đặc biệt</p>
              <a href="#" class="btn btn-outline-primary">Xem thêm</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1603532648955-039310d9ed75" class="card-img-top" alt="Bánh ngọt">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Ngọt</h4>
              <p class="card-text">Các loại bánh ngọt thơm ngon hấp dẫn</p>
              <a href="#" class="btn btn-outline-primary">Xem thêm</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1586195831800-24f14c992cea" class="card-img-top" alt="Bánh mì">
            <div class="card-body text-center">
              <h4 class="card-title">Bánh Mì</h4>
              <p class="card-text">Bánh mì tươi mới nướng mỗi ngày</p>
              <a href="#" class="btn btn-outline-primary">Xem thêm</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Sản phẩm nổi bật -->
    <div class="container mb-5">
      <h2 class="text-center fw-bold mb-4">Sản Phẩm Nổi Bật</h2>
      <div class="row g-4">
        <div class="col-md-3 col-6">
          <div class="card border-0 shadow-sm h-100">
            <div class="badge bg-danger position-absolute top-0 end-0 m-2">-15%</div>
            <img src="https://images.unsplash.com/photo-1578985545062-69928b1d9587" class="card-img-top" alt="Bánh kem chocolate">
            <div class="card-body">
              <h5 class="card-title">Bánh Kem Chocolate</h5>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <span class="text-danger fw-bold">185.000đ</span>
                <span class="text-decoration-line-through text-muted">220.000đ</span>
              </div>
              <button class="btn btn-primary w-100"><i class="bi bi-cart-plus me-1"></i>Thêm vào giỏ</button>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1550617931-e17a7b70dce2" class="card-img-top" alt="Bánh Croissant">
            <div class="card-body">
              <h5 class="card-title">Bánh Croissant</h5>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <span class="text-danger fw-bold">45.000đ</span>
              </div>
              <button class="btn btn-primary w-100"><i class="bi bi-cart-plus me-1"></i>Thêm vào giỏ</button>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card border-0 shadow-sm h-100">
            <div class="badge bg-success position-absolute top-0 end-0 m-2">Mới</div>
            <img src="https://images.unsplash.com/photo-1551024601-bec78aea704b" class="card-img-top" alt="Cupcake">
            <div class="card-body">
              <h5 class="card-title">Cupcake Dâu</h5>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <span class="text-danger fw-bold">35.000đ</span>
              </div>
              <button class="btn btn-primary w-100"><i class="bi bi-cart-plus me-1"></i>Thêm vào giỏ</button>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card border-0 shadow-sm h-100">
            <img src="https://images.unsplash.com/photo-1587248720327-8eb72564be1e" class="card-img-top" alt="Bánh Tiramisu">
            <div class="card-body">
              <h5 class="card-title">Bánh Tiramisu</h5>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <span class="text-danger fw-bold">95.000đ</span>
              </div>
              <button class="btn btn-primary w-100"><i class="bi bi-cart-plus me-1"></i>Thêm vào giỏ</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>
