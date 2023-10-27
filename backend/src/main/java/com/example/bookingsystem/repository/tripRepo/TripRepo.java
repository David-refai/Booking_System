package com.example.bookingsystem.repository.tripRepo;

import com.example.bookingsystem.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface TripRepo extends JpaRepository<Trip, Long> {

}
