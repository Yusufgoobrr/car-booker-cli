package com.yusuf.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Car> availableCars = cars.stream().filter(car -> !car.isOccupied()).toList();
        if (availableCars.isEmpty()) {
            return new ArrayList<>();
        }
        return availableCars;
    }

    public List<Car> getAvailableElectricCars() {
        List<Car> cars = carDAO.getAllCars();
        List<Car> availableElectricCars = cars.stream().filter(car -> !car.isOccupied()&& car.isElectric()).toList();

        if (availableElectricCars.isEmpty()) {
            return new ArrayList<>();
        }

        return availableElectricCars;
    }
}
