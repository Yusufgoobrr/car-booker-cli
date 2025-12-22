package com.yusuf;

import com.yusuf.Booking.Booking;
import com.yusuf.Booking.BookingService;
import com.yusuf.Car.Car;
import com.yusuf.Car.CarService;
import com.yusuf.User.User;
import com.yusuf.User.UserService;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        CarService carService = new CarService();
        BookingService bookingService = new BookingService();

        User[] users = {
                new User(UUID.fromString("2ea85178-fada-4279-9d5e-eea627049fa2"), "Yusuf", "Kaya"),
                new User(UUID.fromString("576590ff-57a1-4df3-8430-79980eb42343"), "Nelson", "Amigos-code")
        };

        Car[] cars = {
                new Car(UUID.fromString("9d818235-ce3b-40e8-b74a-3674985c6bcd"), "Tofas", "Kulustur", 1000.9, false, false),
                new Car(UUID.fromString("87cb62d9-d262-4174-b1b2-957f9e2a1f40"), "Tesla", "Model 3", 2000.21, false, true)
        };

        Booking[] bookings = new Booking[0];
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

                    bookings = bookingService.bookCar(bookings, users, cars, userId, carId);
                }

                case 2 -> {
                    System.out.print("Enter user Id: ");
                    UUID userId = UUID.fromString(scanner.next());
                    bookingService.viewUserBookings(bookings, userId);
                }

                case 3 -> {
                    if (bookings.length == 0) {
                        System.out.println("The booking list is empty");
                    } else {
                        bookingService.showAllBookings(bookings);
                    }
                }

                case 4 -> carService.showAvailableCars(cars);

                case 5 -> carService.showAvailableElectricCars(cars);

                case 6 -> userService.getAllUsers(users);

                case 7 -> {

                    System.out.println("Thanks for using the app 😊");
                    return;
                }

                default -> System.out.println("Please enter a valid choice!");
            }

        } while (choice != 7);
    }

}
