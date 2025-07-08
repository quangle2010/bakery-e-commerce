<script lang="ts" setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import { showError, showSuccess } from '../../util/useAlert';
import Loading from '../../components/common/Loading.vue';

const router = useRouter();
const formSubmit = reactive({
    fullName: '',
    email: '',
    password: '',
    confirmPassword: ''
});

const errors = reactive({
    fullName: '',
    email: '',
    password: '',
    confirmPassword: '',
    general: ''
});

const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;
    errors.fullName = '',
        errors.email = '';
    errors.password = '';
    errors.confirmPassword = '',
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

    if (!formSubmit.email.trim()) {
        errors.email = 'Vui lòng nhập email';
        isValid = false;
    } else if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(formSubmit.email)) {
        errors.email = 'Email không hợp lệ';
        isValid = false;
    }

    if (!formSubmit.password) {
        errors.password = 'Vui lòng nhập mật khẩu';
        isValid = false;
    }else if (formSubmit.password.length < 6) {
        errors.password = 'Mật khẩu phải có ít nhất 6 ký tự';
        isValid = false;
    }


    if (!formSubmit.confirmPassword) {
        errors.confirmPassword = 'Vui lòng nhập mật khẩu xác nhận';
        isValid = false;
    }
    if (!(formSubmit.confirmPassword === formSubmit.password)) {
        errors.confirmPassword = 'Mật khẩu không trùng khớp';
        isValid = false;
    }


    return isValid;
};


const handleRegister = async () => {
    if (!validateForm()) return;

    try {
        isLoading.value = true;

        const { data } = await axios.post("http://localhost:8080/register", formSubmit);
        if (data.status === true) {
            router.push("/login");
            showSuccess("Chào mừng bạn đến với Fshop", "Đăng ký thành công");
        } else {
            showError("Đăng ký thất bại");
        }
    } catch (error: any) {
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data?.message;
            setError(errorMessage);
        } else {
            errors.general = 'Có lỗi xảy ra. Vui lòng thử lại.';
            showError(errors.general);
        }

    } finally {
        isLoading.value = false;
    }
};

const errorMap: Record<string, keyof typeof errors> = {
    "Tên không được chứa ký tự số hoặc ký tự đặc biệt": "fullName",
    "Tên không được để trống": "fullName",
    "Email không được bỏ trống": "email",
    "Email không tồn tại": "email",
    "Email đã được sử dụng": "email",
    "Mật khẩu không được để trống": "password",
    "Mật khẩu phải từ 6 đến 20 ký tự": "password",
    "Xác nhận mật khẩu không được để trống": "confirmPassword",
    "Mật khẩu và xác nhận mật khẩu không khớp": "confirmPassword",
};

function setError(message: string) {
    const field = errorMap[message];
    if (field) {
        errors[field] = message;
    }
}


</script>


<template>
    <div v-if="isLoading">
        <Loading />
    </div>
    <div class="row justify-content-center">
        <div class="col-12 col-sm-10 col-md-8 col-lg-6">
            <div class="card mb-4 border rounded-4">
                <div class="card-header text-center text-white bg-primary py-4 border-0 rounded-top-4">
                    <h2 class="fw-bold mb-2">Fshop</h2>
                    <p class="mb-3">Cửa hàng bánh kẹo, bánh ngọt và quà tặng ngọt ngào hàng đầu</p>
                </div>
                <div class="card-body p-4">
                    <div class="text-center mb-4">
                        <h3 class="fw-bold">Đăng ký</h3>
                        <p class="text-muted">Tạo tài khoản mới tại Fshop!</p>
                    </div>
                    <form @submit.prevent="handleRegister">

                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-person text-muted"></i>
                                </span>
                                <input type="text" class="form-control border-start-0" name="fullName"
                                    placeholder="Họ và tên" v-model="formSubmit.fullName">
                            </div>
                            <small class="text-danger fw-bold">
                                {{ errors?.fullName }}
                            </small>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-envelope text-muted"></i>
                                </span>
                                <input type="email" class="form-control border-start-0" name="email" placeholder="Email"
                                    v-model="formSubmit.email">
                            </div>
                            <small class="text-danger fw-bold">
                                {{ errors?.email }}
                            </small>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock text-muted"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" name="password"
                                    placeholder="Mật khẩu" v-model="formSubmit.password">
                            </div>
                            <small class="text-danger fw-bold">
                                {{ errors?.password }}
                            </small>
                        </div>
                        <div class="mb-3">
                            <div class="input-group">
                                <span class="input-group-text bg-light border-end-0">
                                    <i class="bi bi-lock text-muted"></i>
                                </span>
                                <input type="password" class="form-control border-start-0" name="confirmPassword"
                                    placeholder="Xác nhận mật khẩu" v-model="formSubmit.confirmPassword">
                            </div>
                            <small class="text-danger fw-bold ">
                                {{ errors?.confirmPassword }}
                            </small>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-sm shadow">
                                Đăng ký
                            </button>
                        </div>

                        <div class="mt-4 text-center">
                            <p>
                                Đã có tài khoản?
                                <router-link to="/login" class="text-decoration-none fw-bold">Đăng
                                    nhập</router-link>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>



</template>
