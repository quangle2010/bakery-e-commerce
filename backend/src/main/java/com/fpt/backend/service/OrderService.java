package com.fpt.backend.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.Order;
import com.fpt.backend.entity.OrderItem;
import com.fpt.backend.entity.Product;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.OrderJpa;
import com.fpt.backend.mapper.OrderMapper;
import com.fpt.backend.util.Constant;

@Service
public class OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderJpa orderJpa;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    public Order save(Order order) {
        return orderJpa.save(order);
    }

    public List<Object> findByUserIdAndCreateAtRange(String token, Date startDate, Date endDate, Pageable pageable) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            return null;
        }
        int userId = user.getId();
        Page<Order> oPages = orderJpa.findByUserIdAndCreateAtRange(userId, startDate, endDate, pageable);

        return oPages.getContent().stream() // ✅
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(orderMapper::mapToOrderResponse)
                .collect(Collectors.toList());

    }

    public int countByUserIdAndCreateAtRange(String token, Date startDate, Date endDate) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            return 0;
        }
        int userId = user.getId();
        return orderJpa.countByUserIdAndCreateAtRange(userId, startDate, endDate);
    }
 public List<Object> findByCreateAtRange(String token, Date startDate, Date endDate, Pageable pageable) {
       
        Page<Order> oPages = orderJpa.findByCreateAtRange(startDate, endDate, pageable);

        return oPages.getContent().stream() // ✅
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(orderMapper::mapToOrderResponse)
                .collect(Collectors.toList());

    }

    public int countByCreateAtRange(String token, Date startDate, Date endDate) {
        return orderJpa.countByCreateAtRange(startDate, endDate);
    }

    public List<Object> findAll(Date startDate, Date endDate) {
        List<Order> orders = orderJpa.findByCreateAt(startDate, endDate);
        if (orders == null) {
            return null;
        }
        return orders.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(orderMapper::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public Object findAllByDate(Date startDate, Date endDate) {
        List<Order> orders = orderJpa.findByCreateAt(startDate, endDate);
        if (orders == null) {
            return null;
        }
        return orderMapper.dashboard(orders);
    }

    public Order findById(int id) {
        return orderJpa.findById(id).orElse(null);
    }

    public Object getById(int id) {
        Order order = findById(id);
        return orderMapper.mapToOrderResponse(order);
    }

    public Object findByOrderUserId(String token, int id) {
        User user = userService.getUserIsLogin(token);
        if (user == null) {
            return null;
        }
        Order order = orderJpa.findByOrderUserId(user.getId(), id).orElseGet(null);
        return orderMapper.mapToOrderResponse(order);
    }

   

    public boolean updateOrderStatus(int orderId, int newStatus, String cancelReason) {
        Order order = findById(orderId);
        if (order == null) {
            return false;
        }

        int currentStatus = order.getStatus();
        if (currentStatus == Constant.ORDER_STATUS_PENDING && newStatus == Constant.ORDER_STATUS_CANCELLED) {
            order.setStatus(Constant.ORDER_STATUS_CANCELLED);
            order.setCancelOrder(cancelReason);
            save(order);
            return true;
        }

        if (newStatus == Constant.ORDER_STATUS_CONFIRMED && currentStatus == Constant.ORDER_STATUS_PENDING) {
            boolean updated = checkQuantity(order);
            if (!updated) {
                return false;
            }
            order.setStatus(Constant.ORDER_STATUS_CONFIRMED);
            save(order);
            return true;
        }

        if (checkOrderStatus(currentStatus, newStatus)) {
            order.setStatus(newStatus);
            if (newStatus == 3) {
                order.setPaymentStatus(Constant.ORDER_PAYMENT_STATUS_SUCCESS);
            }
            save(order);
            return true;
        }

        return false;
    }

    public boolean checkQuantity(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            int quantityOrdered = item.getQuantity();
            if (product.getQuantity() < quantityOrdered) {
                return false;
            }
            product.setQuantity(product.getQuantity() - quantityOrdered);
            // productService.save(product);
        }
        return true;
    }

    public boolean checkOrderStatus(int currentStatus, int newStatus) {

        return (currentStatus == Constant.ORDER_STATUS_CONFIRMED && newStatus == Constant.ORDER_STATUS_SHIPPING)
                || (currentStatus == Constant.ORDER_STATUS_SHIPPING && newStatus == Constant.ORDER_STATUS_COMPLETED);
    }

    // public List<ProductSellBean> findTop5ProductSell(Date starDate , Date
    // endDate) {
    // return orderJpa.getTop5ProductSell(starDate, endDate);
    // }
}
