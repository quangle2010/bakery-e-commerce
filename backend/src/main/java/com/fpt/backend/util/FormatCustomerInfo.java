package com.fpt.backend.util;

public class FormatCustomerInfo {
    public static String fullName(String customerInfo) {
        String[] parts = customerInfo.split(" - ");
        return parts.length > 0 ? parts[0].trim() : "";
    }

    public static String phone(String customerInfo) {
        String[] parts = customerInfo.split(" - ");
        return parts.length > 1 ? parts[1].trim() : "";
    }

    public static String address(String customerInfo) {
        String[] parts = customerInfo.split(" - ");
        return parts.length > 2 ? parts[2].trim() : "";
    }

   public static String customFullName(String fullName) {
    String[] words = fullName.trim().toLowerCase().split("\\s+");
    StringBuilder result = new StringBuilder();

    for (String word : words) {
        if (word.length() > 0) {
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1))
                  .append(" ");
        }
    }
    return result.toString().trim();
}

}
