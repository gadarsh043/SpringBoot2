package com.adarsh.spring.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Getter
@Setter
public class Employee {
    @MongoId
    private long id;
    private String name;
    private String department;
}
