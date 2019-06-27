package com.lx.curriculum_vitae.controller;

import com.lx.curriculum_vitae.dao.UserDao;
import com.lx.curriculum_vitae.model.User;
import com.lx.curriculum_vitae.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @GetMapping(value = "/user")
    public List<User> getUser()
    {
        return  userService.getUserList();
    }
}
