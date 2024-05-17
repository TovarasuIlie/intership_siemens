package org.example.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    @Embedded
    private Set<Room> rooms;

    public Hotel() {
    }

    public Hotel(int id, String name, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
        rooms.forEach(room -> room.setHotel(this));
    }

    public double calculateDistance(double userLatitude, double userLongitude) {
        double userLatitudeRad = Math.toRadians(userLatitude);
        double hotelLatiduteRad = Math.toRadians(this.latitude);
        double userLongitudeRad = Math.toRadians(userLongitude);
        double hotelLongitudeRad = Math.toRadians(userLongitude);

        double x = (hotelLongitudeRad - userLongitudeRad) * Math.cos((userLatitudeRad + hotelLatiduteRad) / 2);
        double y = (hotelLatiduteRad - userLatitudeRad);
        double distance = Math.sqrt(x * x + y * y) * 6371;

        return distance;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", rooms=" + rooms +
                '}';
    }
}
