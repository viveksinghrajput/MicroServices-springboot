package com.example.userservice.config;

import com.example.userservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT-SERVICES", url = "http://localhost:8080")
public interface APIClient {

    @GetMapping(value = "/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long departmentId);
}
