package com.example.bookingsystem.dto;

public record ActivityDTO (
     String name,
     String description,
     double pricePerPerson,
     int capacity,
     String location
) {
}
