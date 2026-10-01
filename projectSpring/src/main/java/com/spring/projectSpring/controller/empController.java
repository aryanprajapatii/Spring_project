package com.spring.projectSpring.controller;


import com.spring.projectSpring.entity.Employee;
import com.spring.projectSpring.entity.User;
import com.spring.projectSpring.service.EmployeeService;
import com.spring.projectSpring.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class empController {

@Autowired
private EmployeeService employeeService;

@Autowired
private UserService userService;

    //DBREF CONNECTION
    @GetMapping("{userName}")
    public ResponseEntity<?>getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUsername(userName);
        List<Employee> all = user.getEmployees();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    //Post Mapping for DBREF
    @PostMapping("{userName}")
    public ResponseEntity<Employee>createEntry (@RequestBody Employee emp , @PathVariable String userName) {
        try {
            employeeService.saveentry(emp);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Employee>getById(@PathVariable ObjectId myId){
        Optional<Employee> employee = employeeService.findById(myId);
         if (employee.isPresent()){
             return new ResponseEntity<>(employee.get(), HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<Employee>updateId(@RequestBody Employee emp, @PathVariable ObjectId myId) {
        Employee old = employeeService.findById(myId).orElse(null);
        if (old != null) {
            old.setName(emp.getName() != null && !emp.getName().equals("") ? emp.getName() : old.getName());
            old.setCity(emp.getCity() != null && !emp.getCity().equals(" ") ? emp.getCity() : old.getCity());
            old.setSalary(emp.getSalary() != null && !emp.getSalary().equals(" ") ? emp.getSalary() : old.getSalary());
            employeeService.saveentry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//DELEET MAPPING FOR DBREF CONNECTION
@DeleteMapping("id/{userName}/{myId}")
public ResponseEntity<?>deleteById(@PathVariable ObjectId myId, @PathVariable String userName){
        employeeService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}

