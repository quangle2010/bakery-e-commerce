package com.fpt.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.CartItem;
import com.fpt.backend.entity.Order;
import com.fpt.backend.entity.OrderItem;
import com.fpt.backend.entity.Product;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.CartItemJpa;
import com.fpt.backend.jpa.OrderItemJpa;
import com.fpt.backend.mapper.CartItemMapper;
import com.fpt.backend.util.Constant;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemJpa cartItemJpa;

    @Autowired
    private OrderItemJpa orderItemJpa;

    @Autowired
    private OrderService orderService;

    public Object getCartByUser(String token) {
        User user = userService.getUserIsLogin(token);
        if (user.getCartItems().isEmpty()) {
            return null;
        }
        List<Object> carts = user.getCartItems().stream()
                .map(cartItemMapper::toDTO)
                .toList();
        return carts;
    }

    public CartItem getCartItemByUserIdAndProductId(int userId, int productId) {
        return cartItemJpa.findByUserIdAndProductId(userId, productId)
                .orElse(null);
    }

    public CartItem saveCartItem(String token, int productId, int quantity) {

        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng");
        }
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại");
        }

        if (product.getQuantity() - quantity < 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm không đủ trong kho");
        }

        CartItem cartItem = getCartItemByUserIdAndProductId(
                user.getId(), productId);

        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + quantity;

            if (newQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Số lượng sản phẩm không đủ trong kho");
            }

            cartItem.setQuantity(Math.max(1, newQuantity));
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
        }
        return cartItemJpa.save(cartItem);
    }

    public boolean deleteCartItem(String token, int productId) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            return false;
        }
        CartItem cartItem = getCartItemByUserIdAndProductId(user.getId(), productId);
        if (cartItem != null) {
            cartItemJpa.delete(cartItem);
            return true;
        }
        return false;
    }

    public Order payment(String token, String fullName, String phone, String address, int paymentMethod) {
        User user = userService.getUserIsLogin(token);
        boolean updated = userService.updatePhoneAddress(user, phone, address);
        if (updated) {
            System.out.println("User info updated before payment.");
        }
        Order order = new Order();
        if (paymentMethod == 0) {
            order.setPaymentMethod(Constant.ORDER_PAYMENT_COD);
        } else {
            order.setPaymentMethod(Constant.ORDER_PAYMENT_ONLINE);
        }

        order.setPaymentStatus(Constant.ORDER_PAYMENT_STATUS_PENDING);
        order.setStatus(Constant.ORDER_STATUS_PENDING);
        order.setUserInfo(fullName + " -  " + phone + " - " + address);
        order.setUser(user);
        order.setCreateAt(new Date());
        Order saved = orderService.save(order);
        for (CartItem item : user.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            orderItem.setOrder(saved);
            orderItemJpa.save(orderItem);
            cartItemJpa.delete(item);
        }

        return saved;
    }

    public boolean updatePaymentStatus(Integer orderId, String vnpayReps) {
        Order order = orderService.findById(orderId);
        if (vnpayReps.equals("00")) {
            order.setPaymentStatus(Constant.ORDER_PAYMENT_STATUS_SUCCESS);
            orderService.save(order);
        }
        return true;
    }

}
