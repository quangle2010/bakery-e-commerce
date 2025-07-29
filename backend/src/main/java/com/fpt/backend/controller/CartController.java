package com.fpt.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.CartService;
import com.fpt.backend.util.ResponseEntityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<ResponseData> getCart(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntityUtil.OK("Lấy thông tin thành công", cartService.getCartByUser(token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<ResponseData> postCart(@RequestHeader("Authorization") String token,
            @RequestParam Integer productId, @RequestParam Integer quantity) {
        try {
            cartService.saveCartItem(token, productId, quantity);
            return ResponseEntityUtil.OK("Lưu sản phẩm vào giỏ hàng thành công",
                    cartService.getCartByUser(token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<ResponseData> deleteCart(@RequestHeader("Authorization") String token,
            @PathVariable Integer id) {
        try {
            cartService.deleteCartItem(token, id);
            return ResponseEntityUtil.OK("Xóa sản phẩm khỏi giỏ hàng thành công",
                    cartService.getCartByUser(token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

}
