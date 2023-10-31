package com.example.bookingsystem.controllers;

import com.example.bookingsystem.services.TripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/trips/{tripId}/images")
public class ImageUploadController {

    private TripImageService tripImageService;

    @Autowired
    public void TripImageController(TripImageService tripImageService) {
        this.tripImageService = tripImageService;
    }

    @PostMapping
    public String uploadImageToTrip(
            @PathVariable Long tripId,
            @RequestParam("file") MultipartFile file,
            @RequestParam String url) {
        return tripImageService.uploadImageToTrip(tripId, file, url);
    }


    @GetMapping
    public ResponseEntity<List<String>> getTripImages(@PathVariable Long tripId) {
        List<String> imageUrls = tripImageService.getImagesForTrip(tripId);
        if (imageUrls != null) {
            return new ResponseEntity<>(imageUrls, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


