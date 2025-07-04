<template>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h4 class="fw-bold mb-0">Quản lý danh mục</h4>
            <small class="text-muted">Danh sách các danh mục sản phẩm</small>
        </div>
        <div class="d-flex gap-2">
            <button class="btn btn-sm btn-primary">
                <i class="bi bi-plus-circle me-1"></i>Thêm danh mục
            </button>
        </div>
    </div>
    <div class="card">
        <div class="card-body p-1">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th scope="col">Tên danh mục</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col" class="text-center">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Example row -->
                        <tr>
                            <td>Danh mục 1</td>
                            <td>Mô tả ngắn về danh mục 1</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-outline-secondary me-1">
                                    <i class="bi bi-pencil"></i> Sửa
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Xóa
                                </button>
                            </td>
                        </tr>
                          <tr>
                            <td>Danh mục 1</td>
                            <td>Mô tả ngắn về danh mục 1</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-outline-secondary me-1">
                                    <i class="bi bi-pencil"></i> Sửa
                                </button>
                                <button class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Xóa
                                </button>
                            </td>
                        </tr>
                          <tr>
                            <td>Danh mục 1</td>
                            <td>Mô tả ngắn về danh mục 1</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-outline-secondary me-1">
                                    <i class="bi bi-pencil"></i> Sửa
                                </button>
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
<script lang="ts" setup></script>