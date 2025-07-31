package com.fpt.backend.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.bean.AddressBean;
import com.fpt.backend.entity.Address;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.AddressJpa;
import com.fpt.backend.mapper.AddressMapper;

@Service
public class AddressService {

    @Autowired
    private AddressJpa addressJpa;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressMapper addressMapper;

    public List<Object> getAddressByUser(String token) {
        User user = userService.getUserIsLogin(token);
        if (user == null)
            return Collections.emptyList();

        List<Address> addresses = user.getAddresses();
        return addresses.stream()
                .sorted(
                        Comparator.comparing(Address::isDefault).reversed()
                                .thenComparing(Address::getId))
                .map(addressMapper::toDTO)
                .toList();
    }

    public Object getDefaultAddress(Integer id) {
        Address address = getAddressById(id);
        return addressMapper.toDTO(address);
    }

    public Address add(String token, AddressBean addressBean) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để thêm địa chỉ");
        }

        if (user.getAddresses().size() >= 6) {
            throw new IllegalArgumentException("Bạn chỉ có thể lưu tối đa 6 địa chỉ");
        }

        Address address = new Address();
        address.setFullName(addressBean.getFullName());
        address.setPhone(addressBean.getPhone());
        address.setStreet(addressBean.getStreet());
        address.setProvinceId(addressBean.getProvinceId());
        address.setDistrictId(addressBean.getDistrictId());
        address.setWardId(addressBean.getWardId());

        if (user.getAddresses().isEmpty()) {
            address.setDefault(true);
        }
        address.setUser(user);
        return addressJpa.save(address);
    }

    public Address update(String token, Integer addressId, AddressBean addressBean) {
        // Kiểm tra đăng nhập
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để cập nhật địa chỉ");
        }

        // Lấy địa chỉ theo ID
        Address address = addressJpa.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Địa chỉ không tồn tại"));

        address.setFullName(addressBean.getFullName());
        address.setPhone(addressBean.getPhone());
        address.setStreet(addressBean.getStreet());
        address.setProvinceId(addressBean.getProvinceId());
        address.setDistrictId(addressBean.getDistrictId());
        address.setWardId(addressBean.getWardId());
        return addressJpa.save(address);
    }

    public void deleteAddress(String token, Integer addressId) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để xóa địa chỉ");
        }

        Address address = addressJpa.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Địa chỉ không tồn tại"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Bạn không có quyền xóa địa chỉ này");
        }

        addressJpa.delete(address);
    }

    public Address getAddressById(Integer addressId) {
        return addressJpa.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Địa chỉ không tồn tại"));
    }

    public Object findByIdAndUserId(String token, Integer addressId) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để xem địa chỉ");
        }

        Address address = addressJpa.findByIdAndUserId(addressId, user.getId()).orElse(null);
        return addressMapper.toDTO(address);
    }


    public Address setDefaultAddress(String token, Integer addressId) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            throw new IllegalArgumentException("Vui lòng đăng nhập để đặt địa chỉ mặc định");
        }

        Address address = getAddressById(addressId);
        if (!address.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Bạn không có quyền đặt địa chỉ này làm mặc định");
        }
        user.getAddresses().forEach(addr -> addr.setDefault(false));
        address.setDefault(true);
        return addressJpa.save(address);
    }
}
