package com.yusuf.Booking;

import java.awt.print.Book;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BookingFileDataAccessService implements BookingDAO {

    private static final String FILE_PATH = "src/com/yusuf/Booking/bookings.txt";

    @Override
    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Booking booking = new Booking(
                        UUID.fromString(parts[0]),
                        LocalDateTime.parse(parts[1]),
                        UUID.fromString(parts[2]),
                        UUID.fromString(parts[3])
                );

               bookings.add(booking);
            }

        } catch (IOException e) {
            System.out.println("Failed to read bookings file");
        }

        return bookings;
    }

    @Override
    public List<Booking> getUserBookings(UUID userId) {

        List<Booking> bookings = getAllBookings();
        int count = 0;
        for (Booking booking : bookings) {
            if (booking.getUserPurchased().equals(userId)) {
                count++;
            }
        }

        if (count == 0) {
            return new ArrayList<>();//come back to this
        }

        List<Booking> userBookings = new ArrayList<>(count);
        int index = 0;

        // collect matching bookings
        for (Booking booking : getAllBookings()) {
            if (booking.getUserPurchased().equals(userId)) {
                userBookings.add(booking);
                index++;
            }
        }

        return userBookings;
    }

    @Override
    public void save(Booking booking) {

        String line =
                booking.getBookingId() + "," +
                        booking.getTimeOfPurchase() + "," +
                        booking.getUserPurchased() + "," +
                        booking.getCarPurchased();

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Failed to save booking to file");
        }
    }
}
