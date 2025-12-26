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
        assertThat(bookings)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void canGetBookingsForSpecificUser() {
        // given
        UUID userId =
                UUID.fromString("11111111-1111-1111-1111-111111111111");

        // when
        List<Booking> userBookings = bookingDAO.getUserBookings(userId);

        // then
        assertThat(userBookings)
                .isNotNull()
                .allMatch(b -> b.getUserPurchased().equals(userId));
    }

    @Test
    void canReturnEmptyListIfUserHasNoBookings() {
        // given
        UUID nonExistingUser =
                UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

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
                UUID.fromString("11111111-1111-1111-1111-111111111111"),
                UUID.fromString("2f3c1c6e-8c2a-4d3b-9c9f-3c7e8a1b4e21")
        );

        int sizeBefore = bookingDAO.getAllBookings().size();

        // when
        bookingDAO.save(booking);

        // then
        List<Booking> bookingsAfter = bookingDAO.getAllBookings();

        assertThat(bookingsAfter)
                .hasSize(sizeBefore + 1)
                .anyMatch(b -> b.getBookingId().equals(booking.getBookingId()));
    }
}
