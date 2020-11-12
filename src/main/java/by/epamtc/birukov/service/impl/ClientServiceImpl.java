package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.UserDAO;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;
import by.epamtc.birukov.service.validator.ServiceValidator;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    //возврат null если пользователь не найден
    @Override
    public User authentication(String login, String password) throws ServiceException {

        if (!ServiceValidator.isDataCorrect(login, password)) {
            throw new ServiceException("Login or password if incorrect");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        User user = null;
        try {
            user = userDAO.authentication(login, password);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean registration(UserRegForm user) throws ServiceException {

        //todo проверка параметров валидатором
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();


        try {
            return userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User getSettings(String login) throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User user;
        try {
            user = new User();
            user = userDAO.authorization(login);

        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> showAllUsers() throws ServiceException {

        List<User> listOfUsers = new ArrayList<>();

        try {

            DAOProvider daoProvider = DAOProvider.getInstance();
            UserDAO userDAO = daoProvider.getUserDAO();


            listOfUsers = userDAO.showAllUsers();

        } catch (DAOException e) {
            throw new ServiceException(e);
            //todo log
        }
        return listOfUsers;
    }

}
