package com.lqh.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCondition implements Serializable {
    private String username;
    private int age;
    private int ageTo;
    private String xxx;
}
