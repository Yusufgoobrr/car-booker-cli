package com.yusuf.Booking;

import com.yusuf.Car.Car;
import com.yusuf.Car.CarDAO;
import com.yusuf.Car.CarFileDataAccsessService;
import com.yusuf.User.User;
import com.yusuf.User.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {

    private final BookingDAO bookingDAO = new BookingFileDataAccessService();
    private final CarDAO carDAO = new CarFileDataAccsessService();
    private final UserService userService = new UserService();

    public Booking[] getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    // ✅ THIS IS YOUR METHOD — IT NEVER SHOULD HAVE LEFT
    public Booking[] getUserBookings(UUID userId) {

        Booking[] allBookings = bookingDAO.getAllBookings();
        int count = 0;

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

        for (Booking booking : allBookings) {
            if (booking.getUserPurchased().equals(userId)) {
                userBookings[index++] = booking;
            }
        }

        return userBookings;
    }

    public Booking bookCar(UUID userId, UUID carId) {

        // 1️⃣ check user exists
        boolean userExists = false;
        for (User user : userService.getAllUsers()) {
            if (user.getUserId().equals(userId)) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("User not found!");
            return null;
        }

        // 2️⃣ find car from FILE
        Car selectedCar = null;
        for (Car car : carDAO.getAllCars()) {
            if (car.getCarId().equals(carId)) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Car not found!");
            return null;
        }

        if (selectedCar.isOccupied()) {
            System.out.println("Car already occupied!");
            return null;
        }

        // 3️⃣ create booking
        Booking booking = new Booking(
                UUID.randomUUID(),
                LocalDateTime.now(),
                userId,
                carId
        );

        // 4️⃣ persist booking to FILE
        bookingDAO.save(booking);

        // 5️⃣ update car FILE
        carDAO.setCarOccupied(carId);

        return booking;
    }
}
