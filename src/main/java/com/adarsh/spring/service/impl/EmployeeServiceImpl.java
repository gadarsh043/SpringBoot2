package com.adarsh.spring.service.impl;

import com.adarsh.spring.dto.DepartmentResponseDTO;
import com.adarsh.spring.dto.EmployeeRequestDTO;
import com.adarsh.spring.dto.EmployeeResponseDTO;
import com.adarsh.spring.entities.Department;
import com.adarsh.spring.entities.Employee;
import com.adarsh.spring.repository.DepartmentRepository;
import com.adarsh.spring.repository.EmployeeRepository;
import com.adarsh.spring.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDTO, employee);

        //copy fields from dto to employee
        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }
        else{
            Department department=new Department();
            department.setName(employeeRequestDTO.getDepartment().getName());
            employee.setDepartment(department);
        }


        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        // copy from employee to response dto
        EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee, responseDto);

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(savedEmployee.getDepartment().getId());
        departmentResponseDTO.setName(savedEmployee.getDepartment().getName());

        responseDto.setDepartment(departmentResponseDTO);

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

            //fetch department from db
            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }

            //save to db
            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            //copy from employee to responseDTO
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(savedEmployee, responseDTO);

            responseDTO.setDepartmentFromEntity(savedEmployee.getDepartment());
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

    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId) {
        /*1. Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment(department);*/

        /*2. List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);*/

        /*3. List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(departmentId);*/

        /*4
        List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentIdParam(departmentId);*/

        //5
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);
        List<EmployeeResponseDTO> employeeResponseDtoList = new ArrayList<>();
        for (Employee employee : employeeList){
            EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee, responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(responseDto);
        }
        return employeeResponseDtoList;
    }

}
