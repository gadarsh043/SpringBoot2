package com.adarsh.spring.service;

import com.adarsh.spring.dto.DepartmentRequestDTO;
import com.adarsh.spring.dto.DepartmentResponseDTO;
import com.adarsh.spring.entities.Department;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    Department getDepartmentById(Long id);
    DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDto);
}
