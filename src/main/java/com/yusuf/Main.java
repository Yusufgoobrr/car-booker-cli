package com.yusuf;

import com.yusuf.booking.BookingDAO;
import com.yusuf.booking.BookingFileDataAccessService;
import com.yusuf.booking.BookingService;
import com.yusuf.car.CarDAO;
import com.yusuf.car.CarFileDataAccsessService;
import com.yusuf.car.CarService;
import com.yusuf.user.UserDAO;
import com.yusuf.user.UserFakerDataAccsessService;
import com.yusuf.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserDAO userDAO = new UserFakerDataAccsessService();
        CarDAO carDAO = new CarFileDataAccsessService();
        BookingDAO bookingDAO = new BookingFileDataAccessService();

        CarService carService = new CarService(carDAO);
        UserService userService = new UserService(userDAO);
        BookingService bookingService =
                new BookingService(bookingDAO, carDAO, userDAO);

        int choice;

        do {
            System.out.println("""
                    ******* Car Booking CLI *******
                    1️⃣ - Book Car
                    2️⃣ - View User Bookings
                    3️⃣ - View All Bookings
                    4️⃣ - View Available Cars
                    5️⃣ - View Available Electric Cars
                    6️⃣ - View All Users
                    7️⃣ - Exit
                    """);

            System.out.print("Enter Your Action: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter user Id: ");
                    UUID userId = UUID.fromString(scanner.next());

                    System.out.print("Enter car Id: ");
                    UUID carId = UUID.fromString(scanner.next());

                    var booking = bookingService.bookCar(userId, carId);

                    if (booking == null) {
                        System.out.println("The booking can't be made ❌");
                    } else {
                        System.out.println("Successfully booked ✅");
                    }
                }

                case 2 -> {
                    System.out.print("Enter user Id: ");
                    UUID userId = UUID.fromString(scanner.next());

                    var bookings = bookingService.getUserBookings(userId);

                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found ❌");
                    } else {
                        bookings.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    var bookings = bookingService.getAllBookings();

                    if (bookings.isEmpty()) {
                        System.out.println("The booking list is empty ❌");
                    } else {
                        bookings.forEach(System.out::println);
                    }
                }

                case 4 -> {
                    carService.getAllCars().stream()
                            .filter(car -> !car.isOccupied())
                            .forEach(System.out::println);
                }

                case 5 -> {
                    carService.getAllCars().stream()
                            .filter(car -> car.isElectric() && !car.isOccupied())
                            .forEach(System.out::println);
                }

                case 6 -> {
                    userService.getAllUsers().forEach(System.out::println);
                }

                case 7 -> {
                    System.out.println("Thanks for using the app 😊");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Please enter a valid choice!");
            }

        } while (choice != 7);
    }
}
