package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;

public interface UserDAO {

    User authentication(String login, String password) throws DAOException;

    boolean registration(UserRegForm user) throws DAOException;

    User authorization(String login) throws DAOException;



}
