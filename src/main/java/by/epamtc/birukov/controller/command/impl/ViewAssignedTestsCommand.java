package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewAssignedTestsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        HttpSession httpSession = request.getSession();
        String login = (String)httpSession.getAttribute("login");

        List<BasicDescriptionTest> listOfQuestion = testService.showMyTests(login);

        request.setAttribute("list", listOfQuestion);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_assigned_tests.jsp");
        dispatcher.forward(request, response);
    }
}
