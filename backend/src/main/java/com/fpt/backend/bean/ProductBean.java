package com.fpt.backend.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductBean {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không vượt quá 100 ký tự")
    private String name;

    @Min(value = 1, message = "Khối lượng phải lớn hơn 0")
    private double weight=50;

    private List<Integer> attributeOptions;

    @Min(value = 0, message = "Số lượng không được âm")
    private int quantity = 10;

    @Min(value = 1000, message = "Giá phải từ 1.000 VNĐ trở lên")
    private double price = 10000.0;
    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 1000, message = "Mô tả không vượt quá 1000 ký tự")
    private String description;

    private MultipartFile image;
}
