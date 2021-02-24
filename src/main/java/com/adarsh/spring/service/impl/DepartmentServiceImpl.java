package com.adarsh.spring.service.impl;

import com.adarsh.spring.dto.DepartmentRequestDTO;
import com.adarsh.spring.dto.DepartmentResponseDTO;
import com.adarsh.spring.entities.Department;
import com.adarsh.spring.entities.Employee;
import com.adarsh.spring.repository.DepartmentRepository;
import com.adarsh.spring.repository.EmployeeRepository;
import com.adarsh.spring.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentRequestDTO, department);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment, responseDTO);
        return responseDTO;
    }
    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }
    @Override
    public DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDto) {
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

        //update department
        department.setName(departmentRequestDto.getName());
        Department savedDepartment = departmentRepository.save(department);

        //append departmentCode to employee code

        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDto.getDepartmentCode());
        });
        employeeRepository.saveAll(employeeList);


        DepartmentResponseDTO responseDto = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment, responseDto);
        return responseDto;
    }

}
