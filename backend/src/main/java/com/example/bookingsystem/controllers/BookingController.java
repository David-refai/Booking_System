package com.example.bookingsystem.controllers;

import com.example.bookingsystem.services.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String getBooking() {
        return "Hello World";
    }

    @PostMapping("/{userId}/booking")
    public void createBooking(@PathVariable Long userId, @RequestParam List<Long> trip) {
        bookingService.addTripsToUser(userId, trip);
    }

}
