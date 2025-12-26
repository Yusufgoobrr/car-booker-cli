package com.yusuf.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                .hasSize(6)
                .allMatch(car -> !car.isOccupied());
    }


    @Test
    void canReturnOnlyAvailableElectricCars() {
        // when
        List<Car> availableElectricCars = carService.getAvailableElectricCars();

        // then
        assertThat(availableElectricCars)
                .isNotEmpty()
                .allMatch(car -> !car.isOccupied())
                .allMatch(Car::isElectric);
    }
}
