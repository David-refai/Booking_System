package com.example.bookingsystem.repository.accommadationRepo;

import com.example.bookingsystem.entities.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepo extends JpaRepository<Accommodation, Long> {
}
