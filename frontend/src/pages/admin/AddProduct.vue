<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';
import axiosClient from '../../util/axiosClient';
import { showError, showSuccess } from '../../util/useAlert';

const flavors = ref<any[]>([]);
const categories = ref<any[]>([]);

const formSubmit = reactive({
    name: '',
    category: '',
    flavor: '',
    image: null as File | null,
    price: '',
    quantity: '',
    weight: '', // 👈 thêm
    description: ''
});

const errors = reactive({
    name: '',
    category: '',
    flavor: '',
    image: '',
    price: '',
    quantity: '',
    weight: '', // 👈 thêm
    general: ''
});

const isLoading = ref(false);

const validateForm = () => {
    let isValid = true;

    errors.name = '';
    errors.category = '';
    errors.flavor = '';
    errors.image = '';
    errors.price = '';
    errors.quantity = '';
    errors.weight = '';
    errors.general = '';

    if (!formSubmit.name.trim()) {
        errors.name = "Vui lòng nhập tên sản phẩm";
        isValid = false;
    }

    if (!formSubmit.category) {
        errors.category = "Vui lòng chọn danh mục";
        isValid = false;
    }

    if (!formSubmit.flavor) {
        errors.flavor = "Vui lòng chọn hương vị";
        isValid = false;
    }

    if (!formSubmit.image) {
        errors.image = "Vui lòng chọn hình ảnh";
        isValid = false;
    }

    if (!formSubmit.price || Number(formSubmit.price) <= 0) {
        errors.price = "Giá bán phải lớn hơn 0";
        isValid = false;
    }

    if (!formSubmit.quantity || Number(formSubmit.quantity) < 0) {
        errors.quantity = "Số lượng phải là số không âm";
        isValid = false;
    }

    if (!formSubmit.weight || Number(formSubmit.weight) <= 0) {
        errors.weight = "Cân nặng phải lớn hơn 0";
        isValid = false;
    }

    return isValid;
};

const handleSubmit = async () => {
    if (!validateForm()) return;

    try {
        isLoading.value = true;

        const formData = new FormData();
        formData.append('name', formSubmit.name);
        formData.append('image', formSubmit.image as Blob);
        formData.append('price', formSubmit.price);
        formData.append('quantity', formSubmit.quantity);
        formData.append('weight', formSubmit.weight);
        formData.append('description', formSubmit.description || '');

        // 👇 Gộp category và flavor thành attributeOptions
        const attributeOptions = [formSubmit.category, formSubmit.flavor];
        attributeOptions.forEach((optionId) => {
            formData.append('attributeOptions', optionId); 
        });
        for (const pair of formData.entries()) {
            console.log(pair[0], pair[1]);
        }
        const { data } = await axiosClient.post('/admin/product', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });

        if (data.status === true) {
            showSuccess("Lưu thành công", data.message);
        } else {
            showError("Lưu thất bại");
        }
    } catch (error) {
        if (axios.isAxiosError(error)) {
            const errorMessage = error.response?.data?.message || 'Có lỗi xảy ra';
            showError(errorMessage);
        } else {
            errors.general = 'Có lỗi xảy ra. Vui lòng thử lại.';
            showError(errors.general);
        }
    } finally {
        isLoading.value = false;
    }
};


const fetchOptions = async () => {
    try {
        const reps = await axiosClient.get('/admin/product/options');
        if (reps.data.status) {
            flavors.value = reps.data.data.flavors;
            categories.value = reps.data.data.categories;
        } else {
            flavors.value = [];
            categories.value = [];
        }
    } catch (error) {
        console.error(error);
        flavors.value = [];
        categories.value = [];
    }
};

onMounted(() => {
    fetchOptions();
});
</script>

