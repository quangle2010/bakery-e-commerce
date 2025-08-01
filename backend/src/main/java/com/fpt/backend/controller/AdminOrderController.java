package com.fpt.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.OrderService;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {
@Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<ResponseData> getOrders(@RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12,Sort.by(Sort.Direction.DESC, "id"));
            List<Object> orders = orderService.findByCreateAtRange(token, startDate, endDate, pageable);
            int totalItems = orderService.countByCreateAtRange(token, startDate, endDate);
            return ResponseEntityUtil.OK("Load thành công",
                    CustomResponse.ARRAYLIST_INT(orders, totalItems));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());

        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseData> getOrderById(@RequestHeader("Authorization") String token,
            @PathVariable int id) {
        try {
            Object order = orderService.getById(id);
            return ResponseEntityUtil.OK("Load thành công", order);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PostMapping("/order/update-status/{id}")
    public ResponseEntity<ResponseData> updateOrderStatus(@RequestHeader("Authorization") String token,
            @PathVariable int id, @RequestParam int statusId, @RequestParam String cancelReason) {
        try {
            boolean check = orderService.updateOrderStatus(id, statusId, cancelReason);
            if (check == false) {
                return ResponseEntityUtil.BAD_REQUEST("Không thể cập nhật đơn hàng này.");
            }
            return ResponseEntityUtil.OK(statusId == -1 ? "Hủy đơn hàng thành công." : "Cập nhật đơn hàng thành công.", null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }
}
