package com.example.bookingsystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "accommodation")
public class Accommodation {

    @Id
    @SequenceGenerator(
            name = "accommodation_sequence",
            sequenceName = "accommodation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "accommodation_sequence")
    private Long id;

    private String name;
    private String description;
    private double pricePerNight;
    private int capacity;
    private String location;

    @OneToOne(mappedBy = "accommodation")
    private Trip trip;

    public Accommodation() {
    }
}
