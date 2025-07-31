<template>
    <div class="row justify-content-center h-100">
        <div class="col-md-4 col-lg-3 mb-4 mb-md-0">
            <Sidebar/>
        </div>
        <div class="col-md-8 col-lg-9">
           
             <router-view />
        </div>
    </div>
</template>
<script lang="ts" setup>

import Sidebar from '../components/user/Sidebar.vue';

</script>

