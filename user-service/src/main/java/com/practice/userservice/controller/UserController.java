//package com.practice.userservice.controller;
//
//import com.practice.userservice.entity.User;
//import com.practice.userservice.grpc.model.GrcpUser;
//import com.practice.userservice.service.UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**------------------ This is for REST api-----------------------**/
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;

//    @PostMapping("/")
//    public User save(@RequestBody User user) {
//        return userService.save(user);
//    }

//    @GetMapping("/{id}")
//    public User getById(@PathVariable("id") Long id) {
//        Optional<User> user = userService.getById(id);
//        if(user.isPresent()){
//            return user.get();
//        }
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public boolean delete(@PathVariable("id") Long id) {
//        userService.deleteById(id);
//        return true;
//    }

//    @PutMapping("/{id}")
//    public User update(@PathVariable("id") Long id, @RequestBody User user) {
//        Optional<User> userData = userService.getById(id);
//        if(userData.isPresent()){
//            User _user = userData.get();
//            _user.setFirstName(user.getFirstName());
//            _user.setLastName(user.getLastName());
//            _user.setAge(user.getAge());
//            return userService.save(_user);
//        }
//        return null;
//    }
//}
