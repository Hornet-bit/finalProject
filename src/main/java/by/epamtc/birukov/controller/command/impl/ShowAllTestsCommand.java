package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllTestsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        List<BasicDescriptionTest> listOfQuestion= new ArrayList<>();
        listOfQuestion = testService.showAllTests();

        request.setAttribute("all_tests", listOfQuestion);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/temp_show_tests.jsp");
        dispatcher.forward(request, response);
    }
}
