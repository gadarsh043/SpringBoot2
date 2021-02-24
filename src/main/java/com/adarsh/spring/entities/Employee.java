package com.adarsh.spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    //Auto Increment
    @GenericGenerator(name = "employee_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Department department;
}
