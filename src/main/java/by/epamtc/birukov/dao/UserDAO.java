package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;

import java.util.List;

public interface UserDAO {

    User authentication(String login, String password) throws DAOException;

    boolean registration(UserRegForm user) throws DAOException;

    User authorization(String login) throws DAOException;

    List<User> showAllUsers() throws DAOException;

}
