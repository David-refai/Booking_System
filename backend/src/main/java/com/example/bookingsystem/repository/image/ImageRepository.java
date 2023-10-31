package com.example.bookingsystem.repository.image;

import com.example.bookingsystem.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ImageRepository extends JpaRepository<Image, Long> {

}
