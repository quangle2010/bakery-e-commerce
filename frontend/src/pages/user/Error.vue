<template>
  <div class="error-container">
    <div class="error-content">
      <div class="error-icon">
        <i class="bi bi-exclamation-triangle-fill"></i>
      </div>
      <h1 class="error-title">Oops!</h1>
      <h2 class="error-subtitle">Đã xảy ra lỗi</h2>
      <p class="error-message">Trang bạn đang tìm kiếm không tồn tại hoặc đã được di chuyển.</p>
      <div class="error-actions">
        <button @click="goBack" class="btn-back">
          <i class="bi bi-arrow-left"></i> Quay lại 
        </button>
       
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Error page component
const goBack = () => {
  window.history.back();
};
</script>

<style scoped>
.error-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
  padding: 2rem;
  background-color: #f8f9fa;
}

.error-content {
  text-align: center;
  max-width: 600px;
  padding: 3rem;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.error-icon {
  font-size: 5rem;
  color: #dc3545;
  margin-bottom: 1.5rem;
}

.error-title {
  font-size: 3.5rem;
  font-weight: 700;
  color: #343a40;
  margin-bottom: 0.5rem;
}

.error-subtitle {
  font-size: 1.8rem;
  color: #6c757d;
  margin-bottom: 1.5rem;
}

.error-message {
  font-size: 1.2rem;
  color: #6c757d;
  margin-bottom: 2rem;
}

.error-actions {
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  gap: 1rem;
}

.btn-home, .btn-back {
  display: inline-block;
  padding: 0.8rem 1.5rem;
  border-radius: 5px;
  text-decoration: none;
  font-weight: 600;
  transition: all 0.3s ease;
  border: none;
  cursor: pointer;
}

.btn-home {
  background-color: #007bff;
  color: white;
}

.btn-back {
  background-color: #6c757d;
  color: white;
}

.btn-home:hover, .btn-back:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn-home:hover {
  background-color: #0056b3;
}

.btn-back:hover {
  background-color: #5a6268;
}
</style>