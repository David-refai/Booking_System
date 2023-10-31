package com.example.bookingsystem.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "user_sequence")
    private Long id;
    private String username;
    private String email;
    private String password;

    private String role;
    private String gender;
    private String token;
    private String tokenExpiry;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_trip",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private Set<Trip> trips = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image profileImage;
    public User() {

    }

    //    add trip to user
    public void addTrip(Trip trip) {
        this.trips.add(trip);
        trip.getUsers().add(this);
    }

}