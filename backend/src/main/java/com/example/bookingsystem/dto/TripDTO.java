package com.example.bookingsystem.dto;


import com.example.bookingsystem.entities.Image;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TripDTO {
    private Long id;
    private String name;
    private String description;
    private String price;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Image> images;
    private String destination;

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setImage(Image image) {
        this.images.add(image);
    }
//    base64Image



}
