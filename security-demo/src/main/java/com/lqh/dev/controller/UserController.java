package com.lqh.dev.controller;

import com.lqh.dev.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> query() {
        return null;
    }
}
