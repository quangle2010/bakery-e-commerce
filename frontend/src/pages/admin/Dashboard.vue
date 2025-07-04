<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-3">
      <div>
        <h4 class="fw-bold mb-0">Tổng quan</h4>
        <small class="text-muted">Thống kê hoạt động kinh doanh</small>
      </div>
      <div class="d-flex gap-2">
        <div class="input-group ">
          <span class="input-group-text bg-light border-end-0">
            <i class="bi bi-calendar3"></i>
          </span>
          <input type="date" class="form-control border-start-0" placeholder="Từ ngày" />
        </div>
        <div class="input-group ">
          <span class="input-group-text bg-light border-end-0">
            <i class="bi bi-calendar3"></i>
          </span>
          <input type="date" class="form-control border-start-0" placeholder="Đến ngày" />
        </div>
        <button class="btn btn-sm btn-primary">
          <i class="bi bi-search"></i>
        </button>
      </div>
    </div>

    <div class="row g-3">
      <div class="col-md-4">
        <div class="card h-100 border-0 shadow-sm">
          <div class="card-body">
            <div class="d-flex justify-content-between mb-3">
              <h5 class="fw-bold text-primary mb-0">Đơn hàng</h5>
              <div class="rounded-circle bg-success bg-opacity-10 p-2">
                <i class="bi bi-cart text-primary"></i>
              </div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2 pb-2 border-bottom">
              <div class="fw-bold">Tổng đơn hàng</div>
              <div class="badge bg-success rounded-pill fs-6">12.000.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-x-circle text-danger me-1"></i> Đã hủy</div>
              <div class="badge bg-danger rounded-pill">1.200.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-hourglass text-warning me-1"></i> Chờ xác nhận</div>
              <div class="badge bg-warning rounded-pill">2.400.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-check-circle text-success me-1"></i> Đã xác nhận</div>
              <div class="badge bg-success rounded-pill">3.500.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-truck text-info me-1"></i> Đang giao</div>
              <div class="badge bg-info rounded-pill">2.100.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
              <div><i class="bi bi-check-all text-success me-1"></i> Đã giao</div>
              <div class="badge bg-success rounded-pill">2.800.000đ</div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-0 shadow-sm">
          <div class="card-body">
            <div class="d-flex justify-content-between mb-3">
              <h5 class="fw-bold text-success mb-0">Doanh thu</h5>
              <div class="rounded-circle bg-success bg-opacity-10 p-2">
                <i class="bi bi-currency-dollar text-success"></i>
              </div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2 pb-2 border-bottom">
              <div class="fw-bold">Tổng doanh thu</div>
              <div class="badge bg-success rounded-pill fs-6">12.000.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-x-circle text-danger me-1"></i> Đã hủy</div>
              <div class="badge bg-danger rounded-pill">1.200.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-hourglass text-warning me-1"></i> Chờ xác nhận</div>
              <div class="badge bg-warning rounded-pill">2.400.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-check-circle text-success me-1"></i> Đã xác nhận</div>
              <div class="badge bg-success rounded-pill">3.500.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-truck text-info me-1"></i> Đang giao</div>
              <div class="badge bg-info rounded-pill">2.100.000đ</div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
              <div><i class="bi bi-check-all text-success me-1"></i> Đã giao</div>
              <div class="badge bg-success rounded-pill">2.800.000đ</div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-0 shadow-sm">
          <div class="card-body">
            <div class="d-flex justify-content-between mb-3">
              <h5 class="fw-bold text-info mb-0">Sản phẩm</h5>
              <div class="rounded-circle bg-info bg-opacity-10 p-2">
                <i class="bi bi-box-seam text-info"></i>
              </div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2 pb-2 border-bottom">
              <div class="fw-bold">Tổng sản phẩm</div>
              <div class="badge bg-info rounded-pill fs-6">120</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-star-fill text-warning me-1"></i> Bánh kem chocolate</div>
              <div class="badge bg-warning rounded-pill">32 đã bán</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-star-fill text-warning me-1"></i> Bánh mì Pháp</div>
              <div class="badge bg-warning rounded-pill">28 đã bán</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-star-fill text-warning me-1"></i> Bánh cupcake</div>
              <div class="badge bg-warning rounded-pill">25 đã bán</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-star-fill text-warning me-1"></i> Bánh tiramisu</div>
              <div class="badge bg-warning rounded-pill">20 đã bán</div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
              <div><i class="bi bi-star-fill text-warning me-1"></i> Bánh croissant</div>
              <div class="badge bg-warning rounded-pill">18 đã bán</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="col-md-12 my-4">
      <div class="card border-0 shadow-sm">
        <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
          <h5 class="card-title fw-bold mb-0">Doanh thu theo tháng</h5>

        </div>
        <div class="card-body">
          <div class="chart-container" style="height: 300px;">
            <!-- Đây là nơi để thêm biểu đồ -->
            <div
              class="chart-placeholder d-flex flex-column justify-content-center align-items-center h-100 bg-light rounded">
              <i class="bi bi-bar-chart fs-1 text-primary"></i>
              <p class="mt-2 text-muted">Biểu đồ doanh thu theo tháng</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
// Có thể thêm logic cho dashboard ở đây
</script>
