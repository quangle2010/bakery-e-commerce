package com.fpt.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.ProductService;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/search")
    public ResponseEntity<ResponseData> getProducts(@RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page) {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12);
            List<Object> lists = productService.getProductSearch(keyword, pageable);
            int total = productService.totalItem(keyword);
            return ResponseEntityUtil.OK("Load thành công", CustomResponse.ARRAYLIST_INT(lists, total));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseData>  getMethodName(@PathVariable int id) {
       try {
         return ResponseEntityUtil.OK("Load thành công", productService.getProductDTO(id));
       } catch (Exception e) {
      return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
       }
    }
    

}
