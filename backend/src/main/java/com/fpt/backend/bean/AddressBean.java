package com.fpt.backend.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBean {
    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên không được chứa ký tự số hoặc ký tự đặc biệt")
    private String fullName;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không được quá 10 số và không chứa ký tự")
    private String phone;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String street;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private boolean isDefault;
}
