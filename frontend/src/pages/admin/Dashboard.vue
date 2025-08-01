<template>
  <div>
    <!-- Header -->
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
          <input type="date" class="form-control border-start-0" v-model="startDate" placeholder="Từ ngày" />
        </div>
        <div class="input-group ">
          <span class="input-group-text bg-light border-end-0">
            <i class="bi bi-calendar3"></i>
          </span>
          <input type="date" class="form-control border-start-0" v-model="endDate" placeholder="Đến ngày" />
        </div>
       
      </div>
    </div>

    <!-- Dashboard cards -->
    <div class="row g-3">
      <!-- Đơn hàng -->
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
              <div class="badge bg-success rounded-pill fs-6">
                {{ dashboard.totalOrders }}
              </div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-x-circle text-danger me-1"></i> Đã hủy</div>
              <div class="badge bg-danger rounded-pill">{{ dashboard.cancelledOrders }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-hourglass text-warning me-1"></i> Chờ xác nhận</div>
              <div class="badge bg-warning rounded-pill">{{ dashboard.pendingOrders }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-check-circle text-success me-1"></i> Đã xác nhận</div>
              <div class="badge bg-success rounded-pill">{{ dashboard.confirmedOrders }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-truck text-info me-1"></i> Đang giao</div>
              <div class="badge bg-info rounded-pill">{{ dashboard.deliveringOrders }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
              <div><i class="bi bi-check-all text-success me-1"></i> Đã giao</div>
              <div class="badge bg-success rounded-pill">{{ dashboard.deliveredOrders }}</div>
            </div>
          </div>
        </div>
      </div>

      <!-- Doanh thu -->
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
              <div class="badge bg-success rounded-pill fs-6">
                {{ formatCurrency(dashboard.revenuetotalOrders) }}
              </div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-x-circle text-danger me-1"></i> Đã hủy</div>
              <div class="badge bg-danger rounded-pill">{{ formatCurrency(dashboard.revenuecancelledOrders) }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-hourglass text-warning me-1"></i> Chờ xác nhận</div>
              <div class="badge bg-warning rounded-pill">{{ formatCurrency(dashboard.revenuependingOrders) }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-check-circle text-success me-1"></i> Đã xác nhận</div>
              <div class="badge bg-success rounded-pill">{{ formatCurrency(dashboard.revenueconfirmedOrders) }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-truck text-info me-1"></i> Đang giao</div>
              <div class="badge bg-info rounded-pill">{{ formatCurrency(dashboard.revenuedeliveringOrders) }}</div>
            </div>
            <div class="d-flex justify-content-between align-items-center">
              <div><i class="bi bi-check-all text-success me-1"></i> Đã giao</div>
              <div class="badge bg-success rounded-pill">{{ formatCurrency(dashboard.revenuedeliveredOrders) }}</div>
            </div>
          </div>
        </div>
      </div>

      <!-- Sản phẩm -->
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
              <div class="badge bg-info rounded-pill fs-6">{{ productCount }}</div>
            </div>
            <div v-for="item in productSell" :key="item.id"
              class="d-flex justify-content-between align-items-center mb-2">
              <div><i class="bi bi-star-fill text-warning me-1"></i> {{ item.name }}</div>
              <div class="badge bg-warning rounded-pill">{{ item.count }} đã bán</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Biểu đồ doanh thu -->
    <div class="col-md-12 my-4">
      <div class="card border-0 shadow-sm">
        <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
          <h5 class="card-title fw-bold mb-0">Doanh thu theo tháng</h5>
        </div>
        <div class="card-body">
          <div class="chart-container" style="height: 300px;">
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
import { ref, onMounted, watch } from 'vue';
import axiosClient from '../../util/axiosClient';
import { useRoute, useRouter } from 'vue-router';
const router = useRouter();
const route=useRoute();
const startDate = ref<string>(route.query.startDate as string || '');
const endDate = ref<string>(route.query.endDate as string || '');
const dashboard = ref<any>({});
const productSell = ref<any[]>([]);
const productCount = ref<number>(0);

const fetchData = async () => {
  try {
    const response = await axiosClient.get('/admin/dashboard',{
      params:{
        startDate:startDate.value,
        endDate:endDate.value
      }
    });
    if (response.data.status) {
      dashboard.value = response.data.data.dashboard;
      productSell.value = response.data.data.productSell;
      productCount.value = response.data.data.productCount;
    }
  } catch (error) {
    console.error('Lỗi khi tải dữ liệu:', error);
  }
};

const formatCurrency = (value: number | null | undefined) => {
  if (typeof value !== 'number') return '0đ';
  return value.toLocaleString('vi-VN') + 'đ';
};
watch([startDate, endDate], () => {
    router.push({
        query: {
            startDate: startDate.value || '',
            endDate: endDate.value || '',
        },
    });
    fetchData();
});
onMounted(() => {
  fetchData();
});
</script>
