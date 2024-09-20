package com.user_employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user_employee.model.User;

public interface userRepo extends JpaRepository<User,Integer>{
    User findByusername(String username);
}
