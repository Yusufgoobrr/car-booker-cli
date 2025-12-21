package com.yusuf.Car;

public class CarService {

    public void showAvailableCars(Car[] cars) {
        for (Car car : cars) {
            if (!car.isOccupied()) {
                System.out.println(
                        car.getCarId() + " " +
                                car.getBrand() + " " +
                                car.getModel() + " " +
                                car.getPrice() + " " +
                                car.isOccupied() + " " +
                                car.isElectric()
                );
            }
        }
    }

    public void showAvailableElectricCars(Car[] cars) {
        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                System.out.println(
                        car.getCarId() + " " +
                                car.getBrand() + " " +
                                car.getModel() + " " +
                                car.getPrice() + " " +
                                car.isOccupied() + " " +
                                car.isElectric()
                );
            }
        }
    }
}
