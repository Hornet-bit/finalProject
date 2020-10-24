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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        UserRegForm user = new UserRegForm();

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        try {

            clientService.registration(user);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/greeting.jsp");
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
