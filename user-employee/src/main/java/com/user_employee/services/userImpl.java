package com.user_employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user_employee.model.User;
import com.user_employee.repository.userRepo;

@Service
public class userImpl {
    @Autowired
    public userRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    // private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String saveUser(User user){
        // user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "user added";
    }
    public User getUser(Integer id){
        return userRepo.findById(id).orElse(null);
    }
    public String deleteUser(Integer id){
        userRepo.deleteById(id);
        return "User Deleted";
    }
    public String updateUser(Integer id,User user){
       User u = getUser(id);
        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        saveUser(u);
        return "updated successfully";
    }
    public String verify(User user) {
        try {
            System.out.println("Authenticating user: " + user.getUsername()); // Log username
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return JwtTokenUtil.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage()); // Log error
            return "failed: " + e.getMessage();
        }
        return "failed";
    }
    
    
}
