package com.adarsh.spring.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;


@RedisHash
@Getter
@Setter
public class Employee {

    private long id;
    private String name;
    private String department;
}
