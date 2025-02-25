package com.CarApp;

import java.util.List;

public interface CarDAO {
    void addCar(Car car);
    Car getCarById(int carId);
    void updateCarPrice(int carId, double price);
    void markCarAsSold(int carId);
    List<Car> getAllCars();
}
