package com.lqh.demo.controller;

import com.lqh.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query(User user) {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(new User());
        users.add(new User());
        System.out.println(users);
        return users;
    }
}
