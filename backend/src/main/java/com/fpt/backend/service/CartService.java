package com.fpt.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.CartItem;
import com.fpt.backend.entity.Product;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.CartItemJpa;
import com.fpt.backend.mapper.CartItemMapper;

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

    public boolean saveCartItem(String token ,int productId, int quantity) {

        User user = userService.getUserIsLogin(token);
        if (user == null) {
            return false;
        }
        Product product = productService.getProductById(productId);

        if (product == null) {
            return false;
        }

        if (product.getQuantity() - quantity < 0) {
            return false;
        }

        CartItem cartItem = getCartItemByUserIdAndProductId(
                userService.getUserIsLogin(token).getId(), productId);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            if (cartItem.getQuantity() < 1) {
                cartItem.setQuantity(1);
            }
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
        }
        cartItemJpa.save(cartItem);
        return true;

    }

    public boolean deleteCartItem(String token,int productId) {
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
