package by.epamtc.birukov.service;

import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;

import java.util.List;

public interface ClientService {

    AuthenticationData authentication(String login, String password) throws ServiceException;

    boolean registration(UserRegForm user) throws ServiceException;

    User getSettings(String login) throws ServiceException;

    List<User> showAllUsers() throws ServiceException;

}
