package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.UserDAO;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserAcademicPerformance;
import by.epamtc.birukov.entity.UserRegForm;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.validator.ServiceValidator;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    //todo возврат null если пользователь не найден
    @Override
    public AuthenticationData authentication(String login, String password) throws ServiceException {

        if (!ServiceValidator.isDataCorrect(login, password)) {
            throw new ServiceException("Login or password are incorrect");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        AuthenticationData authenticationData = null;

        try {
            authenticationData = userDAO.authentication(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
            //todo log
        }
        return authenticationData;
    }

    @Override
    public boolean registration(UserRegForm user) throws ServiceException {

        //todo проверка параметров валидатором
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        if(!ServiceValidator.isDataCorrect(user.getUsername(), user.getPassword())){
            return false;
        }

        try {
            return userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean checkAvailableUsernameAndEmail(UserRegForm user) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            return userDAO.checkAvailableUsernameAndEmail(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getSettings(String login) throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();
        User user = null;
//        try {
//            user = new User();
//            user = userDAO.authorization(login);
//
//        } catch (DAOException e) {
//            e.printStackTrace();
//            throw new ServiceException(e);
//        }
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

    @Override
    public void changeRole(String login) throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            userDAO.changeRole(login);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    @Override
    public String uploadAvatar(AuthenticationData user, Part filePart) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        String locationImage;

        try {
            locationImage = userDAO.uploadAvatar(user, filePart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return locationImage;
    }

    @Override
    public List<UserAcademicPerformance> showJournal(String testName) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

       List<UserAcademicPerformance> journal = null;

        try {
            journal = userDAO.showJournal(testName);
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return journal;
    }


}
