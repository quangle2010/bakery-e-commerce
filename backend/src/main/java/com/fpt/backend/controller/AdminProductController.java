package com.fpt.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.backend.bean.ProductBean;
import com.fpt.backend.entity.Product;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.AttributeService;
import com.fpt.backend.service.ProductService;
import com.fpt.backend.util.Constant;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AttributeService attributeService;

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

    @GetMapping("/product/options")
    public ResponseEntity<ResponseData> getOption() {
        try {
            Map<String, Object> map = new HashMap<>();
            List<Object> flavors = attributeService.getAttributeOptions(Constant.FLAVOR);
            List<Object> categories = attributeService.getAttributeOptions(Constant.CATEGORY);
            map.put("flavors", flavors);
            map.put("categories", categories);
            return ResponseEntityUtil.OK("Load thành công", map);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseData>  get( @PathVariable int id) {
      try {
         return ResponseEntityUtil.OK("Load thành công", productService.findDTOById(id));
      } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
      
    }
    

    @PostMapping("/product")
    public ResponseEntity<ResponseData> add(@RequestHeader("Authorization") String token, @RequestParam String name,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam double weight,
            @RequestParam String description,
            @RequestParam List<Integer> attributeOptions,
            @RequestParam MultipartFile image) {
        try {
            ProductBean productBean = new ProductBean();
            productBean.setName(name);
            productBean.setPrice(price);
            productBean.setQuantity(quantity);
            productBean.setWeight(weight);
            productBean.setDescription(description);
            productBean.setImage(image);
            productBean.setAttributeOptions(attributeOptions);

            Product product = productService.save(productBean, null);
            return ResponseEntityUtil.OK(product != null ? "Thêm sản phẩm thành công" : "Có lỗi khi thêm sản phẩm",
                    null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable int id,@RequestHeader("Authorization") String token, @RequestParam String name,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam double weight,
            @RequestParam String description,
            @RequestParam List<Integer> attributeOptions,
           @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            ProductBean productBean = new ProductBean();
            productBean.setName(name);
            productBean.setPrice(price);
            productBean.setQuantity(quantity);
            productBean.setWeight(weight);
            productBean.setDescription(description);
            productBean.setImage(image);
            productBean.setAttributeOptions(attributeOptions);

            Product product = productService.save(productBean, id);
            return ResponseEntityUtil.OK(product != null ? "Cập nhật sản phẩm thành công" : "Có lỗi khi cập nhật sản phẩm",
                    null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

}
