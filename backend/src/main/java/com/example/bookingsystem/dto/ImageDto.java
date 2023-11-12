package com.example.bookingsystem.dto;

import com.example.bookingsystem.entities.Image;
import lombok.Data;


@Data
public class ImageDto {
    private byte[] imageData;
    private String ImageName; // If storing images as byte arrays

}
