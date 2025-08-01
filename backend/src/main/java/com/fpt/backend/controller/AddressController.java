package com.fpt.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.AddressBean;
import com.fpt.backend.entity.Address;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.AddressService;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<ResponseData> getAddresses(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntityUtil.OK("Lấy thông tin thành công", addressService.getAddressByUser(token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<ResponseData> getAddress(@RequestHeader("Authorization") String token,
            @PathVariable Integer addressId) {
        try {
            return ResponseEntityUtil.OK("Lấy thông tin thành công", addressService.findByIdAndUserId(token, addressId));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PostMapping("/addresses")
    public ResponseEntity<ResponseData> createAddress(@RequestHeader("Authorization") String token,
             @Valid @RequestBody AddressBean addressBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntityUtil.BAD_REQUEST(result.getFieldError().getDefaultMessage());
            }
            Address address= addressService.add(token, addressBean);
            return ResponseEntityUtil.OK("Tạo địa chỉ thành công", address.getId());
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<ResponseData> deleteAddress(@RequestHeader("Authorization") String token,
            @PathVariable Integer addressId) {
        try {
            addressService.deleteAddress(token, addressId);
            return ResponseEntityUtil.OK("Xóa địa chỉ thành công", null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }


    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<ResponseData> updateAddress(@RequestHeader("Authorization") String token,
            @PathVariable Integer addressId, @Valid @RequestBody AddressBean addressBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntityUtil.BAD_REQUEST(result.getFieldError().getDefaultMessage());
            }
            addressService.update(token, addressId, addressBean);
            return ResponseEntityUtil.OK("Cập nhật địa chỉ thành công", addressService.getDefaultAddress(addressId));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PutMapping("/addresses/{addressId}/default")
    public ResponseEntity<ResponseData> setDefaultAddress(@RequestHeader("Authorization") String token,
            @PathVariable Integer addressId) {
        try {
            addressService.setDefaultAddress(token, addressId);
            return ResponseEntityUtil.OK("Đặt địa chỉ mặc định thành công", addressService.getDefaultAddress(addressId));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

}
