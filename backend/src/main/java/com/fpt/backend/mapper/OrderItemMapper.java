package com.fpt.backend.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fpt.backend.entity.OrderItem;

@Component
public class OrderItemMapper {


    @Autowired
    private ProductMapper productMapper;

    public List<Object> mapToOrderItemResponse(List<OrderItem> orderItems) {
        if (orderItems == null || orderItems.isEmpty()) {
            return Collections.emptyList();
        }
        return orderItems.stream()
                .map(item -> {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("id", item.getId());
                    itemMap.put("product", productMapper.toDTO(item.getProduct()));
                    itemMap.put("quantity", item.getQuantity());
                    itemMap.put("price", item.getPrice());
                    return itemMap;
                })
                .collect(Collectors.toList());
    }

}
