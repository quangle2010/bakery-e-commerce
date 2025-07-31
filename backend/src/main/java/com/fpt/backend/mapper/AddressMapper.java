package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.Address;

@Component
public class AddressMapper {

    public Object toDTO(Address address) {
        if (address == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", address.getId());
        map.put("fullName", address.getFullName());
        map.put("phone", address.getPhone());
        map.put("street", address.getStreet());
        map.put("provinceId", address.getProvinceId());
        map.put("districtId", address.getDistrictId());
        map.put("wardId", address.getWardId());
        map.put("isDefault", address.isDefault());
        return map;
    }
}
