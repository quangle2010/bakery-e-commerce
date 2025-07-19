<template>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h4 class="fw-bold mb-0">Quản lý sản phẩm</h4>
            <small class="text-muted">Danh sách các sản phẩm</small>
        </div>
        <div class="d-flex gap-2">
            <router-link to="/admin/add-product" class="btn btn-sm btn-primary">
                <i class="bi bi-plus-circle me-1"></i>Thêm sản phẩm
            </router-link>
        </div>
    </div>
    <div class="card">
        <div class="card-body p-1">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th scope="col">Hình</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col" class="text-center">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Example row -->
                        <tr>
                            <td>Hình</td>
                            <td>Sản phẩm 1</td>
                            <td>10</td>
                            <td>200000</td>
                            <td class="text-center">
                                <router-link to="/admin/edit-product/1" >
                                    <i class="bi bi-pencil"></i> Sửa
                                </router-link>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Xóa
                                </button>
                            </td>
                        </tr>

                        <!-- More rows as needed -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
</script>
