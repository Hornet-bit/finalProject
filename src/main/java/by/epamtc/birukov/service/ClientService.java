package by.epamtc.birukov.service;

import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;

public interface ClientService {

    User authentication(String login, String password) throws ServiceException;

    boolean registration(UserRegForm user) throws ServiceException;

    User getSettings(String login) throws ServiceException;

}
