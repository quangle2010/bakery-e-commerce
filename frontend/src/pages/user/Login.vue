<script lang="ts" setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { useAuthStore } from '../../store/auth';
import { showError, showSuccess } from '../../util/useAlert';

const router = useRouter();
const auth = useAuthStore();
const loginForm = reactive({
    email: '',
    password: ''
});

const errors = reactive({
    email: '',
    password: '',
    general: ''
});

const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;
    errors.email = '';
    errors.password = '';
    errors.general = '';

    if (!loginForm.email.trim()) {
        errors.email = 'Vui lòng nhập email';
        isValid = false;
    } else if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(loginForm.email)) {
        errors.email = 'Email không hợp lệ';
        isValid = false;
    }

    if (!loginForm.password) {
        errors.password = 'Vui lòng nhập mật khẩu';
        isValid = false;
    }

    return isValid;
};

const handleLogin = async () => {
    if (!validateForm()) return;

    try {
        isLoading.value = true;
        await auth.login(loginForm.email, loginForm.password);
        console.log(auth?.user);
        if (auth.user?.role === "ROLE_ADMIN") {
           
            router.push("/admin/dashboard")

        } else {
            router.push("/home")
        }
        showSuccess("Chào mừng bạn đến với Fshop","Đăng nhập thành công!")

    } catch (error: any) {
        if (axios.isAxiosError(error)) {
            errors.general = error.response?.data?.message || 'Đăng nhập thất bại';
        } else {
            errors.general = 'Có lỗi xảy ra. Vui lòng thử lại.';
        }
        showError(errors.general );
    } finally {
        isLoading.value = false;
    }
};
</script>

<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card mb-4 border rounded-4">
                    <div class="card-header text-center text-white bg-primary py-4 border-0 rounded-top-4">
                        <h2 class="fw-bold mb-2">Fshop</h2>
                        <p class="mb-3">Thế giới bánh ngọt, kẹo ngon và bánh quy tuyệt vời</p>
                    </div>
                    <div class="card-body p-4">
                        <div class="text-center mb-4">
                            <h3 class="fw-bold">Đăng nhập</h3>
                            <p class="text-muted">Chào mừng bạn quay lại tiệm bánh Fshop!</p>
                        </div>
                        <form @submit.prevent="handleLogin">
                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text bg-light border-end-0">
                                        <i class="bi bi-envelope text-muted"></i>
                                    </span>
                                    <input type="email" class="form-control border-start-0" v-model="loginForm.email"
                                        placeholder="Email" :class="{ 'is-invalid': errors.email }" />
                                </div>
                                <div v-if="errors.email" class="invalid-feedback d-block">
                                    {{ errors.email }}
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="input-group">
                                    <span class="input-group-text bg-light border-end-0">
                                        <i class="bi bi-lock text-muted"></i>
                                    </span>
                                    <input type="password" class="form-control border-start-0"
                                        v-model="loginForm.password" placeholder="Mật khẩu"
                                        :class="{ 'is-invalid': errors.password }" />
                                </div>
                                <div v-if="errors.password" class="invalid-feedback d-block">
                                    {{ errors.password }}
                                </div>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary btn-sm shadow" :disabled="isLoading">
                                    <span v-if="isLoading" class="spinner-border spinner-border-sm me-2" role="status"
                                        aria-hidden="true"></span>
                                    <i v-else class="fas fa-sign-in-alt me-2"></i>
                                    {{ isLoading ? 'Đang xử lý...' : 'Đăng nhập' }}
                                </button>
                            </div>

                            <div class="my-4 d-flex justify-content-between align-items-center">
                                <div>
                                    <p>Chưa có tài khoản?
                                        <router-link to="/register" class="text-decoration-none fw-bold">Đăng ký
                                            ngay</router-link>
                                    </p>
                                </div>
                                <div>
                                    <p><router-link to="/forgot-password" class="text-decoration-none fw-bold">Quên mật
                                            khẩu?</router-link></p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
