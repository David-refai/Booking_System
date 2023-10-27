package com.example.bookingsystem.controllers;


import ch.qos.logback.classic.Logger;
import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;
    private Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
    try {
        return userService.saveUser(user);
    } catch (Exception e) {
        logger.error("Error saving user: " + e.getMessage());
        return null;
    }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users")
    public java.util.List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//
//    @PutMapping("/")
//    public User updateUser(@RequestBody User user) {
//        return userService.update(user.getId(), user);
//    }


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Use the 'id' from the URL to identify the user to update
        return userService.update(id, user);
    }

    @GetMapping("/test")
    public void getUserByToken() {
        System.out.println("test");
    }
}


