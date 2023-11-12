package com.example.bookingsystem.repository.image;


import com.example.bookingsystem.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

    @Query("SELECT i FROM Image i WHERE i.trip.id = ?1")
    List<Image> getImagesByTripId(Long id);
}
