package com.example.bookingsystem;

import com.example.bookingsystem.dto.UserDTO;
import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.repository.userRepo.UserRepo;
import com.example.bookingsystem.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);


    }
}
