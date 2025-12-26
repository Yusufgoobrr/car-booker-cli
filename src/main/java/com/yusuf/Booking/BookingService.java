package com.yusuf.Booking;

import com.yusuf.Car.Car;
import com.yusuf.Car.CarDAO;
import com.yusuf.User.User;
import com.yusuf.User.UserDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {

    private final BookingDAO bookingDAO;
    private final CarDAO carDAO;
    private final UserDAO userDAO;

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
        List<Booking> userBookings =  allBookings.stream().filter(booking -> booking.getUserPurchased().equals(userId)).collect(Collectors.toList());
        if (userBookings.isEmpty()) {
            return new ArrayList<>();
        }
        return userBookings;
    }

    public Booking bookCar(UUID userId, UUID carId) {
        List<User> users = userDAO.getAllUsers();
        List<Car> cars = carDAO.getAllCars();
        if (users.isEmpty() || !users.contains(userId)) {
            System.out.println("User not found!");
            return null;
        }
        if (cars.isEmpty() || !cars.contains(carId)) {
            System.out.println("Car not found!");
            return null;
        }if (cars.contains(carId)) {
                System.out.println("Car already occupied!");
                return null;
            }

       List<Car> foundCar = cars.stream().filter(s -> s.getCarId().equals(carId) && !s.isOccupied()).toList();

            Booking booking = new Booking(
                    UUID.randomUUID(),
                    LocalDateTime.now(),
                    userId,
                    carId
            );

            bookingDAO.save(booking);
            carDAO.setCarOccupied(foundCar.get(0).getCarId());

            return booking;
        }
    }
