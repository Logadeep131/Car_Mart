package com.CarApp;

public class DAOFactory {
    public static CarDAO getCarDAO() {
        return new CarDAOImpl();
    }
}
