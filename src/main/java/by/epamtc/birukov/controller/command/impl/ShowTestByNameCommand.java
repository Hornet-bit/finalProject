package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.service.ServiceException;
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
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idTest = Integer.parseInt(request.getParameter(PARAMETER_NAME_RADIO_BUTTON));
        Test test = null;
        String page = PAGE_SHOW_ONE_TEST;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        try {
            test = testService.showTestById(idTest);
        } catch (ServiceException e) {
            page = ERROR_PAGE;
        }

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(PARAMETER_NAME_TEST, test);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);

    }
}
