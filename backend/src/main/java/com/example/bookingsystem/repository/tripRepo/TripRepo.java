package com.example.bookingsystem.repository.tripRepo;

import com.example.bookingsystem.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface TripRepo extends JpaRepository<Trip, Long> {


//    @Query("SELECT t, t.images FROM Trip t JOIN t.images i WHERE t.id = i.trip.id")
//    List<Trip> findAllTrips();

//    @Query("SELECT DISTINCT t FROM Trip t LEFT JOIN FETCH t.images")
    @Query("SELECT DISTINCT t FROM Trip t LEFT JOIN t.images")
    List<Trip> findAllTripsWithImages();

}
