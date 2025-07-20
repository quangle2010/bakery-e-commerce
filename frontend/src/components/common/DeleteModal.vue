<template>
  <teleport to="body">
    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      aria-labelledby="deleteModalLabel"
      ref="modalRef"
    >
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
            <button
              type="button"
              class="btn btn-danger"
              @click="confirmDelete"
            >
              Xóa
            </button>
          </div>
        </div>
      </div>
    </div>
  </teleport>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { Modal } from 'bootstrap';

const modalRef = ref<HTMLElement | null>(null);
let modalInstance: InstanceType<typeof Modal> | null = null;

const itemId = ref<number | null>(null);

// Setup the modal instance after the DOM is mounted
onMounted(() => {
  if (modalRef.value) {
    modalInstance = Modal.getOrCreateInstance(modalRef.value);
  }
});

// Open modal and set item ID
const openModal = (id: number) => {
  itemId.value = id;
  modalInstance?.show();
};

// Close modal and remove focus
const closeModal = () => {
  // Remove focus from the button to avoid accessibility warning
  if (document.activeElement instanceof HTMLElement) {
    document.activeElement.blur();
  }

  modalInstance?.hide();
};

// Handle delete confirmation
const confirmDelete = () => {
  console.log('Xóa ID:', itemId.value);
  // Call your API or emit event to parent here
  closeModal(); // Close the modal after action
};

// Expose method for parent components
defineExpose({
  openModal,
});
</script>
