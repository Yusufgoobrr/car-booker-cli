package com.yusuf.Booking;

import com.yusuf.Car.Car;
import com.yusuf.Car.CarDAO;
import com.yusuf.User.User;
import com.yusuf.User.UserDAO;

import java.time.LocalDate;
import java.util.UUID;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();
    private final UserDAO userDAO = new UserDAO();
    private final CarDAO carDAO = new CarDAO();

    public Booking[] showAllBookings() {
        return bookingDAO.findAll();
    }

    public Booking[] viewUserBookings(UUID userId) {
        int count = 0;
        for (Booking booking : bookingDAO.findAll()) {
            if (booking.getUserPurchased().equals(userId)) {
                count++;
            }
        }
        if(count==0){
            return null;
        }
        Booking[] userBookings = new Booking[count];
        int index = 0;
        for (Booking booking : bookingDAO.findAll()) {
            if (booking.getUserPurchased().equals(userId)) {
                userBookings[index++] = booking;
            }
        }
        return userBookings;
    }

    public Booking bookCar(UUID userId, UUID carId) {

        boolean userExists = false;
        for (User user : userDAO.findAllUsers()) {
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
        for (Car car : carDAO.findAllCars()) {
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
            System.out.println("Car is already occupied!");
            return null;
        }

        Booking newBooking = new Booking(
                UUID.randomUUID(),
                LocalDate.now(),
                userId,
                carId
        );

        bookingDAO.save(newBooking);
        selectedCar.setOccupied(true);

        return newBooking;
    }
}
