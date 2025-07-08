package com.fpt.backend.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class Constant {


    public static final String[] PUBLIC_ROUTER = new String[]{
            "/login",
            "/register",
            "/home",
            "/forgot-password",
            "/products",
            "/logout",
            "/product/{id}",
            "/products/search/**"
    };

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















    // Chuỗi secret dạng Base64 (phải là Base64 hợp lệ!)
    public static final String BASE64_SECRET_KEY = "IUhuQQpG1l3gA5aFf9SjfjRau2WiXYDIORDGWkggqNBIv4aGb5";

    // Giải mã từ Base64 → SecretKey
    public static final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(BASE64_SECRET_KEY));

    public static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 7 ngày
    public static final long EXPIRATION_TIME_RESETPASSWORD = 1000L * 60 * 15; // 15 phút
    public static final String TYPEJWT_LOGIN = "LOGIN";
    public static final String TYPEJWT_RESETPASSWORD = "RESETPASSWORD";
}
