package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.impl.SQLUserDAO;
import by.epamtc.birukov.entity.User;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShowAllUsersCommandTest {

    SQLUserDAO userDAO = Mockito.spy(SQLUserDAO.class);

    @Test
    void execute() throws DAOException {
        List<User> users = userDAO.showAllUsers();
        assertEquals(users.size(), 3);
        assertEquals(users.get(0).getUsername(), "ingv");
        assertEquals(users.get(1).getUsername(), "test1");
        assertEquals(users.get(2).getUsername(), "test2");
    }
}