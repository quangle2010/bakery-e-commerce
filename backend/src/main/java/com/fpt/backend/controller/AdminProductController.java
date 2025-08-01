package com.fpt.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.ProductService;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/search")
    public ResponseEntity<ResponseData> getProducts(@RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "default") String option) {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12);
            List<Object> lists = productService.getProductSearch(keyword, pageable, option);
            int total = productService.totalItem(keyword);
            return ResponseEntityUtil.OK("Load thành công", CustomResponse.ARRAYLIST_INT(lists, total));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }
}
