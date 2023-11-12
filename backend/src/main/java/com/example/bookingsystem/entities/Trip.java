package com.example.bookingsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
//@Builder
@NoArgsConstructor
@Table(name = "trip")
public class Trip implements java.io.Serializable {
    @Id
    @SequenceGenerator(
            name = "trip_sequence",
            sequenceName = "trip_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "trip_sequence")
    private Long id;
    private String name;
    private String description;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDate createdAt = LocalDate.now();
    private String updatedAt;
    private String destination;

    @ManyToMany(mappedBy = "trips")
    private Set<User> users = new HashSet<>();

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "trip_activity",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    @JsonIgnoreProperties("trips")
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "trip_addon",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_id")
    )
    private Set<Addon> addons = new HashSet<>();

    //    image
    @OneToMany( mappedBy = "trip",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
            , fetch = FetchType.LAZY)
//    @JoinColumn(name = "trip_id")
    @JsonIgnoreProperties("trip")
    private List<Image> images;

    public Trip(Long id, String name, String description,
                double price, LocalDate startDate,
                LocalDate endDate, LocalDate createdAt,
                Set<User> users, Accommodation accommodation,
                String destination,
                Set<Activity> activities, Set<Addon> addons, List<Image> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.users = users;
        this.accommodation = accommodation;
        this.activities = activities;
        this.addons = addons;
        this.images = images;
        this.destination = destination;
    }

    public Trip(String name, String description, LocalDate startDate, LocalDate endDate, double v, String destination) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = v;
        this.createdAt = LocalDate.now();
        this.destination = destination;


    }

    //    addTripUser
    public void addTripUser(User user) {
        this.users.add(user);
        user.getTrips().add(this);
    }

    //    removeTripUser
    public void removeTripUser(User user) {
        this.users.remove(user);
        user.getTrips().remove(this);
    }

    //    addTripActivity
    public void addTripActivity(Activity activity) {
        this.activities.add(activity);
        activity.getTrips().add(this);
    }

    //    removeTripActivity
    public void removeTripActivity(Activity activity) {
        this.activities.remove(activity);
        activity.getTrips().remove(this);
    }

    //    addTripAddon
    public void addTripAddon(Addon addon) {
        this.addons.add(addon);
        addon.getTrips().add(this);
    }

    //    removeTripAddon
    public void removeTripAddon(Addon addon) {
        this.addons.remove(addon);
        addon.getTrips().remove(this);
    }

    public void saveTripImageUrl(Image image) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }

        this.images.add(image);
        image.setTrip(this);

    }


}
