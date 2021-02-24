package com.adarsh.spring.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GenericGenerator(name = "department_id_seq", strategy = "increment")
    @GeneratedValue(generator = "department_id_seq", strategy = GenerationType.AUTO)
    private long id;
    private String name;


    //@JoinColumn(referencedColumnName = "id", name = "department_id")
    //@OneToMany
    //private List<Employee> employeeList;

}
