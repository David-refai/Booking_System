package com.example.bookingsystem.repository.userRepo;

import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.repository.userRepo.UserRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImp {
    private UserRepo userRepo;

    public UserRepoImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByToken(String token) {
        return userRepo.findByToken(token);
    }

    public User getUserByTokenExpiry(String tokenExpiry) {
        return userRepo.findByTokenExpiry(tokenExpiry);
    }
}
