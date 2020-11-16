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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        UserRegForm user = new UserRegForm();

        user.setUsername(request.getParameter(ATTRIBUTE_NAME_USERNAME));
        user.setEmail(request.getParameter(ATTRIBUTE_NAME_EMAIL));
        user.setPassword(request.getParameter(ATTRIBUTE_NAME_PASSWORD));


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        try {

            clientService.registration(user);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_GREETING);
            requestDispatcher.forward(request, response);

        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ServiceException e){
            e.printStackTrace();
            //todo log4j
        }
    }
}
