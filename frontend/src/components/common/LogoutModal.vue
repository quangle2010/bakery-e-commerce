<template>
  <teleport to="body">
    <div
      class="modal fade"
      id="logoutModal"
      tabindex="-1"
      aria-labelledby="logoutModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Xác nhận đăng xuất</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
          </div>
          <div class="modal-body">
            Bạn có chắc chắn muốn đăng xuất không?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" v-on:click="handleLogout">Đăng xuất</button>
          </div>
        </div>
      </div>
    </div>
  </teleport>
</template>

<script setup lang="ts">
import { useAuthStore } from '../../store/auth';
import { useRouter } from 'vue-router';
import { showSuccess } from '../../util/useAlert';
const router=useRouter();
const auth=useAuthStore();
const handleLogout = ()=>{
  auth.logout();
  router.push("/login");
  showSuccess("Hẹn gặp lại","Đăng xuất thành công!");
}
</script>
