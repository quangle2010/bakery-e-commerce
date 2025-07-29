<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useAuthStore } from '../../store/auth';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import axios from 'axios';
import Loading from '../../components/common/Loading.vue';
import router from '../../router';
const auth = useAuthStore();
const formSubmit = reactive({
    passwordOld: '',
    passwordNew: '',
    confirmPassword: ''
});
const errors = reactive({
    passwordOld: '',
    passwordNew: '',
    confirmPassword: '',
    general: ''
});
const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;
    errors.passwordOld = '';
    errors.passwordNew = '';
    errors.confirmPassword = '';
    errors.general = '';

    if (!formSubmit.passwordOld) {
        errors.passwordOld = 'Vui lòng nhập mật khẩu cũ';
        isValid = false;
    }
    if (!formSubmit.passwordNew) {
        errors.passwordNew = 'Vui lòng nhập mật khẩu mới';
        isValid = false;
    } else if (formSubmit.passwordNew.length < 6) {
        errors.passwordNew = 'Mật khẩu mới phải có ít nhất 6 ký tự';
        isValid = false;
    }
    if (!formSubmit.confirmPassword) {
        errors.confirmPassword = 'Vui lòng nhập mật khẩu xác nhận';
        isValid = false;
    }
    if (!(formSubmit.confirmPassword === formSubmit.passwordNew)) {
        errors.confirmPassword = 'Mật khẩu không trùng khớp';
        isValid = false;
    }

    return isValid;
};

const handleSubmit = async () => {
    if (!validateForm()) return;
    try {
        isLoading.value = true;
        const { data } = await axiosClient.put("/auth/change-password", formSubmit);
        if (data.status === true) {
            showSuccess("Cập nhật thông tin", data.message);
            auth.logout();
            router.push("/login");
        } else {
            showError("Cập nhật thất bại")
        }

    } catch (error) {
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data?.message;
            if (errorMessage === "Mật khẩu cũ không đúng") {
                errors.passwordOld = errorMessage;
            } else {
                showError(errorMessage);
            }
        } else {
            errors.general = 'Có lỗi xảy ra. Vui lòng thử lại.';
            showError(errors.general);
        }
    } finally {
        isLoading.value = false;
    }
}
</script>
<template>

    <div class="card mb-4 w-100 h-100">
        <div v-if="isLoading">
            <Loading />
        </div>
        <div class="d-flex align-items-center p-4 bg-light border-bottom ">
            <h6 class="fw-bold">Đổi mật khẩu</h6>
        </div>
        <div class="card-body flex-grow-1">
            <form @submit.prevent="handleSubmit">
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-lock-fill text-muted"></i>
                        </span>
                        <input type="password" class="form-control border-start-0" name="passwordOld"
                            placeholder="Mật khẩu cũ" v-model="formSubmit.passwordOld">
                    </div>
                    <small class="text-danger fw-bold">
                        {{ errors?.passwordOld }}
                    </small>
                </div>
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-lock-fill text-muted"></i>
                        </span>
                        <input type="password" class="form-control border-start-0" name="passwordNew"
                            placeholder="Mật khẩu mới" v-model="formSubmit.passwordNew">
                    </div>
                    <small class="text-danger fw-bold">
                        {{ errors?.passwordNew }}
                    </small>
                </div>
                <div class="mb-3">
                    <div class="input-group">
                        <span class="input-group-text bg-light border-end-0">
                            <i class="bi bi-lock-fill text-muted"></i>
                        </span>
                        <input type="password" class="form-control border-start-0" name="confirmPassword"
                            placeholder="Xác nhận mật khẩu mới" v-model="formSubmit.confirmPassword">
                    </div>
                    <small class="text-danger fw-bold">
                        {{ errors?.confirmPassword }}
                    </small>
                </div>

                <button type="submit" class="btn btn-primary btn-sm shadow">
                    <i class="bi bi-save me-2"></i>Đổi mật khẩu
                </button>
            </form>
        </div>
    </div>

</template>
