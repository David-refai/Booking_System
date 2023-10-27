package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.repository.userRepo.UserRepo;
import com.example.bookingsystem.repository.userRepo.UserRepoImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepoImp userRepoImp;

    public UserService(UserRepo userRepo) {
        this.userRepoImp = new UserRepoImp(userRepo);
    }

    public User getUserById(Long id) {
        User user = userRepoImp.getUserById(id);
        if (user == null) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepoImp.getUserByEmail(email);
    }

    public User saveUser(User user) {
        return userRepoImp.saveUser(user);
    }


    public void deleteUser(Long id) {
        userRepoImp.deleteUser(id);
    }

    public java.util.List<User> getAllUsers() {
        return userRepoImp.getAllUsers();
    }

        public User update(Long id, User updatedUser) {
            if (id == null) {
                throw new IllegalArgumentException("User id is null");
            }

            // Retrieve the existing user by their ID
            User existingUser = userRepoImp.getUserById(id);

            if (existingUser == null) {
                // Handle the case where the user with the given ID doesn't exist
                throw new EntityNotFoundException("User with ID " + id + " not found");
            }

            // Update the properties of the existing user with the new data
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // Update other user properties as needed

            // Save the updated user
            return userRepoImp.saveUser(existingUser);

    }







    public User getUserByToken(String token) {
        return userRepoImp.getUserByToken(token);
    }

    public User getUserByTokenExpiry(String tokenExpiry) {
        return userRepoImp.getUserByTokenExpiry(tokenExpiry);
    }


}
