package com.example.bookingsystem.repository.accommadationRepo;

import com.example.bookingsystem.entities.Accommodation;

public class AccommodationRepoImp {

    private AccommodationRepo accommodationRepo;

    public AccommodationRepoImp() {
    }

    public AccommodationRepoImp(AccommodationRepo accommodationRepo) {
        this.accommodationRepo = accommodationRepo;
    }


    public void save(Accommodation accommodation) {
        accommodationRepo.save(accommodation);
    }

    public void deleteById(Long id) {
        accommodationRepo.deleteById(id);
    }

    public Iterable<Accommodation> findAll() {
        return accommodationRepo.findAll();
    }

    public Accommodation findById(Long id) {
        return accommodationRepo.findById(id).orElse(null);
    }

    public Accommodation update(Accommodation accommodation) {
        return accommodationRepo.save(accommodation);
    }
}
