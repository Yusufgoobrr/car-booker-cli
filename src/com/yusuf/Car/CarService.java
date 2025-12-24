package com.yusuf.Car;

public class CarService {

    private final CarDAO carDAO = new CarFileDataAccsessService();

    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public void setCarOccupied(java.util.UUID carId) {
        carDAO.setCarOccupied(carId);
    }

    public Car[] getAvailableCars() {
        Car[] cars = carDAO.getAllCars();

        int count = 0;
        for (Car car : cars) {
            if (!car.isOccupied()) {
                count++;
            }
        }

        if (count == 0) {
            return new Car[0];
        }

        Car[] availableCars = new Car[count];
        int index = 0;

        for (Car car : cars) {
            if (!car.isOccupied()) {
                availableCars[index++] = car;
            }
        }

        return availableCars;
    }

    public Car[] getAvailableElectricCars() {
        Car[] cars = carDAO.getAllCars();

        int count = 0;
        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                count++;
            }
        }

        if (count == 0) {
            return new Car[0];
        }

        Car[] availableElectricCars = new Car[count];
        int index = 0;

        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                availableElectricCars[index++] = car;
            }
        }

        return availableElectricCars;
    }
}
