package com.yusuf.Car;

import java.util.UUID;

public class CarService implements CarDAO {
    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(UUID.fromString("9d818235-ce3b-40e8-b74a-3674985c6bcd"), "Tofas", "Kulustur", 1000.9, false, false),
                new Car(UUID.fromString("87cb62d9-d262-4174-b1b2-957f9e2a1f40"), "Tesla", "Model 3", 2000.21, false, true)

        };
    }


    @Override
    public Car[] getAllCars() {
        return cars;
    }

    @Override
    public void setCarOccupied(UUID carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                car.setOccupied(true);
                break;
            }
        }
    }

    public Car[] getAvailableCars() {
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