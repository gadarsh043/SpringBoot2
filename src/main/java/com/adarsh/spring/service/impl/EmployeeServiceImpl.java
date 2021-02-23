package com.adarsh.spring.service.impl;

import com.adarsh.spring.dto.EmployeeRequestDTO;
import com.adarsh.spring.dto.EmployeeResponseDTO;
import com.adarsh.spring.entities.Employee;
import com.adarsh.spring.repository.EmployeeRepository;
import com.adarsh.spring.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDTO, employee);

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        // copy from employee to response dto
        EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee, responseDto);

        return responseDto;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            //copy from employee to response dto
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(), responseDTO);
            return responseDTO;
        }
        return null;
    }
    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            //copy from employee to response dto
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDTO.getName());
            employeeFromDb.setDepartment(employeeRequestDTO.getDepartment());
            //save to db
            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            //copy from employee to responseDTO
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(savedEmployee, responseDTO);
            return responseDTO;
        }
        return null;
    }
    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            //delete from employee to response dto
            Employee employeeFromDb = employeeOptional.get();
            employeeRepository.delete(employeeFromDb);

            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional, responseDTO);
            return responseDTO;
        }
        return null;
    }
}
