package com.lqh.security.demo.controller;

import com.lqh.security.demo.pojo.User;
import com.lqh.security.demo.pojo.UserQueryCondition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("user")
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

    @GetMapping("user/{id:\\d++}")
    public User getInfo(@PathVariable String id) {
        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
