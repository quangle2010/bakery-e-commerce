<template>
  <nav aria-label="Page navigation" class="text-center my-3">
    <ul class="pagination justify-content-center">
      <!-- Trang đầu -->
      <li class="page-item me-1" :class="{ disabled: currentPage === 1 }" @click="handlePageChange(1)">
        <a href="#" class="page-link" @click.prevent>
          <i class="p-0 bi bi-chevron-bar-left"></i>
        </a>
      </li>
      <!-- Trang trước -->
      <li class="page-item me-1" :class="{ disabled: currentPage === 1 }" @click="handlePageChange(currentPage - 1)">
        <a href="#" class="page-link" @click.prevent>
          <i class="p-0 bi bi-chevron-double-left"></i>
        </a>
      </li>
      <!-- Các số trang -->
      <li v-for="(page, index) in pages" :key="index"
          class="page-item me-1"
          :class="{ active: page === currentPage, disabled: page === '...' }"
          @click="page !== '...' && handlePageChange(page)">
        <a href="#" class="page-link" @click.prevent>{{ page }}</a>
      </li>
      <!-- Trang tiếp theo -->
      <li class="page-item me-1" :class="{ disabled: currentPage === totalPages }" @click="handlePageChange(currentPage + 1)">
        <a href="#" class="page-link" @click.prevent>
          <i class="p-0 bi bi-chevron-bar-right"></i>
        </a>
      </li>
      <!-- Trang cuối -->
      <li class="page-item me-1" :class="{ disabled: currentPage === totalPages }" @click="handlePageChange(totalPages)">
        <a href="#" class="page-link" @click.prevent>
          <i class="p-0 bi bi-chevron-double-right"></i>
        </a>
      </li>
    </ul>
  </nav>
</template>


<script setup>
import { computed, toRefs } from "vue";
import { defineProps } from "vue";
import { useRoute, useRouter } from "vue-router";

const props = defineProps({
  currentPage: {
    type: Number,
    required: true,
  },
  totalPages: {
    type: Number,
    required: true,
  },
});

const emit = defineEmits(["update:currentPage"]);

const { currentPage, totalPages } = toRefs(props);
const router = useRouter();
const route = useRoute();
const handlePageChange = (page) => {
  if (page >= 1 && page <= totalPages.value && page !== currentPage.value) {
    router.push({
      path: route.path,
      query: { ...route.query, page },
    }).catch(() => {});
    emit("update:currentPage", page);
  }
};

const pages = computed(() => {
  const result = [];
  result.push(1);
  if (currentPage.value > 3) result.push("...");
  for (let i = currentPage.value - 1; i <= currentPage.value + 1; i++) {
    if (i > 1 && i < totalPages.value) result.push(i);
  }
  if (currentPage.value < totalPages.value - 2) result.push("...");
  if (totalPages.value > 1) result.push(totalPages.value);
  return result;
});
</script>

<style scoped>
.page-item.disabled .page-link {
  pointer-events: none;
  opacity: 0.6;
  cursor: default;
}
</style>
