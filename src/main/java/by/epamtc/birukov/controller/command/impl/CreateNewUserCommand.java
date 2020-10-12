package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.entity.UserRegForm;

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

        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

//todo отправка к дао и взаимодействие с бд

//        request.setAttribute("user", user);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");

        try {


            requestDispatcher.forward(request, response);
        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
