package com.yusuf.Booking;

import com.yusuf.Car.Car;
import com.yusuf.Car.CarDAO;
import com.yusuf.Car.CarFileDataAccsessService;
import com.yusuf.User.User;
import com.yusuf.User.UserDAO;
import com.yusuf.User.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private final BookingDAO bookingDAO;
    private final CarDAO carDAO;
    private final UserDAO userDAO;

    public BookingService(BookingDAO bookingDAO, CarDAO carDAO, UserDAO userDAO) {
        this.bookingDAO = bookingDAO;
        this.carDAO = carDAO;
        this.userDAO = userDAO;
    }

    public BookingService(BookingDAO bookingDAO, CarDAO carDAO, UserDAO userDAO) {
        this.bookingDAO = bookingDAO;
        this.carDAO = carDAO;
        this.userDAO = userDAO;
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getUserBookings(UUID userId) {
        List<Booking> allBookings = bookingDAO.getAllBookings();
        int count = 0;

        for (Booking booking : allBookings) {
            if (booking.getUserPurchased().equals(userId)) {
                count++;
            }
        }

        if (count == 0) {
            return new ArrayList<>();
        }

        List<Booking> userBookings = new ArrayList<>(count);
        int index = 0;

        for (Booking booking : allBookings) {
            if (booking.getUserPurchased().equals(userId)) {
                userBookings.add(booking);
                index++;
            }
        }

        return userBookings;
    }

    public Booking bookCar(UUID userId, UUID carId) {

        boolean userExists = false;
        for (User user : userDAO.getAllUsers()) {
            if (user.getUserId().equals(userId)) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("User not found!");
            return null;
        }

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

        Booking booking = new Booking(
                UUID.randomUUID(),
                LocalDateTime.now(),
                userId,
                carId
        );

        bookingDAO.save(booking);
        carDAO.setCarOccupied(carId);

        return booking;
    }
}
