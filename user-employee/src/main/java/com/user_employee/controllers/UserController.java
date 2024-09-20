package com.user_employee.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.user_employee.model.User;
import com.user_employee.services.userImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public userImpl userImpl;
    
    @PostMapping("/setUser")
    public String setUser(@RequestBody User user){
        return userImpl.saveUser(user);
    }
    @GetMapping("/getUser/{id}")   
    public User getUser(@PathVariable Integer id){
        return userImpl.getUser(id);
    }
    @PutMapping("updateUser/{id}")
    public String updateUser(@PathVariable Integer id,@RequestBody User user){
        return userImpl.updateUser(id, user);
    }
    @DeleteMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id){
        return userImpl.deleteUser(id);
    }
}
