package com.user_employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_employee.model.Employee;

public interface employeeRepo extends JpaRepository<Employee,Integer>{

}
