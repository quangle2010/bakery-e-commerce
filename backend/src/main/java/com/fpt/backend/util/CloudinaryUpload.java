package com.fpt.backend.util;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUpload {

        private static String CLOUD_NAME = "dmtgxfqjh";
        private static String API_KEY = "768771458282615";
        private static String API_SECRET = "0JYQgQWiLSc0TI0bmQdIEyJtcXM";
        private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,      
                "api_key", API_KEY,          
                "api_secret", API_SECRET      
        ));
    
        public static String uploadImage(MultipartFile file) throws IOException {
            return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("secure_url");
        }

}