<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useAuthStore } from '../../store/auth';
const auth = useAuthStore();
const loginForm = reactive({
    fullName: auth.user?.fullName || '',
    email: auth.user?.email || '',
    phone: auth.user?.phone || '',
    address: auth?.user?.address || ''
});
const errors = reactive({
    fullName: '',
    email: '',
    phone: '',
    address: '',
    general: ''
});
const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;

    // Reset errors
    errors.fullName = '';
    errors.email = '';
    errors.phone = '';
    errors.address = '';
    errors.general = '';

    // Kiểm tra fullName
    if (!loginForm.fullName || !loginForm.fullName.trim()) {
        errors.fullName = 'Vui lòng nhập họ và tên';
        isValid = false;
    }

    // Kiểm tra email
    if (!loginForm.email || !loginForm.email.trim()) {
        errors.email = 'Vui lòng nhập email';
        isValid = false;
    } else if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(loginForm.email)) {
        errors.email = 'Email không hợp lệ';
        isValid = false;
    }

    // Kiểm tra phoneNumber
    if (!loginForm.phone || !loginForm.phone.trim()) {
        errors.phone = 'Vui lòng nhập số điện thoại';
        isValid = false;
    }

    // Kiểm tra address (nếu cần)
    if (!loginForm.address || !loginForm.address.trim()) {
        errors.address = 'Vui lòng nhập địa chỉ';
        isValid = false;
    }

    return isValid;
};

const handleSubmit = async () => {
    if (!validateForm()) return;
    try {
        isLoading.value = true;
        
    } catch (error) {

    }
}
</script>

<template>
    <div class="card mb-4 border w-100">
        <div class="d-flex align-items-center p-4 bg-light border-bottom ">
            <h6 class="fw-bold">Thông tin tài khoản</h6>
        </div>
        <div class="card-body flex-grow-1">

            <form @submit.prevent="handleSubmit">
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-person text-muted"></i>
                        </span>
                        <input type="text" v-model="loginForm.fullName" class="form-control border-start-0"
                            name="fullName" placeholder="Họ và tên">
                    </div>
                    <div v-if="errors.fullName" class="invalid-feedback d-block">
                        {{ errors.fullName }}
                    </div>
                </div>
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-envelope text-muted"></i>
                        </span>
                        <input type="email" v-model="loginForm.email" class="form-control border-start-0" name="email"
                            placeholder="Email">
                    </div>
                    <div v-if="errors.email" class="invalid-feedback d-block">
                        {{ errors.email }}
                    </div>
                </div>
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-telephone text-muted"></i>
                        </span>
                        <input type="tel" class="form-control border-start-0" v-model="loginForm.phone" name="phone"
                            placeholder="Số điện thoại">
                    </div>
                    <div v-if="errors.phone" class="invalid-feedback d-block">
                        {{ errors.phone }}
                    </div>
                </div>
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-geo-alt text-muted"></i>
                        </span>
                        <input type="text" class="form-control border-start-0" v-model="loginForm.address"
                            name="address" placeholder="Địa chỉ">
                    </div>
                    <div v-if="errors.address" class="invalid-feedback d-block">
                        {{ errors.address }}
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-sm shadow">
                    <i class="bi bi-save me-2"></i>Lưu thông tin
                </button>
            </form>
        </div>
    </div>

</template>
