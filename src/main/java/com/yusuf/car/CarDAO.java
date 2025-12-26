package com.yusuf.car;

import java.util.List;
import java.util.UUID;

public interface CarDAO {

    public List<Car> getAllCars();

    public void setCarOccupied(UUID carId);
}
