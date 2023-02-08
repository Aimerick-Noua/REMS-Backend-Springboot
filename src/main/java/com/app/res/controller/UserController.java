package com.app.res.controller;

import com.app.res.agents.agentClass;
import com.app.res.dao.UserDao;
import com.app.res.entity.User;
import com.app.res.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/user")
    public List<User> getAgent() {
        return userService.getUser();
    }

    @PostConstruct
    public void initRoleAndUsers(){
        userService.initRoleAndUser();
    }

    @PostMapping({"/register"})
    public User registerNewUser(@RequestBody User user){
    return userService.registerNewUser(user);
    }

//    @PostMapping({"/user"})
//    public User registerNewUsers(@RequestBody User user){
//        return userService.registerNewUser(user);
//    }


    @DeleteMapping(path = "/user/{userId}")
    public void deleteStudent(@PathVariable ("userId") String userId){
        userService.deleteAgent(userId);
    }
    @PutMapping(path="/user/{userId}")
    public ResponseEntity<User> updateAgent(@PathVariable(value = "userId") String userId, @RequestBody User new_user) {
        User user = userDao.findById(userId).orElseThrow(()->new IllegalStateException("error while attempting to delete data"));
        user.setUserName(new_user.getUserName());
        user.setUserFirstName(new_user.getUserFirstName());
        user.setUserLastName(new_user.getUserLastName());
        user.setUserEmail(new_user.getUserEmail());
        user.setUserPassword(new_user.getUserPassword());
        final User agent_new = userDao.save(user);
        return ResponseEntity.ok(agent_new);
    }


    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This url is only accessible to admin";
    }
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This url is only accessible to user";
    }
}
