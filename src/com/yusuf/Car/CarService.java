package com.yusuf.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public void setCarOccupied(java.util.UUID carId) {
        carDAO.setCarOccupied(carId);
    }

    public List<Car> getAvailableCars() {
        List<Car> cars = carDAO.getAllCars();

        int count = 0;
        for (Car car : cars) {
            if (!car.isOccupied()) {
                count++;
            }
        }

        if (count == 0) {
            return new ArrayList<>();
        }

        List<Car> availableCars = new ArrayList<>(count);
        int index = 0;

        for (Car car : cars) {
            if (!car.isOccupied()) {
           availableCars.add(car);
            }
        }

        return availableCars;
    }

    public List<Car> getAvailableElectricCars() {
        List<Car> cars = carDAO.getAllCars();

        int count = 0;
        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                count++;
            }
        }

        if (count == 0) {
            return new ArrayList<>();
        }

        List<Car> availableElectricCars = new ArrayList<>(count);
        int index = 0;

        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
             availableElectricCars.add(car);
            }
        }

        return availableElectricCars;
    }
}
