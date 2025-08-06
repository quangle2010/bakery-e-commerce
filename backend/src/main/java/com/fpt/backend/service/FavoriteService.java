package com.fpt.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.Favorite;
import com.fpt.backend.entity.Product;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.FavoriteJpa;
import com.fpt.backend.mapper.ProductMapper;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteJpa favoriteJpa;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductMapper productMapper;

    // Token kiểm tra user đã đăng nhập
    public List<Object> getFavoritesByUser(String token, Pageable pageable) {
        User user = userService.getUserIsLogin(token);
        if (user != null) {
            return favoriteJpa.findByUserId(user.getId(), pageable)
                    .stream()
                    .map(favorite -> productMapper.toDTO(favorite.getProduct()))
                    .toList();
        }
        return null;
    }

    public int countFavoritesByUser(String token) {
        User user = userService.getUserIsLogin(token);
        if (user != null) {
            return favoriteJpa.countByUserId(user.getId());
        }
        return 0;
    }

    // Kiem tra sản phẩm đã yêu thích chưa
    public Favorite findByUserIdAndProductId(Integer userId, Integer productId) {
        return favoriteJpa.findByUserIdAndProductId(userId, productId)
                .orElse(null);
    }

    // Thêm và xóa sản phẩm yêu thích
    public Favorite addAndRemoveFavorite(String token, Integer productId) {
        User user = userService.getUserIsLogin(token);
        Product product = productService.getProductById(productId);

        Favorite favorite = findByUserIdAndProductId(user.getId(), productId);
        if (favorite == null) {
            favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProduct(product);
            return favoriteJpa.save(favorite);
        } else {
            favoriteJpa.delete(favorite);
            return null;
        }
    }

}
