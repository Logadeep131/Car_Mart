package com.CarApp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        CarDAO carDAO = DAOFactory.getCarDAO();

        while (true) {
            System.out.println("Car Management System");
            System.out.println("1.Add Car");
            System.out.println("2.Search Car");
            System.out.println("3.Update Car Price");
            System.out.println("4.Mark Car as Sold");
            System.out.println("5.Display All Cars");
            System.out.println("6.Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> Menu.addCar(scanner, carDAO);
                    case 2 -> Menu.searchCar(scanner, carDAO);
                    case 3 -> Menu.updateCarPrice(scanner, carDAO);
                    case 4 -> Menu.markCarAsSold(scanner, carDAO);
                    case 5 -> Menu.displayAllCars(carDAO);
                    case 6 -> {
                        System.out.println("Exiting the program...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.next(); 
            }
        }
    }
}
