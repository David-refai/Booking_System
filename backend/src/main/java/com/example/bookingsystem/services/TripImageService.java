package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.repository.tripRepo.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TripImageService {

    private final TripRepo tripRepository;
    private final ImageService imageService;

    @Autowired
    public TripImageService(TripRepo tripRepository, ImageService imageService) {
        this.tripRepository = tripRepository;
        this.imageService = imageService;
    }

    public String uploadImageToTrip(Long tripId, MultipartFile file, String url) {
        // Get the trip by ID
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();

            // Save the image and get its URL
            String imageUrl = imageService.saveImage(file, url); // Adjust this according to your image saving logic

            // Associate the image URL with the trip
            trip.setTripImageUrl(imageUrl);
            tripRepository.save(trip);

            return "Image uploaded and associated with the trip successfully.";
        } else {
            return "Trip with ID " + tripId + " not found.";
        }
    }

    public List<String> getImagesForTrip(Long tripId) {
        // Get the trip by ID
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();

            // Get the image URL associated with the trip
            String imageUrl = trip.getTripImageUrl();

            if (imageUrl != null && !imageUrl.isEmpty()) {
                // Return the image URL
                return List.of(imageUrl);
            } else {
                return List.of("No image URL found for Trip ID " + tripId);
            }
        } else {
            return List.of("Trip with ID " + tripId + " not found.");
        }
    }

}

