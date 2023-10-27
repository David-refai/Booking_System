package com.example.bookingsystem.repository.userRepo;

import com.example.bookingsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByToken(String token);

    User findByTokenExpiry(String tokenExpiry);
}
