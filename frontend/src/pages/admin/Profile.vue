<template>
    <div class="row">
        <div class="col-md-6 mb-4">
          <Profile  />
        </div>
        <div class="col-md-6 mb-4">
          <ChangePassword/>
        </div>
    </div>
</template>

<script setup lang="ts">
import ChangePassword from '../user/ChangePassword.vue';
import Profile from '../user/Profile.vue';

// Không có logic, chỉ hiển thị giao diện
</script>
