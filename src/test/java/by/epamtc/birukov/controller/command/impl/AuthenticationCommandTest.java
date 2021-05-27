package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.impl.SQLUserDAO;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthenticationCommandTest {

    SQLUserDAO userDAO = Mockito.spy(SQLUserDAO.class);

    @Test
    void execute() throws DAOException {
        String login = "ingv";
        String password = "1234";

        AuthenticationData auth = userDAO.authentication(login, password);

        assertEquals(auth.getUsername(), login);
    }

    @Test
    public void notRegUserWrongLogin() throws DAOException {
        String login = "wefesfesrg";
        String password = "3rfessf";
        AuthenticationData auth = userDAO.authentication(login, password);
        assertNull(auth);
    }

    @Test
    public void notRegUserEmptyLogin() throws DAOException {
        String login = "";
        String password = "3rfessf";
        AuthenticationData auth = userDAO.authentication(login, password);
        assertNull(auth);
    }

    @Test
    public void notRegUserEmptyPassword() throws DAOException {
        String login = "root23";
        String password = "";
        AuthenticationData auth = userDAO.authentication(login, password);
        assertNull(auth);
    }

    @Test
    public void notRegUserWrongPass() throws DAOException {
        String login = "ingv";
        String password = "3rfessf";
        AuthenticationData auth = userDAO.authentication(login, password);
        assertNull(auth);
    }
}