<template>
  <div class="dashboard">
    <div class="row">
      <div class="col-md-3">
        <div class="card stat-card">
          <div class="card-body">
            <div class="stat-icon">
              <i class="bi bi-cart-check text-primary"></i>
            </div>
            <h5 class="stat-title">Tổng đơn hàng</h5>
            <h2 class="stat-value">254</h2>
            <p class="stat-desc">
              <i class="bi bi-arrow-up-short text-success"></i>
              <span>12% so với tháng trước</span>
            </p>
          </div>
        </div>
      </div>
      
      <div class="col-md-3">
        <div class="card stat-card">
          <div class="card-body">
            <div class="stat-icon">
              <i class="bi bi-currency-dollar text-success"></i>
            </div>
            <h5 class="stat-title">Doanh thu</h5>
            <h2 class="stat-value">$15,245</h2>
            <p class="stat-desc">
              <i class="bi bi-arrow-up-short text-success"></i>
              <span>8% so với tháng trước</span>
            </p>
          </div>
        </div>
      </div>
      
      <div class="col-md-3">
        <div class="card stat-card">
          <div class="card-body">
            <div class="stat-icon">
              <i class="bi bi-people text-info"></i>
            </div>
            <h5 class="stat-title">Khách hàng mới</h5>
            <h2 class="stat-value">45</h2>
            <p class="stat-desc">
              <i class="bi bi-arrow-up-short text-success"></i>
              <span>5% so với tháng trước</span>
            </p>
          </div>
        </div>
      </div>
      
      <div class="col-md-3">
        <div class="card stat-card">
          <div class="card-body">
            <div class="stat-icon">
              <i class="bi bi-box text-warning"></i>
            </div>
            <h5 class="stat-title">Sản phẩm</h5>
            <h2 class="stat-value">124</h2>
            <p class="stat-desc">
              <i class="bi bi-arrow-up-short text-success"></i>
              <span>3 sản phẩm mới</span>
            </p>
          </div>
        </div>
      </div>
    </div>
    
    <div class="row mt-4">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
            <h5 class="card-title">Doanh thu theo tháng</h5>
          </div>
          <div class="card-body">
            <div class="chart-container">
              <!-- Đây là nơi để thêm biểu đồ -->
              <div class="chart-placeholder">
                <i class="bi bi-bar-chart"></i>
                <p>Biểu đồ doanh thu theo tháng</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="col-md-4">
        <div class="card">
          <div class="card-header">
            <h5 class="card-title">Đơn hàng gần đây</h5>
          </div>
          <div class="card-body p-0">
            <ul class="list-group list-group-flush">
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h6 class="mb-0">Đơn #12345</h6>
                  <small class="text-muted">Nguyễn Văn A</small>
                </div>
                <span class="badge bg-success rounded-pill">Hoàn thành</span>
              </li>
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h6 class="mb-0">Đơn #12346</h6>
                  <small class="text-muted">Trần Thị B</small>
                </div>
                <span class="badge bg-warning rounded-pill">Đang giao</span>
              </li>
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h6 class="mb-0">Đơn #12347</h6>
                  <small class="text-muted">Lê Văn C</small>
                </div>
                <span class="badge bg-primary rounded-pill">Đã xác nhận</span>
              </li>
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h6 class="mb-0">Đơn #12348</h6>
                  <small class="text-muted">Phạm Thị D</small>
                </div>
                <span class="badge bg-danger rounded-pill">Đã hủy</span>
              </li>
              <li class="list-group-item d-flex justify-content-between align-items-center">
                <div>
                  <h6 class="mb-0">Đơn #12349</h6>
                  <small class="text-muted">Hoàng Văn E</small>
                </div>
                <span class="badge bg-success rounded-pill">Hoàn thành</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
// Có thể thêm logic cho dashboard ở đây
</script>

<style scoped>
.dashboard {
  padding: 10px;
}

.stat-card {
  border-radius: 10px;
  border: none;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
}

.stat-icon {
  font-size: 2rem;
  margin-bottom: 10px;
}

.stat-title {
  color: #6c757d;
  font-size: 0.9rem;
  margin-bottom: 5px;
}

.stat-value {
  font-weight: bold;
  margin-bottom: 5px;
}

.stat-desc {
  font-size: 0.8rem;
  color: #6c757d;
  margin-bottom: 0;
}

.chart-container {
  height: 300px;
  position: relative;
}

.chart-placeholder {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #ccc;
}

.chart-placeholder i {
  font-size: 3rem;
  margin-bottom: 10px;
}
</style>