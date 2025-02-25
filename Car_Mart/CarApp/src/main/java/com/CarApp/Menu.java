package com.CarApp;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void addCar(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Company: ");
        String company = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Seater Capacity: ");
        int seater = scanner.nextInt();
        System.out.print("Enter Fuel Type: ");
        String fuelType = scanner.next();
        System.out.print("Enter Type (Hatchback/Sedan/SUV): ");
        String type = scanner.next();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Car car = new Car(0, company, model, seater, fuelType, type, price, false);
        carDAO.addCar(car);
    }

    public static void searchCar(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        Car car = carDAO.getCarById(carID);
        if (car != null) {
            System.out.println(car);
        } else {
            System.out.println("Car not found!");
        }
    }

    public static void updateCarPrice(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        System.out.print("Enter New Price: ");
        double newPrice = scanner.nextDouble();
        carDAO.updateCarPrice(carID, newPrice);
    }

    public static void markCarAsSold(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        carDAO.markCarAsSold(carID);
    }

    public static void displayAllCars(CarDAO carDAO) {
        List<Car> cars = carDAO.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars found!");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }
}
