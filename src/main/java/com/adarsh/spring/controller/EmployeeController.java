package com.adarsh.spring.controller;

import com.adarsh.spring.dto.EmployeeRequestDTO;
import com.adarsh.spring.dto.EmployeeResponseDTO;
import com.adarsh.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //POST - /employee
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.createEmployee(employeeRequestDTO);
    }

    //GET - /employee/{id}
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id")long id){
        return employeeService.getEmployeeById(id);
    }

    //PUT - /employee/{id}
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable("id")Long id,@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.updateEmployeeById(id,employeeRequestDTO);
    }

    //DELETE -/employee/{id}
    @DeleteMapping("/{id}")
    public EmployeeResponseDTO deleteEmployeeById(@PathVariable("id")Long id){
        return employeeService.deleteEmployeeById(id);
    }

    //GET - /employee/department/{id}
    @GetMapping("/department/{id}")
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(@PathVariable("id") Long departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId);
    }

}
