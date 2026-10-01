package com.spring.projectSpring.service;


import com.spring.projectSpring.entity.Employee;
import com.spring.projectSpring.entity.User;
import com.spring.projectSpring.repo.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

@Autowired
private EmployeeRepository employeeRepository;

@Autowired
private UserService userService;

//post
    public void saveentry(Employee employee){

        User user = userService.findByUsername(userName);
        Employee save = employeeRepository.save(employee);
        user.getEmployees().add(save);
        userService.saveEntry(user);
    }
    public void saveEntry(Employee employee){
        employeeRepository.save(employee);
    }
    //get
    public List<Employee>getAll(){
        return employeeRepository.findAll();
    }
    //get
    public Optional<Employee>findById(ObjectId id){
        return employeeRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUsername(userName);
//        user.getEmployees().removeIf(x->x.getObjectId());
        userService.saveEntry(user);
        employeeRepository.deleteById(id);
    }
}
