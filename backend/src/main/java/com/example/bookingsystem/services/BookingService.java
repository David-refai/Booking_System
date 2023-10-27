package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.repository.tripRepo.TripRepoImp;
import com.example.bookingsystem.repository.userRepo.UserRepoImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private UserRepoImp userRepoImp;
    private TripRepoImp tripRepoImp;


    public BookingService(UserRepoImp userRepoImp, TripRepoImp tripRepoImp) {
        this.userRepoImp = userRepoImp;
        this.tripRepoImp = tripRepoImp;
    }

    public void addTripsToUser(Long userId, List<Long> tripIds) {
        User user = userRepoImp.getUserById(userId);

        for (Long tripId : tripIds) {
            Trip trip = tripRepoImp.getTripById(tripId);
            user.addTrip(trip);
        }

        userRepoImp.saveUser(user);
    }






}
