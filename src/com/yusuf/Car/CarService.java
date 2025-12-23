package com.yusuf.Car;

public class CarService {
    private final CarDAO carDAO = new CarDAO();

    public Car[] showAvailableCars() {
        Car[] cars = carDAO.findAllCars();
        int count = 0;
        for (Car car : cars) {
            if (!car.isOccupied()) {
                count++;
            }
        }
        if(count==0){
            return null;
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

    public Car[] showAvailableElectricCars() {
        Car[] cars = carDAO.findAllCars();
        int count = 0;
        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                count++;
            }
        }
        if(count==0){
            return null;
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