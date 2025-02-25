package com.CarApp;

import com.database.DBconnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private Connection con;

    public CarDAOImpl() {
        try {
            this.con = DBconnect.getConnection();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }

    @Override
    public void addCar(Car car) {

        try (PreparedStatement pstmt = con.prepareStatement("insert into cars (model, company, seater, fuelType, type, price, is_sold) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getCompany());
            pstmt.setInt(3, car.getSeater());
            pstmt.setString(4, car.getFuelType());
            pstmt.setString(5, car.getType());
            pstmt.setDouble(6, car.getPrice());
            pstmt.setBoolean(7, car.isSold());

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Car added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting car: " + e.getMessage());
        }
    }

    @Override
    public Car getCarById(int carId) {
        
        try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM cars WHERE car_id = ?")) {
            pstmt.setInt(1, carId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    printCarDetails(rs);
                } else {
                    System.out.println("Car not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching car by ID: " + e.getMessage());
        }
		return null;
    }

    @Override
    public void updateCarPrice(int carId, double price) {
     
        try (PreparedStatement pstmt = con.prepareStatement("UPDATE cars SET price = ? WHERE car_id = ?")) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, carId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car price updated successfully!");
            } else {
                System.out.println("Car ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating car price: " + e.getMessage());
        }
    }

    @Override
    public void markCarAsSold(int carId) {
    
        try (PreparedStatement pstmt = con.prepareStatement("UPDATE cars SET is_sold = true WHERE car_id = ?")) {
            pstmt.setInt(1, carId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car marked as sold!");
            } else {
                System.out.println("Car ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error marking car as sold: " + e.getMessage());
        }
    }

    @Override
    public List getAllCars() {
       
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery( "SELECT * FROM cars")) {
            boolean hasData = false;
            while (rs.next()) {
                printCarDetails(rs);
                hasData = true;
            }
            if (!hasData) {
                System.out.println("No cars available.");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all cars: " + e.getMessage());
        }
		return null;
    }

    private void printCarDetails(ResultSet rs) throws SQLException {
        System.out.println("Car ID: " + rs.getInt("car_id") +
                ", Company: " + rs.getString("company") +
                ", Model: " + rs.getString("model") +
                ", Seater: " + rs.getInt("seater") +
                ", Fuel Type: " + rs.getString("fuelType") +
                ", Type: " + rs.getString("type") +
                ", Price: $" + rs.getDouble("price") +
                ", Sold: " + (rs.getBoolean("is_sold") ? "Yes" : "No"));
    }
}
