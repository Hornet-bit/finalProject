package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllUsersCommand implements Command {

    private static final String ATTRIBUTE_NAME_USER_LIST = "user_list";
    private static final String PAGE_LIST_OF_USERS = "/WEB-INF/jsp/list_of_users.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        List<User> listOfUsers = new ArrayList<>();
        String page = PAGE_LIST_OF_USERS;
        try {
            listOfUsers = clientService.showAllUsers();
        } catch (ServiceException e){
            page = ERROR_PAGE;
        }

        request.setAttribute(ATTRIBUTE_NAME_USER_LIST, listOfUsers);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }
}
