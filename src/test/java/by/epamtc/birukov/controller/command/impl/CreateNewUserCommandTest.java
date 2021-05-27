package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.impl.SQLUserDAO;
import by.epamtc.birukov.entity.UserRegForm;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CreateNewUserCommandTest {

    ClientService service = Mockito.spy(ClientService.class);
    SQLUserDAO dao = Mockito.spy(SQLUserDAO.class);
    @Test
    void execute() throws DAOException {
        UserRegForm user = new UserRegForm();
        user.setUsername("user");
        user.setEmail("email@gmail.tu");
        user.setPassword("112235r");

        boolean daoResult = dao.registration(user);

        assertTrue(daoResult);
    }
}