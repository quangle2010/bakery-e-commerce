<template>
  <div class="py-2">
    <div v-if="cartItems.length > 0" class="card border shadow-sm rounded-3 mb-4">
      <div class="card-body p-0">
        <div class="table-responsive p-0">
          <table class="table table-hover align-middle mb-0">
            <thead class="table-light text-secondary">
              <tr>
                <th scope="col" class="ps-4">Sản phẩm</th>
                <th scope="col" class="text-center">Giá</th>
                <th scope="col" class="text-center">Số lượng</th>
                <th scope="col" class="text-center">Tổng</th>
                <th scope="col" class="text-center">Thao tác</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in cartItems" :key="index" class="product-row">
                <td class="ps-4">
                  <div class="d-flex align-items-center">
                    <div class="product-img-wrapper me-3">
                      <img
                        :src="item.product.image || 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?q=80&w=1000'"
                        :alt="item.product.name" class="img-fluid rounded-3"
                        style="width: 80px; height: 80px; object-fit: cover;">
                    </div>
                    <div>
                      <h6 class="mb-1 fw-medium">{{ item.product.name }}</h6>
                      <span class="text-muted small">{{ item.product.description }}</span>
                    </div>
                  </div>
                </td>
                <td class="text-center fw-medium">{{ formatPrice(item.product.price) }}</td>
                <td class="text-center">
                  <div class="d-flex align-items-center justify-content-center">
                    <div class="input-group input-group-sm quantity-control" style="width: 120px;">
                      <button class="btn btn-outline-primary rounded-start" type="button"
                        @click="decreaseQuantity(index)">
                        <i class="bi bi-dash"></i>
                      </button>
                      <input type="text" class="form-control text-center bg-white" :value="item.quantity" readonly>
                      <button class="btn btn-outline-primary rounded-end" type="button"
                        @click="increaseQuantity(index)">
                        <i class="bi bi-plus"></i>
                      </button>
                    </div>
                  </div>
                </td>
                <td class="text-center fw-medium">{{ formatPrice(item.product.price * item.quantity) }}</td>
                <td class="text-center">
                  <button class="btn btn-sm btn-outline-danger rounded-circle" title="Xóa sản phẩm"
                    @click="removeItem(index)">
                    <i class="bi bi-trash"></i>
                  </button>
                </td>
              </tr>
            </tbody>
            <tfoot class="table-light">
              <tr>
                <td colspan="3" class="text-end fw-medium py-3">Tổng cộng:</td>
                <td class="text-center fw-bold text-primary py-3 fs-5">{{ formatPrice(totalAmount) }}</td>
                <td></td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>

    <div class="d-flex justify-content-between mt-4" v-if="cartItems.length > 0">
      <button class="btn btn-outline-primary rounded-pill px-4 py-2 d-flex align-items-center"
        @click="$router.push('/products')">
        <i class="bi bi-arrow-left me-2"></i> Tiếp tục mua sắm
      </button>
      <button class="btn btn-primary rounded-pill px-4 py-2 d-flex align-items-center"
        v-on:click="$router.push('/payment')">
        Tiến hành thanh toán <i class="bi bi-arrow-right ms-2"></i>
      </button>
    </div>

    <!-- Phần hiển thị khi giỏ hàng trống -->
    <div v-if="cartItems.length === 0" class="card border-0 shadow-sm rounded-3 text-center py-5">
      <div class="card-body py-5">
        <div class="mb-4">
          <i class="bi bi-cart-x" style="font-size: 5rem; color: #dee2e6;"></i>
        </div>
        <h3 class="fw-bold mb-3">Giỏ hàng của bạn đang trống</h3>
        <p class="text-muted mb-4">Hãy thêm một vài sản phẩm vào giỏ hàng của bạn và quay lại đây nhé!</p>
        <button class="btn btn-primary rounded-pill px-4 py-2" @click="$router.push('/products')">
          <i class="bi bi-bag-plus me-2"></i> Tiếp tục mua sắm
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import axiosClient from '../../util/axiosClient';

// Cấu trúc dữ liệu giỏ hàng theo API
interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  image: string;
  quantity: number;
  weight: number;
  isfavorite: boolean;
  category: string;
  createAt: string;
}

interface CartItem {
  id: number;
  product: Product;
  quantity: number;
}

// Giả lập dữ liệu giỏ hàng - sau này sẽ được thay thế bằng dữ liệu từ API hoặc store
const cartItems = ref<CartItem[]>([]);


const data = async () => {
  try {
    const reps = await axiosClient.get('/user/cart');
    if (reps.data.status === true) {
      cartItems.value = reps.data.data;
    } else {
      cartItems.value = [];
    }
  } catch (error) {
    cartItems.value = [];
  }
}

// Tính tổng tiền
const totalAmount = computed(() => {
  return cartItems.value.reduce((total, item) => total + (item.product.price * item.quantity), 0);
});

// Định dạng giá tiền
const formatPrice = (price: number): string => {
  return new Intl.NumberFormat('vi-VN').format(price) + ' ₫';
};

// Tăng số lượng sản phẩm
const increaseQuantity = (index: number) => {
  const item = cartItems.value[index];
  // Kiểm tra số lượng tồn kho
  if (item.quantity < item.product.quantity) {
    item.quantity += 1;
  }
};

// Giảm số lượng sản phẩm
const decreaseQuantity = (index: number) => {
  if (cartItems.value[index].quantity > 1) {
    cartItems.value[index].quantity -= 1;
  }
};

// Xóa sản phẩm khỏi giỏ hàng
const removeItem = (index: number) => {
  cartItems.value.splice(index, 1);
};

onMounted(() => {
    data();
});

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

.quantity-control {
  display: flex;
  align-items: center;
  justify-content: center;
}

.quantity-control .form-control {
  border-left: 0;
  border-right: 0;
  text-align: center;
  padding-left: 0;
  padding-right: 0;
  width: 50px;
  margin: 0 auto;
  font-weight: 500;
}

.quantity-control .btn {
  min-width: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  padding: 0;
}

.quantity-control .btn:hover {
  background-color: #e9ecef;
}

.quantity-control .btn:focus,
.quantity-control .form-control:focus {
  box-shadow: none;
  border-color: #dee2e6;
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