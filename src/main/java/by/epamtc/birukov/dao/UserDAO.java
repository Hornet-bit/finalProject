package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.*;
import by.epamtc.birukov.service.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface UserDAO {

    AuthenticationData authentication(String login, String password) throws DAOException;

    boolean registration(UserRegForm user) throws DAOException;

//    User authorization(String login) throws DAOException;
    User getSettings(int id) throws DAOException;

    List<User> showAllUsers() throws DAOException;

    void changeRole(String login) throws DAOException;

    String  uploadAvatar(AuthenticationData user, Part filePart) throws DAOException;

    List<UserAcademicPerformance> showJournal(String testName) throws DAOException;


}
