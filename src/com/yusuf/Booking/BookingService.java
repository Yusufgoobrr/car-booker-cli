package com.yusuf.Booking;

import com.yusuf.Car.Car;
import com.yusuf.User.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class BookingService {

    public void showAllBookings(Booking[] bookings) {
        for (Booking booking : bookings) {
            System.out.println(
                    "BookingId: "+booking.getBookingId() + "\n" +
                            "Date Of Purchase: "+booking.getTimeOfPurchase() +"\n"+
                            "Booker's UserId: " + booking.getUserPurchased()+"\n"+
                            "Booked CarId: " + booking.getCarPurchased()+"\n"
            );
        }
    }

    public void viewUserBookings(Booking[] bookings, UUID userId) {
        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.getUserPurchased().equals(userId)) {
                System.out.println(
                        "BookingId: "+booking.getBookingId() + "\n" +
                                "Date Of Purchase: "+booking.getTimeOfPurchase() +"\n"+
                                "Booker's UserId: " + userId+"\n"+
                                "Booked CarId: " + booking.getCarPurchased()+"\n"
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No bookings found for this user");
        }
    }

    public Booking[] bookCar(Booking[] bookings, User[] users, Car[] cars, UUID userId, UUID carId) {

        boolean userExists = false;
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("User not found!");
            return bookings;
        }

        Car selectedCar = null;
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Car not found!");
            return bookings;
        }

        if (selectedCar.isOccupied()) {
            System.out.println("Car is already occupied!");
            return bookings;
        }

        Booking newBooking = new Booking(
                UUID.randomUUID(),
                LocalDate.now(),
                userId,
                carId
        );

        bookings = Arrays.copyOf(bookings, bookings.length + 1);
        bookings[bookings.length - 1] = newBooking;
        selectedCar.setOccupied(true);

        System.out.println("Booking created successfully!");
        return bookings;
    }
}
