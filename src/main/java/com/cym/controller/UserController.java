package com.cym.controller;

import com.cym.dao.UserDao;
import com.cym.entity.Admin;
import com.cym.entity.Teacher;
import com.cym.entity.User;
import com.cym.service.JwtService;
import com.cym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewAdmin"})
    public User registerNewAdmin(@RequestBody User user) {
        return userService.registerNewAdmin(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Uuid = ((UserDetails) userPrincipal).getUsername();
        // User logInUser = (User) this.jwtService.loadUserByUsername(Uuid);
        System.out.println("+++++++++++++++++++++++++++++++++++ logInUser");
        System.out.println(Uuid);
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}


