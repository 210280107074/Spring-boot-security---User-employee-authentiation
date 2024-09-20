package com.user_employee.services;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_employee.model.Employee;
import com.user_employee.model.User;
import com.user_employee.repository.employeeRepo;
import com.user_employee.repository.userRepo;

@Service
public class employeeImpl {
    @Autowired
    public employeeRepo employeeRepo;

    @Autowired
    public userImpl userImpl;

    public String saveEmpl(Employee employee){
        // user.setPassword(encoder.encode(user.getPassword()));
        employeeRepo.save(employee);
        return "employee added";
    }
    public Employee getEmpl(Integer id){
        return employeeRepo.findById(id).orElse(null);
    }
    public String deleteEmpl(Integer id){
        employeeRepo.deleteById(id);
        return "employee Deleted";
    }
    public String updateEmpl(Integer id,Employee empl){
        Employee e = getEmpl(id);
         e.setEid(empl.getEid());
         e.setEname(empl.getEname());
         e.setCity(empl.getCity());
         saveEmpl(e);
         return "updated successfully";
     }

     public String assignUser(Integer uid,Integer emplid){
        User u = userImpl.getUser(emplid);
        Employee e = getEmpl(emplid);
        if(u != null && e != null){
            List<Employee> elist = u.getList();
            elist.add(e);
            u.setList(elist);
            userImpl.saveUser(u);
            return "assigned";
        }
        else{
            return "user or employee not found";
        }

     }
}
