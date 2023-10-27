package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Activity;
import com.example.bookingsystem.entities.Addon;
import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.entities.User;
import com.example.bookingsystem.repository.activityRepo.ActivityRepoImp;
import com.example.bookingsystem.repository.tripRepo.TripRepo;
import com.example.bookingsystem.repository.tripRepo.TripRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TripService {

    private final TripRepo tripRepoImp;
    private final UserService userService;
    private final ActivityService activityService;
    private final AddonService addonService;


    public TripService(TripRepo tripRepoImp, UserService userService, ActivityService activityService, AddonService addonService) {
        this.tripRepoImp = tripRepoImp;
        this.userService = userService;
        this.activityService = activityService;
        this.addonService = addonService;
    }

    public Trip createTrip(Trip trip) {
        return tripRepoImp.save(trip);
    }

    public Optional<Trip> getTripById(Long id) {
        Optional<Trip> trip = tripRepoImp.findById(id);
        trip.ifPresent(value -> System.out.println(value.getActivities()));
        return trip;
    }

    public Trip updateTrip(Trip trip) {
        return tripRepoImp.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepoImp.deleteById(id);
    }

    public Iterable<Trip> getAllTrips() {
        return tripRepoImp.findAll();
    }


        public Trip addTripUser(Long tripId, Long userId) {
            Trip trip = tripRepoImp.findById(tripId).orElse(null);
            User user = userService.getUserById(userId);

            if (trip != null && user != null) {
                trip.addTripUser(user);
                return tripRepoImp.save(trip);
            }

            // Handle cases where the trip or user is not found
            return null;
        }

        public Trip addTripActivity(Long tripId, Long activityId) {
            Trip trip = tripRepoImp.findById(tripId).orElse(null);
            Activity activity = activityService.findById(activityId);

            if (trip != null && activity != null) {
                trip.addTripActivity(activity);
                return tripRepoImp.save(trip);
            }

            // Handle cases where the trip or activity is not found
            return null;
        }

        public Trip addTripAddon(Long tripId, Long addonId) {
            Trip trip = tripRepoImp.findById(tripId).orElse(null);
            Addon addon = addonService.findById(addonId);

            if (trip != null && addon != null) {
                trip.addTripAddon(addon);
                return tripRepoImp.save(trip);
            }

            // Handle cases where the trip or addon is not found
            if (trip == null) {
                System.out.println("Trip is null");
            }

            if (addon == null) {
                System.out.println("Addon is null");
            }
            return null;
        }
    }


