package com.user_employee.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    public int id;
    public String username;
    public String password;

    @OneToMany(fetch = FetchType.LAZY) 
    @JoinTable(
        name = "user_employee", // Name of the join table
        joinColumns = @JoinColumn(name = "user_id"), // Foreign key column for user
        inverseJoinColumns = @JoinColumn(name = "employee_id") // Foreign key column for employee
    )
    public List<Employee> emplist;
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Employee> getList() {
        return emplist;
    }
    public void setList(List<Employee> emplist) {
        this.emplist = emplist;
    }
    public String getUsername() {
        return username;
    }
    public User(String username, String password,int id) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User() {
    }
}
