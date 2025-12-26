package com.yusuf.booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Booking {

    private UUID bookingId;
    private LocalDateTime timeOfPurchase;
    private UUID userPurchased;
    private UUID carPurchased;

    public Booking(UUID bookingId, LocalDateTime timeOfPurchase, UUID userPurchased, UUID carPurchased) {
        this.bookingId = bookingId;
        this.timeOfPurchase = timeOfPurchase;
        this.userPurchased = userPurchased;
        this.carPurchased = carPurchased;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public UUID getUserPurchased() {
        return userPurchased;
    }

    public void setUserPurchased(UUID userPurchased) {
        this.userPurchased = userPurchased;
    }

    public UUID getCarPurchased() {
        return carPurchased;
    }

    public void setCarPurchased(UUID carPurchased) {
        this.carPurchased = carPurchased;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "Booking{" +
                "bookingId=" + bookingId +
                ", timeOfPurchase=" + timeOfPurchase.format(formatter) +
                ", userPurchased=" + userPurchased +
                ", carBooked=" + carPurchased +
                '}';
    }
}
