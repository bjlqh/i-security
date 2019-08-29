package com.lqh.security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryCondition implements Serializable {
    private String username;
    private int age;
    private int ageTo;
    private String xxx;
}
