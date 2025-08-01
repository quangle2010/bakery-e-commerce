package com.fpt.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.ProductSellBean;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.OrderService;
import com.fpt.backend.service.ProductService;
import com.fpt.backend.util.ResponseEntityUtil;

@RestController
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public ResponseEntity<ResponseData> dashboard(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {

            Object dashboard = orderService.findAllByDate(startDate, endDate);
            List<ProductSellBean> productSell = orderService.findTop5ProductSell(startDate, endDate);
            int productCount = productService.getAllProducts();
            Map<String, Object> map = new HashMap<>();
            map.put("dashboard", dashboard);
            map.put("productSell", productSell);
            map.put("productCount", productCount);
            return ResponseEntityUtil.OK("Load thành công", map);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }

    }

}
