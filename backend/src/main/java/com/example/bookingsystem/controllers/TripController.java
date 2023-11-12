package com.example.bookingsystem.controllers;


import com.example.bookingsystem.dto.TripDTO;
import com.example.bookingsystem.entities.Image;
import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.repository.image.ImageRepo;
import com.example.bookingsystem.services.ImageService;
import com.example.bookingsystem.services.TripService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController implements Serializable {
    private final TripService tripService;

    //    @Autowired
    private final ImageService imageService;
    //    @Autowired
    private final ImageRepo imageRepo;


    public TripController(TripService tripService, ImageService imageService, ImageRepo imageRepo) {
        this.tripService = tripService;
        this.imageService = imageService;
        this.imageRepo = imageRepo;

    }


    // Set the path to save images
    @PostMapping(value = "/createTrip",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public String createTripWithImages(@RequestPart("trip") TripDTO tripDTO, @RequestPart("files") MultipartFile[] files) {


        // Your existing code ...
        Trip newTrip = new Trip();
        newTrip.setName(tripDTO.getName());
        newTrip.setDescription(tripDTO.getDescription());
        newTrip.setDescription(tripDTO.getDescription());
        newTrip.setStartDate(tripDTO.getStartDate());
        newTrip.setEndDate(tripDTO.getEndDate());
        newTrip.setPrice(Double.parseDouble(tripDTO.getPrice()));

        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                Image image = new Image();
                try {
                    String originalFileName = file.getOriginalFilename();

                    image.setImageData(file.getBytes());
                    image.setTrip(newTrip);
                    image.setImageName(originalFileName);

                    assert originalFileName != null;
                    String[] parts = originalFileName.split("\\.");
                    String fileNameToSave = parts[0] + "_resized." + parts[1];
//
                    // Set the path to save the resized image
                    //    @Value("${image.save.path}")
                    String imageSavePath = "backend/src/main/resources/static/images/";
                    String savePath = imageSavePath + fileNameToSave;

                    int targetWidth = 100; // Set the desired width for the resized image
                    imageService.resizeAndSaveImage(file, savePath, targetWidth);

                    newTrip.saveTripImageUrl(image);
                } catch (IOException e) {
                    return "Error handling image data";
                }
            }
        } else {
            return "No files were uploaded.";
        }

        tripService.createTrip(newTrip);
        return "Trip created with images successfully!";
    }








    @GetMapping("/{tripId}/images/{imageId}")
    public ResponseEntity<byte[]> getImageData(@PathVariable Long tripId, @PathVariable Long imageId) {
        // Retrieve image data for a specific trip and image ID
        byte[] imageData = tripService.getImageData(tripId, imageId); // Custom method to retrieve image data

        // Return the image data with appropriate headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentType(MediaType.IMAGE_GIF);
        headers.setContentLength(imageData.length);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TripDTO>> getAllImageData() {
        List<Trip> allTrips = tripService.getAllTrips();
        List<TripDTO> tripDTOs = new ArrayList<>();

        for (Trip trip : allTrips) {
            List<Image> tripImages = tripService.retrieveLargeObjects(trip.getId());
            ;
            TripDTO tripDTO = TripDTO.builder()
                    .id(trip.getId())
                    .name(trip.getName())
                    .description(trip.getDescription())
                    .destination(trip.getDestination())
                    .startDate(trip.getStartDate())
                    .endDate(trip.getEndDate())
                    .price(String.valueOf(trip.getPrice()))
                    .images(tripImages)
                    .build();
            tripDTOs.add(tripDTO);

        }

        return ResponseEntity.ok(tripDTOs);
    }


    @GetMapping("/{id}")
    public TripDTO getTripById(@PathVariable Long id) {
        Optional<Trip> trip = tripService.getTripById(id);
        List<Image> tripImages = tripService.retrieveLargeObjects(id);
        return trip.map(value -> TripDTO.builder()
                .id(value.getId())
                .name(value.getName())
                .description(value.getDescription())
                .destination(value.getDestination())
                .startDate(value.getStartDate())
                .endDate(value.getEndDate())
                .price(String.valueOf(value.getPrice()))
                .images(tripImages)
                .build()).orElse(null);

    }


    @PutMapping("/")
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }


// This method is used to save images as byte arrays

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


    //    saveAll
    @PostMapping("/saveAll")
    public void saveAll(@RequestBody Iterable<Trip> trips) {
        tripService.saveAll(trips);
    }


}
