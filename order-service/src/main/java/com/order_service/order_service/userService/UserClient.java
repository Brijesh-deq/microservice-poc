package com.order_service.order_service.userService;

import com.order_service.order_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "USER-SERVICE", url = "http://user-service:8080/")
public interface UserClient {
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getUserById(@RequestParam List<Long> userIds);
}
