package com.yusuf.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarFileDataAccsessService implements CarDAO {

    private static final String FILE_PATH = "src/com/yusuf/Car/cars.txt";

    @Override
    public List<Car> getAllCars() {

        List<Car> cars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                Car car = new Car(
                        UUID.fromString(parts[0]),
                        parts[1],
                        parts[2],
                        Double.parseDouble(parts[3]),
                        Boolean.parseBoolean(parts[4]),
                        Boolean.parseBoolean(parts[5])
                );

                cars.add(car);
            }

        } catch (IOException e) {
            System.out.println("Failed to read cars file");
        }

        return cars;
    }

    @Override
    public void setCarOccupied(UUID carId) {

        List<Car> cars = getAllCars();
        if (!cars.contains(carId)) {
            System.out.println("Car not found");
            return;
        }
        cars.stream().filter(car -> car.getCarId().equals(carId)).distinct().forEach(car -> car.setOccupied(true));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Car car : cars) {
                String line =
                        car.getCarId() + "," +
                                car.getBrand() + "," +
                                car.getModel() + "," +
                                car.getPrice() + "," +
                                car.isOccupied() + "," +
                                car.isElectric();

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to update car occupancy");
        }
    }
}
