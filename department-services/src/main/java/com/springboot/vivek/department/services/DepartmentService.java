package com.springboot.vivek.department.services;

import com.springboot.vivek.department.entity.Department;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department getDepartmentById(Long departmentId);
}
