package com.fpt.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.CartItem;
import com.fpt.backend.entity.Product;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.AddressJpa;
import com.fpt.backend.jpa.CartItemJpa;
import com.fpt.backend.mapper.CartItemMapper;

@Service
public class CartService {

    private final AddressJpa addressJpa;
    
    @Autowired
    private UserService userService;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemJpa cartItemJpa;

    CartService(AddressJpa addressJpa) {
        this.addressJpa = addressJpa;
    }

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
}
