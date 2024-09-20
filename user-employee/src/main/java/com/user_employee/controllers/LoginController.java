package com.user_employee.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.user_employee.model.User;

import com.user_employee.services.userImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("log")
public class LoginController {
    @Autowired
    public userImpl userImpl;

    @PostMapping("/login")    
    public String login(@RequestBody User user){ // Ensure @RequestBody to capture JSON
        return userImpl.verify(user);
    }

    @GetMapping("/get")    
    public String login() {
        return "ok";
    }

}

