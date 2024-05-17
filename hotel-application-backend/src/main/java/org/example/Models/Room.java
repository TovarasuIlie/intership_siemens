package org.example.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roomNumber;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    @Embedded
    private Hotel hotel;
    private int type;
    private int price;
    @Column(name = "is_available")
    private boolean isAvailable;

    public Room() {
    }

    public Room(int roomNumber, Hotel hotel, int type, int price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.hotel = hotel;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", hotel=" + hotel +
                ", type=" + type +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
