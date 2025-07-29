package com.fpt.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.entity.Favorite;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.FavoriteService;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorites")
    public ResponseEntity<ResponseData> getFavorites(@RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "1") int page) {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12);
            List<Object> favorites = favoriteService.getFavoritesByUser(token, pageable);
            int total = favoriteService.countFavoritesByUser(token);
            return ResponseEntityUtil.OK("Favorites retrieved successfully",
                    CustomResponse.ARRAYLIST_INT(favorites, total));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST("Error retrieving favorites: " + e.getMessage());
        }
    }

    @PostMapping("favorite")
    public ResponseEntity<ResponseData> postMethodName(@RequestHeader("Authorization") String token,
            @RequestParam Integer productId) {
        try {
            Favorite favorite = favoriteService.addAndRemoveFavorite(token, productId);
            return ResponseEntityUtil.OK(favorite == null ? "Đã xóa khỏi yêu thích" : "Đã thêm vào yêu thích",
                   favorite);

        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST("Error adding favorite: " + e.getMessage());
        }
    }


    
}
