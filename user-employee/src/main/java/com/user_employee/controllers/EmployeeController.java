package com.user_employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_employee.model.Employee;
import com.user_employee.model.User;
import com.user_employee.services.employeeImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    employeeImpl employeeImpl;
    
    @PostMapping("/setEmpl")
    public String setEmpl(@RequestBody Employee empl){
        return employeeImpl.saveEmpl(empl);
    }
    @GetMapping("/getEmpl/{id}")   
    public Employee getEmpl(@PathVariable Integer id){
        return employeeImpl.getEmpl(id);
    }
    @PutMapping("updateEmpl/{id}")
    public String updateEmpl(@PathVariable Integer id,@RequestBody Employee empl){
        return employeeImpl.updateEmpl(id, empl);
    }
    @DeleteMapping("deleteEmpl/{id}")
    public String deleteEmpl(@PathVariable Integer id){
        return employeeImpl.deleteEmpl(id);
    }
    @PutMapping("{emplid}/assignto/{uid}")
    public String assignEmployeeToUser(@PathVariable Integer uid,@PathVariable Integer emplid){
        return employeeImpl.assignUser(uid, emplid);
    }
}
