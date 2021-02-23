package com.adarsh.spring.service;

import com.adarsh.spring.dto.EmployeeRequestDTO;
import com.adarsh.spring.dto.EmployeeResponseDTO;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDto);

    EmployeeResponseDTO getEmployeeById(long id);

    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO);

    public EmployeeResponseDTO deleteEmployeeById(Long id);

}

