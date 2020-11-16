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

    private static final String PARAMETER_NAME_LIST_OF_TEST = "all_tests";
    private static final String PAGE_SHOW_TESTS = "/WEB-INF/jsp/temp_show_tests.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        List<BasicDescriptionTest> listOfQuestion= new ArrayList<>();
        listOfQuestion = testService.showAllTests();

        request.setAttribute(PARAMETER_NAME_LIST_OF_TEST, listOfQuestion);
        //////////////////


        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_SHOW_TESTS);
        dispatcher.forward(request, response);
    }
}
