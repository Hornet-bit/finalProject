package by.epamtc.birukov.service;

import by.epamtc.birukov.entity.*;

import javax.servlet.http.Part;
import java.util.List;

public interface ClientService {
//todo обязательная валидация входящих параметров(можем ли с этими параметрами работать), в контроллере - техническая валидация - null/не null, тип переменных и пр
    AuthenticationData authentication(String login, String password) throws ServiceException;

    boolean registration(UserRegForm user) throws ServiceException;

    boolean checkAvailableUsernameAndEmail(UserRegForm user) throws ServiceException;

    User getSettings(String login) throws ServiceException;

    List<User> showAllUsers() throws ServiceException;

    void changeRole(String login) throws ServiceException;

    String uploadAvatar(AuthenticationData user, Part filePart) throws ServiceException;

    List<UserAcademicPerformance> showJournal(String testName) throws ServiceException;
}
