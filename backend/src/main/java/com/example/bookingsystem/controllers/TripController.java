package com.example.bookingsystem.controllers;


import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.services.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/")
    public void createTrip(@RequestBody Trip trip) {
        tripService.createTrip(trip);
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        Optional<Trip> trip = tripService.getTripById(id);
        return trip.orElse(null);
    }

    @PutMapping("/")
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

    @GetMapping("/")
    public Iterable<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }


    @PostMapping("/{tripId}/addUser/{userId}")
    public ResponseEntity<Trip> addTripUser(
            @PathVariable Long tripId,
            @PathVariable Long userId
    ) {
        Trip trip = tripService.addTripUser(tripId, userId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{tripId}/addActivity/{activityId}")
    public ResponseEntity<Trip> addTripActivity(
            @PathVariable Long tripId,
            @PathVariable Long activityId
    ) {
        Trip trip = tripService.addTripActivity(tripId, activityId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{tripId}/addAddon/{addonId}")
    public ResponseEntity<Trip> addTripAddon(
            @PathVariable Long tripId,
            @PathVariable Long addonId
    ) {
        Trip trip = tripService.addTripAddon(tripId, addonId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
