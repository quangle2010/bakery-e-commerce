<template>
  <div class="py-2">
    <div class="card border shadow-sm rounded-3 mb-4">
      <div class="card-body p-0">
        <div class="table-responsive p-0">
          <table class="table table-hover align-middle mb-0">
            <thead class="table-light text-secondary">
              <tr>
                <th scope="col" class="ps-4">
                  <div class="form-check">
                    <label for="headerCheckbox">Chọn tất cả (0)</label>
                    <input class="form-check-input border border-primary " type="checkbox" id="headerCheckbox">
                  </div>
                </th>
                <th scope="col" class="text-center">Giá</th>
                <th scope="col" class="text-center">Số lượng</th>
                <th scope="col" class="text-center">Tổng</th>
              </tr>
            </thead>
            <tbody>
              <tr class="product-row">
                <td class="ps-4 ">
                  <div class="d-flex align-items-center">
                    <div class="form-check">
                      <input class="form-check-input border border-primary" type="checkbox" id="product1">
                    </div>
                    <div class="d-flex align-items-center">
                      <div class="product-img-wrapper me-3">
                        <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a?q=80&w=1000"
                          alt="Product Image" class="img-fluid rounded-3"
                          style="width: 80px; height: 80px; object-fit: cover;">
                      </div>
                      <div>
                        <h6 class="mb-1 fw-medium">Bánh Croissant</h6>
                        <span class="text-muted small">Bánh sừng bò Pháp</span>
                      </div>
                    </div>
                  </div>
                </td>
                <td class="text-center fw-medium">35.000 ₫</td>
                <td class="text-center">
                  <div class="input-group input-group-sm quantity-control mx-auto" style="width: 120px;">
                    <button class="btn btn-outline-secondary rounded-start-pill" type="button" title="Giảm">-</button>
                    <input type="number" class="form-control text-center border-secondary-subtle" value="2" min="1">
                    <button class="btn btn-outline-secondary rounded-end-pill" type="button" title="Thêm">+</button>
                  </div>
                </td>
                <td class="text-center fw-medium">70.000 ₫</td>

              </tr>
               <tr class="product-row">
                <td class="ps-4 ">
                  <div class="d-flex align-items-center">
                    <div class="form-check">
                      <input class="form-check-input border border-primary" type="checkbox" id="product1">
                    </div>
                    <div class="d-flex align-items-center">
                      <div class="product-img-wrapper me-3">
                        <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a?q=80&w=1000"
                          alt="Product Image" class="img-fluid rounded-3"
                          style="width: 80px; height: 80px; object-fit: cover;">
                      </div>
                      <div>
                        <h6 class="mb-1 fw-medium">Bánh Croissant</h6>
                        <span class="text-muted small">Bánh sừng bò Pháp</span>
                      </div>
                    </div>
                  </div>
                </td>
                <td class="text-center fw-medium">35.000 ₫</td>
                <td class="text-center">
                  <div class="input-group input-group-sm quantity-control mx-auto" style="width: 120px;">
                    <button class="btn btn-outline-secondary rounded-start-pill" type="button" title="Giảm">-</button>
                    <input type="number" class="form-control text-center border-secondary-subtle" value="2" min="1">
                    <button class="btn btn-outline-secondary rounded-end-pill" type="button" title="Thêm">+</button>
                  </div>
                </td>
                <td class="text-center fw-medium">70.000 ₫</td>

              </tr>
               <tr class="product-row">
                <td class="ps-4 ">
                  <div class="d-flex align-items-center">
                    <div class="form-check">
                      <input class="form-check-input border border-primary" type="checkbox" id="product1">
                    </div>
                    <div class="d-flex align-items-center">
                      <div class="product-img-wrapper me-3">
                        <img src="https://images.unsplash.com/photo-1555507036-ab1f4038808a?q=80&w=1000"
                          alt="Product Image" class="img-fluid rounded-3"
                          style="width: 80px; height: 80px; object-fit: cover;">
                      </div>
                      <div>
                        <h6 class="mb-1 fw-medium">Bánh Croissant</h6>
                        <span class="text-muted small">Bánh sừng bò Pháp</span>
                      </div>
                    </div>
                  </div>
                </td>
                <td class="text-center fw-medium">35.000 ₫</td>
                <td class="text-center">
                  <div class="input-group input-group-sm quantity-control mx-auto" style="width: 120px;">
                    <button class="btn btn-outline-secondary rounded-start-pill" type="button" title="Giảm">-</button>
                    <input type="number" class="form-control text-center border-secondary-subtle" value="2" min="1">
                    <button class="btn btn-outline-secondary rounded-end-pill" type="button" title="Thêm">+</button>
                  </div>
                </td>
                <td class="text-center fw-medium">70.000 ₫</td>

              </tr>
            </tbody>
            <tfoot class="table-light">
              <tr>
                <td colspan="1" class="text-start ps-4 py-3">
                  <div class="d-flex align-items-center">
                    <div class="form-check me-3">
                      <input class="form-check-input border border-primary" type="checkbox" id="selectAll">
                      <label class="form-check-label" for="selectAll">Chọn tất cả (0)</label>
                    </div>
                    <button class="btn btn-sm btn-outline-danger rounded-pill px-3" title="Xóa các mục đã chọn">
                      <i class="bi bi-trash me-1"></i> Xóa đã chọn
                    </button>
                  </div>
                </td>
                <td colspan="2" class="text-end fw-medium py-3">Tổng cộng (0) :</td>
                <td class="text-center fw-bold text-primary py-3 fs-5">190.000 ₫</td>
            
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>

    <div class="d-flex justify-content-between mt-4">
      <button class="btn btn-outline-primary rounded-pill px-4 py-2 d-flex align-items-center">
        <i class="bi bi-arrow-left me-2"></i> Tiếp tục mua sắm
      </button>
      <button class="btn btn-primary rounded-pill px-4 py-2 d-flex align-items-center" v-on:click="$router.push('/payment')">
        Tiến hành thanh toán <i class="bi bi-arrow-right ms-2"></i>
      </button>
    </div>

    <!-- Phần hiển thị khi giỏ hàng trống (ẩn đi) -->
    <div class="card border-0 shadow-sm rounded-3 text-center py-5" style="display: none;">
      <div class="card-body py-5">
        <div class="mb-4">
          <i class="bi bi-cart-x" style="font-size: 5rem; color: #dee2e6;"></i>
        </div>
        <h3 class="fw-bold mb-3">Giỏ hàng của bạn đang trống</h3>
        <p class="text-muted mb-4">Hãy thêm một vài sản phẩm vào giỏ hàng của bạn và quay lại đây nhé!</p>
        <button class="btn btn-primary rounded-pill px-4 py-2">
          <i class="bi bi-bag-plus me-2"></i> Tiếp tục mua sắm
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Không có logic, chỉ có giao diện
</script>

<style scoped>
.table th {
  font-weight: 600;
  font-size: 0.9rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.table td {
  vertical-align: middle;
  padding-top: 1rem;
  padding-bottom: 1rem;
}

.product-row:hover {
  background-color: #f8f9fa;
}

.product-img-wrapper {
  overflow: hidden;
  border-radius: 0.5rem;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;
}

.product-row:hover .product-img-wrapper {
  transform: scale(1.05);
}

.quantity-control .btn:hover {
  background-color: #e9ecef;
}

input[type=number]::-webkit-inner-spin-button,
input[type=number]::-webkit-outer-spin-button {
  opacity: 1;
}

.form-check-input border border-primary {
  cursor: pointer;
}

.rounded-3 {
  border-radius: 0.5rem !important;
}

.fw-medium {
  font-weight: 500;
}

.btn-outline-primary:hover,
.btn-primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(13, 110, 253, 0.2);
  transition: all 0.3s ease;
}

.btn-outline-danger:hover,
.btn-link.text-danger:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(220, 53, 69, 0.2);
  transition: all 0.3s ease;
}

.btn-link.text-danger:hover {
  color: #bb2d3b !important;
}

.table-light {
  --bs-table-bg: #f8f9fa;
}
</style>