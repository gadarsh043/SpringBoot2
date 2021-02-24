package com.adarsh.spring.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private long id;

    private String name;

    private String code;

    private DepartmentRequestDTO department;


}
