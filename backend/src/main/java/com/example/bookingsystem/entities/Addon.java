package com.example.bookingsystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "addon")
public class Addon {
    @Id
    @SequenceGenerator(
            name = "addon_sequence",
            sequenceName = "addon_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "addon_sequence")
    private Long id;

    private String name;

    private String description;

    private double pricePerPerson;

    private int capacity;

    private String location;


    @ManyToMany(mappedBy = "addons")
    private Set<Trip> trips = new HashSet<>();

    public Addon() {
    }


    // add trip to addon
    public void addTrip(Trip trip) {
        this.trips.add(trip);
        trip.getAddons().add(this);
    }


}
