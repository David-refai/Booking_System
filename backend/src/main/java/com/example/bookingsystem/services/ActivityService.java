package com.example.bookingsystem.services;

import com.example.bookingsystem.dto.ActivityDTO;
import com.example.bookingsystem.entities.Activity;
import com.example.bookingsystem.exception.DuplicateResourceException;
import com.example.bookingsystem.repository.activityRepo.ActivityRepo;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private final ActivityRepo activityRepository;

    public ActivityService(ActivityRepo activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityRepo getActivityRepository() {
        return activityRepository;
    }

    public void addActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    public Iterable<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Activity findById(Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        System.out.println(activity);
        return activity;
    }

    public Activity update(Long id, ActivityDTO activityUpdate) {
        Activity activity = activityRepository.findById(id).orElse(null);
        boolean updated = false;
//       ActivityDTO is record so we can't implement setter method so we have to use getter method
        if (activityUpdate.name() != null && !activityUpdate.name().equals(activity != null ? activity.getName() : null)) {
            activity.setName(activityUpdate.name());
            updated = true;
        }
        if (activityUpdate.description() != null && !activityUpdate.description().equals(activity != null ? activity.getDescription() : null)) {
            activity.setDescription(activityUpdate.description());
            updated = true;
        }
        if (activityUpdate.pricePerPerson() != 0 && !(activityUpdate.pricePerPerson() == (activity != null ? activity.getPricePerPerson() : 0))) {
            activity.setPricePerPerson(activityUpdate.pricePerPerson());
            updated = true;
        }
        if (activityUpdate.capacity() != 0 && !(activityUpdate.capacity() == activity.getCapacity())) {
            activity.setCapacity(activityUpdate.capacity());
            updated = true;
        }
        if (activityUpdate.location() != null && !activityUpdate.location().equals(activity.getLocation())) {
            activity.setLocation(activityUpdate.location());
            updated = true;
        }

        if (!updated) {
         throw new DuplicateResourceException("No changes were made");
        } else {
            return activityRepository.save(activity);
        }
    }
}
