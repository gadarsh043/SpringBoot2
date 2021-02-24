package com.adarsh.spring.repository;

import com.adarsh.spring.entities.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
}
