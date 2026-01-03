package com.yusuf.car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
class CarFileDataAccsessServiceTest {

    @TempDir
    Path tempDir;

    private CarFileDataAccsessService carFileDataAccsessService;

    @BeforeEach
    void setUp() throws Exception {
        Path carsFile = tempDir.resolve("cars.txt");
        Files.writeString(
                carsFile,
                """
                2f3c1c6e-8c2a-4d3b-9c9f-3c7e8a1b4e21,Tesla,Model 3,120.0,false,true
                7a4e9b2d-1c6f-4f3a-8e2d-91c6b8a4f0d3,BMW,3 Series,95.0,false,false
                c8b1f3e4-6a9d-4f2c-9b7a-2d4e6f8a1c3b,Audi,A4,100.0,true,false
                4d6a9f2b-8c3e-4a7d-9f1b-6e2c8a3d4b7e,Mercedes,C-Class,110.0,false,false
                9e2f4c6a-1b8d-4a3f-9c7e-5d6b2a8f1c4e,Tesla,Model Y,130.0,true,true
                1c7e9a4b-6d2f-4c8a-9b3e-5f2d6a1c8e4b,Toyota,Corolla,70.0,false,false
                b6e4c1d8-9f2a-4e7b-8c3d-1a6f5e2b9c4a,Volkswagen,Golf,80.0,true,false
                3a9c7f4e-2d6b-4a8e-9c1f-b5d2e6a4c8f1,Hyundai,Kona,85.0,false,true
                f4b8c6e2-9a1d-4c7e-8f3b-2d6a9c5e1b4a,Ford,Focus,75.0,false,false
                8c2e4a9b-6f1d-4e3c-9a7b-5f2d1c6e8b4a,Kia,Niro,90.0,true,true
                """
        );
        carFileDataAccsessService =
                new CarFileDataAccsessService(carsFile.toString());
    }

    @Test
    void canGetAllCarsFromFile() {
        List<Car> cars = carFileDataAccsessService.getAllCars();

        assertThat(cars).hasSize(10);
    }

    @ParameterizedTest
    @CsvSource({
            "2f3c1c6e-8c2a-4d3b-9c9f-3c7e8a1b4e21"
    })
    void canSetCarOccupied(UUID carId) {

        // before
        Car carBefore = carFileDataAccsessService.getAllCars().stream()
                .filter(c -> c.getCarId().equals(carId))
                .findFirst()
                .orElseThrow();

        assertThat(carBefore.isOccupied()).isFalse();

        // when
        carFileDataAccsessService.setCarOccupied(carId);

        // after
        Car carAfter = carFileDataAccsessService.getAllCars().stream()
                .filter(c -> c.getCarId().equals(carId))
                .findFirst()
                .orElseThrow();

        assertThat(carAfter.isOccupied()).isTrue();
    }
}
