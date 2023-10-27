package com.example.bookingsystem.repository.activityRepo;

import com.example.bookingsystem.entities.Activity;

public class ActivityRepoImp {
    private ActivityRepo activityRepo;

    public ActivityRepoImp() {
    }

    public ActivityRepoImp(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    public void save(Activity activity) {
        activityRepo.save(activity);
    }

    public void deleteById(Long id) {
        activityRepo.deleteById(id);
    }

    public Iterable<Activity> findAll() {
        return activityRepo.findAll();
    }

    public Activity findById(Long id) {
        return activityRepo.findById(id).orElse(null);
    }

    public Activity update(Activity activity) {
        return activityRepo.save(activity);
    }


}
