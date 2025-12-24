package com.yusuf.Booking;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class BookingFileDataAccessService implements BookingDAO {

    private static final String FILE_PATH = "src/com/yusuf/Booking/bookings.txt";

    @Override
    public Booking[] getAllBookings() {

        Booking[] bookings = new Booking[100];
        int currentSize = 0;

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

                if (currentSize >= bookings.length) {
                    bookings = Arrays.copyOf(bookings, bookings.length + 100);
                }

                bookings[currentSize++] = booking;
            }

        } catch (IOException e) {
            System.out.println("Failed to read bookings file");
        }

        return Arrays.copyOf(bookings, currentSize);
    }

    @Override
    public Booking[] getUserBookings(UUID userId) {

        Booking[] allBookings = getAllBookings();
        int count = 0;

        // count matching bookings
        for (Booking booking : allBookings) {
            if (booking.getUserPurchased().equals(userId)) {
                count++;
            }
        }

        if (count == 0) {
            return new Booking[0];
        }

        Booking[] userBookings = new Booking[count];
        int index = 0;

        // collect matching bookings
        for (Booking booking : allBookings) {
            if (booking.getUserPurchased().equals(userId)) {
                userBookings[index++] = booking;
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
