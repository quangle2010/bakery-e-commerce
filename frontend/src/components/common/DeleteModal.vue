<template>
  <teleport to="body">
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" ref="modalRef">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Xác nhận xóa</h5>
            <button type="button" class="btn-close" @click="closeModal"></button>
          </div>
          <div class="modal-body">
            Bạn có chắc chắn muốn xóa?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Hủy</button>
            <button type="button" class="btn btn-danger" @click="confirmDelete">Xóa</button>
          </div>
        </div>
      </div>
    </div>
  </teleport>
</template>

<script setup lang="ts">
import { ref, onMounted, defineEmits } from 'vue';
import { Modal } from 'bootstrap';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';

const modalRef = ref<HTMLElement | null>(null);
let modalInstance: InstanceType<typeof Modal> | null = null;

const emit = defineEmits<{
  (e: 'deleted'): void;
}>();

const itemId = ref<number | null>(null);
const deleteUrl = ref<string>('');

// Khởi tạo modal
onMounted(() => {
  if (modalRef.value) {
    modalInstance = Modal.getOrCreateInstance(modalRef.value);
  }
});

const openModal = (id: number, url: string) => {
  itemId.value = id;
  deleteUrl.value = url;
  modalInstance?.show();
};

const closeModal = () => {
  if (document.activeElement instanceof HTMLElement) {
    document.activeElement.blur();
  }
  modalInstance?.hide();
};

const confirmDelete = async () => {
  try {
    const response = await axiosClient.delete(`${deleteUrl.value}/${itemId.value}`);
    if (response.data.status === true) {
      showSuccess(response.data.message);
        console.log('deleted emitted');
      emit('deleted'); // Gửi sự kiện về cha
    } else {
      showError(response.data.message);
    }
  } catch (error) {
    showError('Đã xảy ra lỗi khi xóa sản phẩm');
  }
  closeModal();
};

defineExpose({
  openModal
});
</script>
