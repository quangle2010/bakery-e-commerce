<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useAuthStore } from '../../store/auth';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';
import axios from 'axios';
import Loading from '../../components/common/Loading.vue';
const auth = useAuthStore();
const formSubmit = reactive({
    fullName: auth.user?.fullName || '',
    phone: auth.user?.phone || '',
    address: auth?.user?.address || ''
});
const errors = reactive({
    fullName: '',
    phone: '',
    address: '',
    general: ''
});
const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;
    errors.fullName = '';
    errors.phone = '';
    errors.address = '';
    errors.general = '';
    if (!formSubmit.fullName.trim()) {
        errors.fullName = "Vui lòng nhập Họ và tên";
        isValid = false;
    } else if (/\d/.test(formSubmit.fullName)) {
        errors.fullName = "Họ và tên không được chứa số";
        isValid = false;
    } else if (!(/^[a-zA-ZÀ-ỹ\s]+$/u.test(formSubmit.fullName))) {
        errors.fullName = "Họ và tên không được chứa lý tự đặt biệt";
        isValid = false;
    }

    if (!formSubmit.phone || !formSubmit.phone.trim()) {
        errors.phone = 'Vui lòng nhập số điện thoại';
        isValid = false;
    } else if (!(/^0\d{9}$/.test(formSubmit.phone))) {
        errors.phone = 'Số điện thoại phải gồm 10 số và bắt đầu bằng số 0';
        isValid = false;
    }

    if (!formSubmit.address || !formSubmit.address.trim()) {
        errors.address = 'Vui lòng nhập địa chỉ';
        isValid = false;
    }

    return isValid;
};

const handleSubmit = async () => {
    if (!validateForm()) return;
    try {
        isLoading.value = true;
        const { data } = await axiosClient.put("/auth", formSubmit);
        if (data.status === true) {
            showSuccess("Cập nhật thông tin", data.message);
        } else {
            showError("Cập nhật thất bại")
        }

    } catch (error) {
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data?.message;
            showError(errorMessage);
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
                <h6 class="fw-bold">Thông tin tài khoản</h6>
            </div>
            <div class="card-body flex-grow-1">

                <form @submit.prevent="handleSubmit">
                    <div class="mb-3">
                        <div class="input-group">
                            <span class="input-group-text bg-light border-end-0">
                                <i class="bi bi-person text-muted"></i>
                            </span>
                            <input type="text" v-model="formSubmit.fullName" class="form-control border-start-0"
                                name="fullName" placeholder="Họ và tên">
                        </div>
                        <small class="text-danger fw-bold">
                            {{ errors?.fullName }}
                        </small>
                    </div>

                    <div class="mb-3">
                        <div class="input-group">
                            <span class="input-group-text bg-light border-end-0">
                                <i class="bi bi-telephone text-muted"></i>
                            </span>
                            <input type="tel" class="form-control border-start-0" v-model="formSubmit.phone"
                                name="phone" placeholder="Số điện thoại">
                        </div>
                        <small class="text-danger fw-bold">
                            {{ errors?.phone }}
                        </small>
                    </div>
                    <div class="mb-3">
                        <div class="input-group">
                            <span class="input-group-text bg-light border-end-0">
                                <i class="bi bi-geo-alt text-muted"></i>
                            </span>
                            <input type="text" class="form-control border-start-0" v-model="formSubmit.address"
                                name="address" placeholder="Địa chỉ">
                        </div>
                        <small class="text-danger fw-bold">
                            {{ errors?.address }}
                        </small>
                    </div>

                    <button type="submit" class="btn btn-primary btn-sm shadow">
                        <i class="bi bi-save me-2"></i>Lưu thông tin
                    </button>
                </form>
            </div>
    </div>

</template>
