package com.yusuf.Car;

public class CarService {

    public void showAvailableCars(Car[] cars) {
        boolean isFound = false;
        for (Car car : cars) {
            if (!car.isOccupied()) {
                        isFound = true;
                System.out.println(
                        "Car Id: " + car.getCarId() + "\n" +
                                "Brand: " + car.getBrand() + "\n" +
                                "Model: " + car.getModel() + "\n" +
                                "Price: " + car.getPrice() + "\n" +
                                "IsOccupied: " + car.isOccupied() + "\n" +
                                "IsElectric: " + car.isElectric() + "\n"
                );
            }
        }
        if(isFound){
            return;
        }else{
            System.out.println("There is no available cars ❌");
        }
    }

    public void showAvailableElectricCars(Car[] cars) {
        boolean isFound = false;
        for (Car car : cars) {
            if (car.isElectric() && !car.isOccupied()) {
                isFound = true;
                System.out.println(
                        "Car Id: " + car.getCarId() + "\n" +
                                "Brand: " + car.getBrand() + "\n" +
                                "Model: " + car.getModel() + "\n" +
                                "Price: " + car.getPrice() + "\n" +
                                "IsOccupied: " + car.isOccupied() + "\n" +
                                "IsElectric: " + car.isElectric() + "\n"
                );
            }
        }if(isFound){
            return;
        }else{
            System.out.println("No available electric cars at the moment ❌");
        }
    }
}
