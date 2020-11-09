package by.epamtc.birukov.dao;

import by.epamtc.birukov.dao.impl.SQLTestDAO;
import by.epamtc.birukov.dao.impl.SQLUserDAO;
import by.epamtc.birukov.entity.User;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new SQLUserDAO();

    private final TestDAO testDAO = new SQLTestDAO();

    public static DAOProvider getInstance(){
        return instance;
    }

    private DAOProvider(){
    }


    public UserDAO getUserDAO(){
        return userDAO;
    }
    public TestDAO getTestDAO(){
        return testDAO;
    }


}
