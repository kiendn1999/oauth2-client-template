package com.example.oauth2clienttemplate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // Sau khi đăng nhập thành công và Client đã nhận được token từ Máy chủ xác thực thì có thể truy cập vào API này
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello oauth2 World!";
    }
}
