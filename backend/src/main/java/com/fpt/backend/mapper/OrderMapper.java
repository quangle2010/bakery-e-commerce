package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fpt.backend.entity.Order;
import com.fpt.backend.util.Constant;
import com.fpt.backend.util.FormatCustomerInfo;

@Component
public class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;
    public String mapToOrderStatus(int status) {
        switch (status) {
            case -1:
                return "Đã hủy";
            case 0:
                return "Chờ xác nhận";
            case 1:
                return "Đã xác nhận";
            case 2:
                return "Đã giao hàng";
            case 3:
                return "Đã nhận hàng";
            default:
                return "Trạng thái không xác định";
        }
    }

    public String mapToOrderPaymentMethod(int paymentMethod) {
        switch (paymentMethod) {
            case 0:
                return "Thanh toán khi nhận hàng";
            case 1:
                return "Thanh toán online";
            default:
                return "Thanh toán không xác định";
        }
    }

    public Object mapToOrderResponse(Order order) {
        if (order == null) {
            return null;
        }
        Map<String, Object> orderResponse = new HashMap<>();
        orderResponse.put("id", order.getId());
        orderResponse.put("userId", order.getUser().getId());
        orderResponse.put("status", mapToOrderStatus(order.getStatus()));
        orderResponse.put("createAt", order.getCreateAt());
        orderResponse.put("statusId", order.getStatus());
        orderResponse.put("fullName", FormatCustomerInfo.fullName(order.getUserInfo()));
        orderResponse.put("phone", FormatCustomerInfo.phone(order.getUserInfo()));
        orderResponse.put("address", FormatCustomerInfo.address(order.getUserInfo()));
        orderResponse.put("cancelOrder", order.getCancelOrder());
        orderResponse.put("orderItems", orderItemMapper.mapToOrderItemResponse(order.getOrderItems()));
        orderResponse.put("paymentStatus", order.getPaymentStatus());
        orderResponse.put("paymentMethod", mapToOrderPaymentMethod(order.getPaymentMethod()));
        double totalPrice = order.getOrderItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        orderResponse.put("totalPrice", totalPrice);
        return orderResponse;
    }

    public Object dashboard(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        Map<Integer, Long> statusCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        // Tổng tiền theo trạng thái
        Map<Integer, Double> revenueByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus,
                        Collectors.summingDouble(this::calculateTotalPrice)));

        Map<String, Object> result = new HashMap<>();
        result.put("totalOrders", orders.size());
        result.put("cancelledOrders", statusCount.getOrDefault(Constant.ORDER_STATUS_CANCELLED, 0L));
        result.put("pendingOrders", statusCount.getOrDefault(Constant.ORDER_STATUS_PENDING, 0L));
        result.put("confirmedOrders", statusCount.getOrDefault(Constant.ORDER_STATUS_CONFIRMED, 0L));
        result.put("deliveringOrders", statusCount.getOrDefault(Constant.ORDER_STATUS_SHIPPING, 0L));
        result.put("deliveredOrders", statusCount.getOrDefault(Constant.ORDER_STATUS_COMPLETED, 0L));

        result.put("revenuetotalOrders", orders.stream().mapToDouble(this::calculateTotalPrice).sum());
        result.put("revenuecancelledOrders", revenueByStatus.getOrDefault(Constant.ORDER_STATUS_CANCELLED, 0.0));
        result.put("revenuependingOrders", revenueByStatus.getOrDefault(Constant.ORDER_STATUS_PENDING, 0.0));
        result.put("revenueconfirmedOrders", revenueByStatus.getOrDefault(Constant.ORDER_STATUS_CONFIRMED, 0.0));
        result.put("revenuedeliveringOrders", revenueByStatus.getOrDefault(Constant.ORDER_STATUS_SHIPPING, 0.0));
        result.put("revenuedeliveredOrders", revenueByStatus.getOrDefault(Constant.ORDER_STATUS_COMPLETED, 0.0));

        return result;
    }

    private double calculateTotalPrice(Order order) {
        return order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
