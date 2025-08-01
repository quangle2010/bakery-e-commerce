package com.fpt.backend.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSellBean {
    private Integer id;
    private String name;
    private Long count;
}
