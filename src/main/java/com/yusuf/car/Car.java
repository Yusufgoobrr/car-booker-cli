package com.yusuf.car;

import java.util.Objects;
import java.util.UUID;

public class Car {

    private UUID carId;
    private String brand;
    private String model;
    private double price;
    private boolean occupied;
    private boolean electric;

    public Car(UUID carId, String brand, String model, double price, boolean occupied, boolean electric) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.occupied = occupied;
        this.electric = electric;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", occupied=" + occupied +
                ", electric=" + electric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(price, car.price) == 0 && occupied == car.occupied && electric == car.electric && Objects.equals(carId, car.carId) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, brand, model, price, occupied, electric);
    }
}
