package com.yusuf.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarServiceTest {

    private CarService carService;
    private CarDAO carDAO;

    @BeforeEach
    void setUp() {
        carDAO = mock(CarDAO.class);
        carService = new CarService(carDAO);
    }

    @Test
    void canReturnOnlyNotOccupiedCars() {
        // given
        when(carDAO.getAllCars()).thenReturn(List.of(
                new Car(null, "Tesla", "Model 3", 120, false, true),
                new Car(null, "BMW", "3 Series", 95, true, false),
                new Car(null, "Audi", "A4", 100, false, false)
        ));

        // when
        List<Car> availableCars = carService.getAvailableCars();

        // then
        assertThat(availableCars)
                .hasSize(2)
                .allMatch(car -> !car.isOccupied());
    }

    @Test
    void canReturnOnlyAvailableElectricCars() {
        // given
        when(carDAO.getAllCars()).thenReturn(List.of(
                new Car(null, "Tesla", "Model 3", 120, false, true),
                new Car(null, "Tesla", "Model Y", 130, true, true),
                new Car(null, "BMW", "i4", 110, false, true)
        ));

        // when
        List<Car> availableElectricCars = carService.getAvailableElectricCars();

        // then
        assertThat(availableElectricCars)
                .hasSize(2)
                .allMatch(car -> !car.isOccupied())
                .allMatch(Car::isElectric);
    }
}
