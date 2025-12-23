package com.yusuf.Booking;

import java.util.Arrays;

public class BookingDAO {
    private Booking[] bookings = new Booking[100];
    private int currentSize = 0;

    public Booking[] getAllBookings() {
        return Arrays.copyOf(bookings, currentSize);
    }

    public void save(Booking booking) {
        if (currentSize >= bookings.length) {
            bookings = Arrays.copyOf(bookings, bookings.length + 100);
        }

        bookings[currentSize] = booking;
        currentSize++;
    }
}
