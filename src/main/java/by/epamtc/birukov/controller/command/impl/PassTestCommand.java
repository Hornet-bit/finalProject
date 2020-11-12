package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        System.out.println(request.getParameter("radioBTN"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        int idTest = Integer.parseInt(request.getParameter("radioBTN"));
        Test test;
        test = testService.showTestById(idTest);

        request.setAttribute("test", test);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pass_test.jsp");
        requestDispatcher.forward(request, response);
    }
}