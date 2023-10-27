package com.example.bookingsystem.repository.addonRepo;

import com.example.bookingsystem.entities.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddonRepo extends JpaRepository<Addon, Long> {
}
