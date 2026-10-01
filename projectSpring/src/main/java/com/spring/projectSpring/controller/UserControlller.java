package com.spring.projectSpring.controller;

import com.spring.projectSpring.entity.User;
import com.spring.projectSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControlller {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User>getAll(){
       return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateEntry(
            @RequestBody User user,
            @PathVariable("username") String userName) {

        User userInDb = userService.findByUsername(userName);

        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());

            userService.saveEntry(userInDb);

            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
