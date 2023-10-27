package com.example.bookingsystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "activity")
public class Activity {

        @Id
        @SequenceGenerator(
                name = "activity_sequence",
                sequenceName = "activity_sequence",
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "activity_sequence")
        private Long id;
        private String name;
        private String description;
        private double pricePerPerson;
        private int capacity;
        private String location;


        @ManyToMany(mappedBy = "activities")
        private Set<Trip> trips = new HashSet<>();

        public Activity() {
        }

        // add trip to activity
        public void addTrip(Trip trip) {
            this.trips.add(trip);
            trip.getActivities().add(this);
        }


}
