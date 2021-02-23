package com.adarsh.spring.repository;

import com.adarsh.spring.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {


}
