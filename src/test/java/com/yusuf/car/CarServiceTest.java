package com.yusuf.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        CarDAO carDAO = new CarFileDataAccsessService();
        carService = new CarService(carDAO);
    }

    @Test
    void canReturnOnlyNotOccupiedCars() {
        // when
        List<Car> availableCars = carService.getAvailableCars();

        // then
        assertThat(availableCars)
                .isNotNull()
                .allMatch(car -> !car.isOccupied());
    }

    @Test
    void canReturnOnlyAvailableElectricCars() {
        // when
        List<Car> availableElectricCars = carService.getAvailableElectricCars();

        // then
        assertThat(availableElectricCars)
                .isNotNull()
                .allMatch(car -> !car.isOccupied() && car.isElectric());
    }
}
