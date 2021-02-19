package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeRoleCommand implements Command {
    private static final String PARAM_LOGIN = "username";
    private static final String PAGE_LIST_OF_USERS = "/WEB-INF/jsp/list_of_users.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        String changedUserLogin = request.getParameter(PARAM_LOGIN);

        response.sendRedirect("controller?command=show_all_users");


//        request.getRequestDispatcher(PAGE_LIST_OF_USERS).forward(request, response);
        try {
            clientService.changeRole(changedUserLogin);
        } catch (ServiceException e){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
