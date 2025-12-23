package com.yusuf.Booking;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {

    private UUID bookingId;
    private LocalDate timeOfPurchase;
    private UUID userPurchased;
    private UUID carPurchased;

    public Booking(UUID bookingId, LocalDate timeOfPurchase, UUID userPurchased, UUID carPurchased) {
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

    public LocalDate getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDate timeOfPurchase) {
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
        return
                "bookingId=" + bookingId + "\n" +
                        "timeOfPurchase=" + timeOfPurchase + "\n" +
                        "userPurchased=" + userPurchased + "\n" +
                        "carPurchased=" + carPurchased;
    }

}
