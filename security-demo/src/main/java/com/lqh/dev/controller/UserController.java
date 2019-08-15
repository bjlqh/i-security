package com.lqh.dev.controller;

import com.lqh.dev.dto.UserCondition;
import com.lqh.dev.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(UserCondition condition) {
        System.out.println(condition);
        ArrayList<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }
}
