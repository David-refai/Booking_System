package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.*;
import com.example.bookingsystem.entities.Image;
import com.example.bookingsystem.repository.tripRepo.TripRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.reflect.Typed;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

@Service
@Transactional
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

    public void createTrip(Trip trip) {
        tripRepoImp.save(trip);
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



    public List<Trip> getAllTrips() {
        List<Trip> allTrips = tripRepoImp.findAllTripsWithImages();
        if (allTrips.isEmpty()) {
            System.out.println("No trips found");

        }
        return allTrips;
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


    @PersistenceContext
    private EntityManager entityManager; // Injecting EntityManager

    @Transactional
    public List<Image> retrieveLargeObjects( Long id) {
        // Obtain the Hibernate session from EntityManager
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<Image> query = session.createQuery("from Image where trip.id = :id", Image.class);

        query.setParameter("id", id);
        // Ensure large objects are fully loaded


        return query.getResultList();
    }


    public byte[] getImageData(Long tripId, Long imageId) {
        Trip trip = tripRepoImp.findById(tripId).orElse(null);

        if (trip != null) {
            Optional<Image> imageOptional = trip.getImages().stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if (imageOptional.isPresent()) {
                return imageOptional.get().getImageData();
            }
        }
        return new byte[0]; // Return empty array or handle not found scenario
    }


    public List<byte[]> getAllImageDataForTrip() {
        List<byte[]> allImageData = new ArrayList<>();

        Trip trip = tripRepoImp.findAll().get(0);

        if (trip != null) {
            List<Image> images = trip.getImages();

            for (Image image : images) {
                allImageData.add(image.getImageData());
            }
        }

        return allImageData;
    }

}


