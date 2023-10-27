package com.example.bookingsystem.repository.activityRepo;

import com.example.bookingsystem.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity, Long> {
}
