package com.fpt.backend.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.config.PaymentConfig;
import com.fpt.backend.entity.Order;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.CartService;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class PaymentController {

    @Autowired
    private CartService cartService;

    @PostMapping("/payment")
    public ResponseEntity<ResponseData> postMethodName(@RequestHeader("Authorization") String token,
            @RequestParam String fullName, @RequestParam String phone, @RequestParam String address,
            @RequestParam int paymentMethod) {
        try {
            Order payment = cartService.payment(token, fullName, phone, address, paymentMethod);
            if (payment != null) {
                return ResponseEntityUtil.OK("Đặt hàng thành công", payment.getId());

            } else {
                return ResponseEntityUtil.BAD_REQUEST("Đặt hàng thất bại");
            }

        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @GetMapping("/payment-online")
    public ResponseEntity<ResponseData> getOnlinePayment(HttpServletRequest req) {
        try {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_OrderInfo = req.getParameter("orderInfor");
            String orderType = "100000";
            String vnp_TxnRef = req.getParameter("orderId");
            String vnp_IpAddr = PaymentConfig.getIpAddress(req);
            String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
            if (vnp_OrderInfo == null || vnp_OrderInfo.isEmpty() ||
                    vnp_TxnRef == null || vnp_TxnRef.isEmpty() ||
                    req.getParameter("total") == null || req.getParameter("total").isEmpty()) {

                return ResponseEntityUtil.BAD_REQUEST("Missing required parameters");
            }

            int amount;
            try {
                amount = Integer.parseInt(req.getParameter("total")) * 100;
            } catch (NumberFormatException e) {

                return ResponseEntityUtil.BAD_REQUEST(e.getMessage());

            }
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", req.getParameter("language") != null ? req.getParameter("language") : "vn");
            vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));
            cld.add(Calendar.MINUTE, 15);
            vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));
            addOptionalBillingInfo(vnp_Params, req);
            addOptionalInvoiceInfo(vnp_Params, req);
            String queryUrl = buildQueryUrl(vnp_Params);
            String vnp_SecureHash = PaymentConfig.generateHMACSHA512(PaymentConfig.vnp_HashSecret, queryUrl);
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;

            return ResponseEntityUtil.OK("Payment created", paymentUrl);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

    @PostMapping("/return-payment")
    public ResponseEntity<ResponseData> handlePaymentReturn(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam Integer orderId, @RequestParam String vnpResponseCode) {

        try {
            if (orderId == null || vnpResponseCode == null) {
                return ResponseEntityUtil.BAD_REQUEST("Thông tin thanh toán không đầy đủ.");
            }

            boolean check = cartService.updatePaymentStatus(orderId, vnpResponseCode);

            if (check) {
                return ResponseEntityUtil.OK("Thanh toán thành công", null);
            } else {
                return ResponseEntityUtil.BAD_REQUEST("Thanh toán thất bại");
            }

        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST("Thanh toán thất bại: " + e.getMessage());
        }
    }

    private void addOptionalBillingInfo(Map<String, String> params, HttpServletRequest req) {
        params.put("vnp_Bill_Mobile", req.getParameter("txt_billing_mobile"));
        params.put("vnp_Bill_Email", req.getParameter("txt_billing_email"));

        String fullName = req.getParameter("txt_billing_fullname");
        if (fullName != null && !fullName.isEmpty()) {
            String[] nameParts = fullName.split(" ", 2);
            params.put("vnp_Bill_FirstName", nameParts[0]);
            params.put("vnp_Bill_LastName", nameParts.length > 1 ? nameParts[1] : "");
        }

        params.put("vnp_Bill_Address", req.getParameter("txt_inv_addr1"));
        params.put("vnp_Bill_City", req.getParameter("txt_bill_city"));
        params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
            params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
        }
    }

    private void addOptionalInvoiceInfo(Map<String, String> params, HttpServletRequest req) {
        params.put("vnp_Inv_Phone", req.getParameter("txt_inv_mobile"));
        params.put("vnp_Inv_Email", req.getParameter("txt_inv_email"));
        params.put("vnp_Inv_Customer", req.getParameter("txt_inv_customer"));
        params.put("vnp_Inv_Address", req.getParameter("txt_inv_addr1"));
        params.put("vnp_Inv_Company", req.getParameter("txt_inv_company"));
        params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
        params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));
    }

    private String buildQueryUrl(Map<String, String> params) throws Exception {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String fieldName : fieldNames) {
            String fieldValue = params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())).append('&');
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString())).append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())).append('&');
            }
        }

        // Remove trailing '&'
        if (hashData.length() > 0)
            hashData.setLength(hashData.length() - 1);
        if (query.length() > 0)
            query.setLength(query.length() - 1);

        return hashData.toString();
    }

}
