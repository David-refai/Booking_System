package com.example.bookingsystem.repository.tripRepo;

import com.example.bookingsystem.entities.Trip;
import com.example.bookingsystem.repository.tripRepo.TripRepo;
import org.springframework.stereotype.Repository;


@Repository
public class TripRepoImp {

    private TripRepo TripRepo;

    public TripRepoImp(TripRepo tripTripRepo) {
        this.TripRepo = tripTripRepo;
    }

    public Trip createTrip(Trip trip) {
        return TripRepo.save(trip);
    }

    public Trip getTripById(Long id) {
        return TripRepo.findById(id).orElse(null);
    }

    public Trip save(Trip trip) {
        return TripRepo.save(trip);
    }

    public void deleteTrip(Long id) {
        TripRepo.deleteById(id);
    }

    public Iterable<Trip> getAllTrips() {
        return TripRepo.findAll();
    }

// saveAll
    public void saveAll(Iterable<Trip> trips) {
        TripRepo.saveAll(trips);
    }
}
