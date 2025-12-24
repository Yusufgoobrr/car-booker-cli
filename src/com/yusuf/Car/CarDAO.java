package com.yusuf.Car;

import java.util.UUID;

public interface CarDAO {

    public Car[] getAllCars();

    public void setCarOccupied(UUID carId);
}
