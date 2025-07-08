package com.fpt.backend.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangePasswordBean {

    @NotBlank(message = "Mật khẩu cũ không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    private String passwordOld;
    
    @NotBlank(message = "Mật khẩu mới không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    private String passwordNew;

    @NotBlank(message = "Xác nhận mật khẩu mới không được để trống")
    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6 đến 20 ký tự")
    private String confirmPassword;

    public boolean isPasswordMatch() {
        return passwordNew != null && passwordNew.equals(confirmPassword);
    }

}
