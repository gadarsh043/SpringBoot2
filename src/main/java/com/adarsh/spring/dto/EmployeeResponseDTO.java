package com.adarsh.spring.dto;

import com.adarsh.spring.entities.Department;
import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private long id;

    private String name;
    private String code;

    private DepartmentResponseDTO department;

    public void setDepartmentFromEntity(Department departmentEntity){
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(departmentEntity.getId());
        departmentResponseDTO.setName(departmentEntity.getName());
        this.department = departmentResponseDTO;

    }
}
