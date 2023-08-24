package com.springboot.vivek.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {


    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
