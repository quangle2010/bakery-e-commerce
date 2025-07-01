<template>
  <div class="min-vh-100 d-flex flex-column">
    <Header />
    <main class="flex-grow-1 py-4">
      <div class="container">
        <router-view />
      </div>
    </main>
    <Footer />
  </div>
</template>

<script setup lang="ts">
import Header from '../components/user/Header.vue'
import Footer from '../components/user/Footer.vue'
</script>


