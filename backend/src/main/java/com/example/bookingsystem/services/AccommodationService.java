package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Accommodation;
import com.example.bookingsystem.repository.accommadationRepo.AccommodationRepo;
import org.springframework.stereotype.Service;


@Service
public class AccommodationService {

    private final AccommodationRepo accommodationRepository;

    public AccommodationService(AccommodationRepo accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public void addAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    public void deleteAccommodationById(Long id) {
        accommodationRepository.deleteById(id);
    }

    public Iterable<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id).orElse(null);
    }

    public Accommodation update(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

}