<template>
    <div class="card">
        <div class="card-header bg-white d-flex justify-content-between align-items-center">
            <h3>Thêm sản phẩm</h3>
        </div>
        <div class="card-body">
            <form @submit.prevent="handleSubmit">
                <div class="row g-3">
                    <!-- Cột trái -->
                    <div class="col-md-6">
                        <!-- Tên sản phẩm -->
                        <div class="mb-3">
                            <label for="productName" class="form-label">Tên sản phẩm <span
                                    class="text-danger">*</span></label>
                            <input v-model="formSubmit.name" type="text" class="form-control" id="productName">
                            <div class="text-danger">{{ errors?.name }}</div>
                        </div>

                        <!-- Danh mục -->
                        <div class="mb-3">
                            <label for="productCategory" class="form-label">Danh mục <span
                                    class="text-danger">*</span></label>
                            <select v-model="formSubmit.category" class="form-select" id="productCategory">
                                <option value="" disabled>Chọn danh mục</option>
                                <option v-for="cate in categories" :key="cate.id" :value="cate.id">{{ cate.name }}
                                </option>
                            </select>
                            <div class="text-danger">{{ errors.category }}</div>
                        </div>

                        <!-- Hương vị -->
                        <div class="mb-3">
                            <label for="productFlavor" class="form-label">Hương vị <span
                                    class="text-danger">*</span></label>
                            <select v-model="formSubmit.flavor" class="form-select" id="productFlavor">
                                <option value="" disabled>Chọn hương vị</option>
                                <option v-for="fla in flavors" :key="fla.id" :value="fla.id">{{ fla.name }}</option>
                            </select>
                            <div class="text-danger">{{ errors.flavor }}</div>
                        </div>

                        <!-- Hình ảnh -->
                        <div class="mb-3">
                            <label for="productImage" class="form-label">Hình ảnh <span
                                    class="text-danger">*</span></label>
                            <input type="file" class="form-control" id="productImage" accept="image/*"
                                @change="(e) => formSubmit.image = (e.target as HTMLInputElement).files?.[0] || null">
                            <div class="text-danger">{{ errors.image }}</div>
                        </div>
                    </div>

                    <!-- Cột phải -->
                    <div class="col-md-6">
                        <!-- Giá bán -->
                        <div class="mb-3">
                            <label for="productPrice" class="form-label">Giá bán (VNĐ) <span
                                    class="text-danger">*</span></label>
                            <div class="input-group">
                                <input v-model="formSubmit.price" type="number" class="form-control" id="productPrice"
                                    min="0">
                                <span class="input-group-text">₫</span>
                            </div>
                            <div class="text-danger">{{ errors.price }}</div>
                        </div>

                        <!-- Số lượng -->
                        <div class="mb-3">
                            <label for="productQuantity" class="form-label">Số lượng <span
                                    class="text-danger">*</span></label>
                            <input v-model="formSubmit.quantity" type="number" class="form-control" id="productQuantity"
                                min="0">
                            <div class="text-danger">{{ errors.quantity }}</div>
                        </div>

                        <!-- Cân nặng -->
                        <div class="mb-3">
                            <label for="productWeight" class="form-label">Cân nặng (gram) <span
                                    class="text-danger">*</span></label>
                            <div class="input-group">
                                <input v-model="formSubmit.weight" type="number" class="form-control" id="productWeight"
                                    min="0">
                                <span class="input-group-text">g</span>
                            </div>
                            <div class="text-danger">{{ errors.weight }}</div>
                        </div>
                    </div>

                    <!-- Mô tả -->
                    <div class="col-12">
                        <div class="mb-3">
                            <label for="productDescription" class="form-label">Mô tả sản phẩm</label>
                            <textarea v-model="formSubmit.description" class="form-control" id="productDescription"
                                rows="3"></textarea>
                        </div>
                    </div>
                </div>

                <!-- Nút -->
                <div class="d-flex justify-content-end gap-2 mt-4">
                    <router-link to="/admin/products" class="btn btn-outline-secondary">Quay lại</router-link>
                    <button type="submit" class="btn btn-primary" :disabled="isLoading">
                        {{ isLoading ? 'Đang lưu...' : 'Lưu' }}
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
