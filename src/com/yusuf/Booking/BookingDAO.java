package com.yusuf.Booking;

import java.util.Arrays;

public class BookingDAO {
    private Booking[] bookings = new Booking[0];
    public Booking[] findAll(){
        return bookings;
    }
    public void save(Booking booking) {
        bookings = Arrays.copyOf(bookings, bookings.length + 1);
        bookings[bookings.length - 1] = booking;
    }
}
