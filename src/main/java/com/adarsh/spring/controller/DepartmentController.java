package com.adarsh.spring.controller;

import com.adarsh.spring.dto.DepartmentRequestDTO;
import com.adarsh.spring.dto.DepartmentResponseDTO;
import com.adarsh.spring.dto.EmployeeRequestDTO;
import com.adarsh.spring.dto.EmployeeResponseDTO;
import com.adarsh.spring.entities.Department;
import com.adarsh.spring.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //POST - /department
    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return departmentService.createDepartment(departmentRequestDTO);
    }
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") Long id){
        return departmentService.getDepartmentById(id);
    }
    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentRequestDTO departmentRequestDto){
        return departmentService.updateDepartment(departmentId,departmentRequestDto);
    }

}
