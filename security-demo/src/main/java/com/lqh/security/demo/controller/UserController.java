package com.lqh.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.security.demo.pojo.User;
import com.lqh.security.demo.pojo.UserQueryCondition;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("user")
    @JsonView(User.SimpleView.class)
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
    @JsonView(User.DetailView.class)
    public User getInfo(@PathVariable String id) {
        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping("user")
    @JsonView(User.DetailView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(s -> System.out.println(s.getDefaultMessage()));
        }
        System.out.println(user);
        user.setId(1);
        return user;
    }
}
