-- create table users, trips, addon, activity, accommodation


-- V2__Add_trip_id_to_activities.sql

--     drop table trip_activity;
    DROP TABLE trip_activity;

-- Add the trip_id column to the activities table
ALTER TABLE activity
    ADD trip_id bigint;

-- Create a foreign key constraint on the trip_id column
ALTER TABLE activity
    ADD CONSTRAINT fk_trip_id
        FOREIGN KEY (trip_id)
            REFERENCES trip(id);
