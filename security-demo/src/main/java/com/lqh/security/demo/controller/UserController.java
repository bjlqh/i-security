package com.lqh.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.security.demo.dto.UserQueryCondition;
import com.lqh.security.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition) {
        System.out.println(condition);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        System.out.println(users);
        return users;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/user/{id:\\d++}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(required = false) int id) {
        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        user.setPassword(UUID.randomUUID().toString().substring(0,6));
        return user;
    }
}
