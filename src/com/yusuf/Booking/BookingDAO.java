package com.yusuf.Booking;

import java.util.UUID;

public interface BookingDAO {

    Booking[] getAllBookings();

    Booking[] getUserBookings(UUID userId);

    void save(Booking booking);
}
