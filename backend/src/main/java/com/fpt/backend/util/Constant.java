package com.fpt.backend.util;

public class Constant {

    public static final Integer ROLE_USER = 0;// Người dùng thường
    public static final Integer ROLE_ADMIN = 1;// Quản trị viên

    public static final String ROLE_USER_NAME = "ROLE_USER";// Người dùng thường
    public static final String ROLE_ADMIN_NAME = "ROLE_ADMIN";// Quản trị viên

    public static final Integer ORDER_STATUS_CANCELLED = -1; // Đã hủy
    public static final Integer ORDER_STATUS_PENDING = 0; // Chờ xác nhận

    public static final Integer ORDER_STATUS_CONFIRMED = 1; // Đã xác nhận
    public static final Integer ORDER_STATUS_SHIPPING = 2; // Đang giao
    public static final Integer ORDER_STATUS_COMPLETED = 3; // Đã giao

    public static final Integer PRODUCT_STATUS_ACTIVE = 1; // Sản phẩm đang hoạt động
    public static final Integer PRODUCT_STATUS_INACTIVE = 0; // Sản phẩm không hoạt động

    public static final Integer ORDER_PAYMENT_COD = 0; // Thanh toán khi nhận hàng
    public static final Integer ORDER_PAYMENT_ONLINE = 1; // Thanh toán trực tuyến

    public static final String ORDER_PAYMENT_STATUS_PENDING = "Chưa thanh toán"; // Thanh toán chưa hoàn thành
    public static final String ORDER_PAYMENT_STATUS_SUCCESS = "Đã thanh toán"; // Thanh toán thành công

    public static final int FLAVOR = 1; // Hương vị
    public static final int CATEGORY = 2; // Loại
}
