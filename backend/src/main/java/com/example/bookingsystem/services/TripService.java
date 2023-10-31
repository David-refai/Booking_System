package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.*;
import com.example.bookingsystem.repository.tripRepo.TripRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TripService {

    private final TripRepo tripRepoImp;
    private final UserService userService;
    private final ActivityService activityService;
    private final AddonService addonService;
    private final ImageService imageService;

    public TripService(TripRepo tripRepoImp, UserService userService, ActivityService activityService, AddonService addonService, ImageService imageService) {
        this.tripRepoImp = tripRepoImp;
        this.userService = userService;
        this.activityService = activityService;
        this.addonService = addonService;
        this.imageService = imageService;
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

        public Trip removeTripUser(Long tripId, Long userId) {
            Trip trip = tripRepoImp.findById(tripId).orElse(null);
            User user = userService.getUserById(userId);

            if (trip != null && user != null) {
                trip.removeTripUser(user);
                return tripRepoImp.save(trip);
            }

            // Handle cases where the trip or user is not found
            return null;
        }

        public Trip removeTripActivity(Long tripId, Long activityId) {
            Trip trip = tripRepoImp.findById(tripId).orElse(null);
            Activity activity = activityService.findById(activityId);

            if (trip != null && activity != null) {
                trip.removeTripActivity(activity);
                return tripRepoImp.save(trip);
            }

            // Handle cases where the trip or activity is not found
            return null;
        }

//        saveAll
        public void saveAll(Iterable<Trip> trips) {
            tripRepoImp.saveAll(trips);
        }

    public Trip addTripImage(Long tripId, Long imageId) {
        Trip trip = tripRepoImp.findById(tripId).orElse(null);

        Image image = imageService.findById(imageId);

        if (trip != null && image != null) {
            trip.setTripImageUrl(image.getImageUrl());

            return tripRepoImp.save(trip);
        }
        return null;
    }
}


