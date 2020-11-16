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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowTestByNameCommand implements Command {

    private static final String PARAMETER_NAME_RADIO_BUTTON = "radioBTN";
    private static final String PARAMETER_NAME_TEST = "test";
    private static final String PAGE_SHOW_ONE_TEST = "/WEB-INF/jsp/show_one_test.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        int idTest = Integer.parseInt(request.getParameter(PARAMETER_NAME_RADIO_BUTTON));
        Test test;
        test = testService.showTestById(idTest);


        HttpSession httpSession = request.getSession();
        httpSession.setAttribute( PARAMETER_NAME_TEST , test);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_SHOW_ONE_TEST);
        dispatcher.forward(request, response);

    }
}
