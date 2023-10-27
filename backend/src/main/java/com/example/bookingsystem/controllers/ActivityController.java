package com.example.bookingsystem.controllers;


import com.example.bookingsystem.dto.ActivityDTO;
import com.example.bookingsystem.entities.Activity;
import com.example.bookingsystem.services.ActivityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/")
    public void createActivity(@RequestBody Activity activity) {
        activityService.addActivity(activity);
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.findById(id);
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activity) {
        return activityService.update(id, activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivityById(id);
    }

    @GetMapping("/")
    public Iterable<Activity> getAllActivities() {
        return activityService.findAll();
    }
}
