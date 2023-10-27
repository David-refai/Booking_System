package com.example.bookingsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @SequenceGenerator(
            name = "trip_sequence",
            sequenceName = "trip_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY, generator = "trip_sequence")
    private Long id;
    private String name;
    private String description;
    private String destination;
    private String startDate;
    private String endDate;
    private String price;
    private String status;
    private String createdAt;
    private String updatedAt;

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

}