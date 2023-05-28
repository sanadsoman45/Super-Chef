package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/superchef")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{emailid}")
    public HashMap<String,String> getUserByEmailId(@PathVariable String emailid){
        return userService.getUserByEmailId(emailid);
    }

    @DeleteMapping("/users/{emailid}")
    public String deleteUserById(@PathVariable String emailid){
        boolean check1 = userService.deleteUser(emailid);
        boolean check2 = userService.checkUserByEmailId(emailid);
        String msg = "Records Deletion Failed";
        if(check1 != check2){
            msg = "Records deleted for: "+emailid;
        }
        return msg;
    }

    @PutMapping("/user/{emailid}")
    public String updateUser(@RequestBody User user, @PathVariable String emailid){
        return "No of records updated is: "+userService.updateUser(user,emailid);
    }

    @GetMapping("/users/login")
    public String loginUser(@RequestBody User user){
        return userService.loginUser(user.getUserEmail(),user.getPasswd());
    }


}
