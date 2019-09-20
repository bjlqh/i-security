package com.lqh.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.security.demo.pojo.User;
import com.lqh.security.demo.pojo.UserQueryCondition;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
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

    @GetMapping("/{id:\\d++}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    /**
     * @param user
     * @param errors @Valid 会根据User类里面的注解 如@NotBlank的错误信息会被放到errors里。
     * @return
     */
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(s -> System.out.println(s.getDefaultMessage()));
        }
        System.out.println(user);
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {

            errors.getAllErrors().forEach(error -> {
                        if (error instanceof FieldError) {
                            //String message = ((FieldError) error).getField() +" : "+ error.getDefaultMessage();
                            // /System.out.println(message);
                            System.out.println(error.getDefaultMessage());
                        }
                    }
            );
        }

        System.out.println(user);
        user.setId("1");
        return user;
    }
}
