package com.yusuf.booking;

import java.util.List;
import java.util.UUID;

public interface BookingDAO {

    List<Booking> getAllBookings();

    List<Booking> getUserBookings(UUID userId);

    void save(Booking booking);
}
