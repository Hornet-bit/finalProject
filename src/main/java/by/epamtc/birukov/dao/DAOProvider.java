package by.epamtc.birukov.dao;

import by.epamtc.birukov.dao.impl.SQLUserDAO;
import by.epamtc.birukov.entity.User;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    public static DAOProvider getInstance(){
        return instance;
    }

    private DAOProvider(){
    }


    private final UserDAO userDAO = new SQLUserDAO();


    public UserDAO getUserDAO(){
        return userDAO;
    }


}
