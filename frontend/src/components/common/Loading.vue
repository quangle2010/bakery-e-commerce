<template>
  <div v-if="loading" class="spinner-overlay">
    <div class="spinner-border" style="width: 3rem; height: 3rem;" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
const loading = ref(true)
</script>

<style scoped>
.spinner-overlay {
  position: fixed;
  inset: 0; 
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(255, 255, 255, 0.5); 
  z-index: 9999;
}
</style>
