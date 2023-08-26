package com.example.userservice.controller;

import com.example.userservice.dto.DepartmentDto;
import com.example.userservice.dto.ResponseDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String DEPARTMENT_SERVICES ="departmentService";

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    //@CircuitBreaker(name =DEPARTMENT_SERVICES,fallbackMethod = "departmentFallback")
    @Retry(name = DEPARTMENT_SERVICES,fallbackMethod = "departmentFallback")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<ResponseDto> departmentFallback(Exception e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setUser(new UserDto(1001L,"jyoti","Singh","jyoti@gmail.com"));
        responseDto.setDepartment(new DepartmentDto(5001L,"Electronics","Pune","EC"));
        return ResponseEntity.ok(responseDto);
    }
}
