package com.adarsh.spring.repository;

import com.adarsh.spring.entities.Department;
import com.adarsh.spring.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    //by method name
    //1
    List<Employee> findByDepartment(Department department);

    //2
    List<Employee> findByDepartment_Id(Long departmentId);

    //3
    //by Query Annotation
    @Query("SELECT e FROM Employee e WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    //4
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> getEmployeeListByDepartmentIdParam(@Param("departmentId") Long departmentId);

    //by native query

    @Query(value = "SELECT * FROM employee e WHERE e.department_id = ?1", nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);
}
