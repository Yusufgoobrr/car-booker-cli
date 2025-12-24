package com.yusuf;

import com.yusuf.Booking.BookingDAO;
import com.yusuf.Booking.BookingFileDataAccessService;
import com.yusuf.Booking.BookingService;
import com.yusuf.Car.CarDAO;
import com.yusuf.Car.CarFileDataAccsessService;
import com.yusuf.Car.CarService;
import com.yusuf.User.UserDAO;
import com.yusuf.User.UserFileDataAccsessService;
import com.yusuf.User.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserFileDataAccsessService();
        CarDAO carDAO = new CarFileDataAccsessService();
        BookingDAO bookingDAO = new BookingFileDataAccessService();
        CarService carService = new CarService(carDAO);
        UserService userService = new UserService(userDAO);
        BookingService bookingService = new BookingService(bookingDAO, carDAO, userDAO);
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

                    if (bookings.size() == 0) {
                        System.out.println("No bookings found for this user ❌");
                    } else {
                        for (var booking : bookings) {
                            System.out.println(booking);
                            System.out.println();
                        }
                    }
                }

                case 3 -> {
                    var bookings = bookingService.getAllBookings();

                    if (bookings.size() == 0) {
                        System.out.println("The booking list is empty ❌");
                    } else {
                        for (var booking : bookings) {
                            System.out.println(booking);
                            System.out.println();
                        }
                    }
                }

                case 4 -> {
                    var cars = carService.getAllCars();

                    boolean found = false;
                    for (var car : cars) {
                        if (!car.isOccupied()) {
                            System.out.println(car);
                            System.out.println();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("There are no available cars ❌");
                    }
                }

                case 5 -> {
                    var cars = carService.getAllCars();

                    boolean found = false;
                    for (var car : cars) {
                        if (car.isElectric() && !car.isOccupied()) {
                            System.out.println(car);
                            System.out.println();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No available electric cars ❌");
                    }
                }

                case 6 -> {
                    var users = userService.getAllUsers();

                    if (users.size() == 0) {
                        System.out.println("There are no users in the system ❌");
                    } else {
                        for (var user : users) {
                            System.out.println(user);
                            System.out.println();
                        }
                    }
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
