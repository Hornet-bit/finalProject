package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.entity.UserRegForm;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewUserCommand implements Command {

    private static final String ATTRIBUTE_NAME_USERNAME = "username";
    private static final String ATTRIBUTE_NAME_EMAIL = "email";
    private static final String ATTRIBUTE_NAME_PASSWORD = "password";

    private static final String PAGE_GREETING = "/WEB-INF/jsp/greeting.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    private static final String REG_PAGE = "/WEB-INF/jsp/registration.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        UserRegForm user = new UserRegForm();

        user.setUsername(request.getParameter(ATTRIBUTE_NAME_USERNAME));
        user.setEmail(request.getParameter(ATTRIBUTE_NAME_EMAIL));
        user.setPassword(request.getParameter(ATTRIBUTE_NAME_PASSWORD));


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        String page = PAGE_GREETING;
        try {
            if(!clientService.checkAvailableUsernameAndEmail(user)){
                   page = REG_PAGE;
                   request.setAttribute("error", "this username not unique");
            }
            else {
                clientService.registration(user);
            }
        } catch (ServiceException e) {
            page = ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);

    }
}
