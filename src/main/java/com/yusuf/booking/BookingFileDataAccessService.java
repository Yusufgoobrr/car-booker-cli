package com.yusuf.booking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookingFileDataAccessService implements BookingDAO {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path FILE_PATH;

    public BookingFileDataAccessService() {
        try {
            this.FILE_PATH = Path.of(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource("bookings.txt")
                    ).toURI()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load bookings.txt", e);
        }
    }

    public BookingFileDataAccessService(Path filePath) {
        this.FILE_PATH = filePath;
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Booking booking = new Booking(
                        UUID.fromString(parts[0]),
                        LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER),
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
        List<Booking> result = new ArrayList<>();

        for (Booking booking : getAllBookings()) {
            if (booking.getUserPurchased().equals(userId)) {
                result.add(booking);
            }
        }

        return result;
    }

    @Override
    public void save(Booking booking) {

        String line =
                booking.getBookingId() + "," +
                        booking.getTimeOfPurchase().format(DATE_TIME_FORMATTER) + "," +
                        booking.getUserPurchased() + "," +
                        booking.getCarPurchased();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND)) {

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Failed to save booking to file");
        }
    }
}
