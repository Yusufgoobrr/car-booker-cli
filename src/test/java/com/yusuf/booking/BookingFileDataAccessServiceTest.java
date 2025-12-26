package com.yusuf.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BookingFileDataAccessServiceTest {

    private BookingFileDataAccessService bookingDAO;

    @BeforeEach
    void setUp() {
        bookingDAO = new BookingFileDataAccessService();
    }

    @Test
    void canGetAllBookingsFromFile() {
        // when
        List<Booking> bookings = bookingDAO.getAllBookings();

        // then
        assertThat(bookings).isNotEmpty();
    }

    @Test
    void canGetBookingsForSpecificUser() {
        // given (MUST match file data → KEEP hard-coded)
        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        // when
        List<Booking> userBookings = bookingDAO.getUserBookings(userId);

        // then
        assertThat(userBookings)
                .extracting(Booking::getUserPurchased)
                .containsOnly(userId);
    }

    @Test
    void canReturnEmptyListIfUserHasNoBookings() {
        // given (reviewer: UUID.random)
        UUID nonExistingUser = UUID.randomUUID();

        // when
        List<Booking> bookings = bookingDAO.getUserBookings(nonExistingUser);

        // then
        assertThat(bookings).isEmpty();
    }

    @Test
    void canSaveBookingToFile() {
        // given
        Booking booking = new Booking(
                UUID.randomUUID(),
                LocalDateTime.now(),
                UUID.randomUUID(),
                UUID.randomUUID()
                );

        int sizeBefore = bookingDAO.getAllBookings().size();
        int expectedSize = sizeBefore + 1;

        // when
        bookingDAO.save(booking);

        // then
        List<Booking> bookingsAfter = bookingDAO.getAllBookings();

        assertThat(bookingsAfter)
                .hasSize(expectedSize)
                .extracting(Booking::getBookingId)
                .contains(booking.getBookingId());
    }
}
