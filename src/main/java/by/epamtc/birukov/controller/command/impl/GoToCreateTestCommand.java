package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToCreateTestCommand implements Command {
    private static final String PAGE_CREATE_TEST ="/WEB-INF/jsp/create_test.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_CREATE_TEST);
        requestDispatcher.forward(request, response);
    }
}
