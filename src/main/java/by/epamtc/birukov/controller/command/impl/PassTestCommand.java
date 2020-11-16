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

public class PassTestCommand implements Command {
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static final String ATTRIBUTE_NAME_RADIOBUTTON = "radioBTN";
    private static final String ATTRIBUTE_NAME_TEST = "test";
    private static final String PAGE_PASS_TEST = "/WEB-INF/jsp/pass_test.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        request.setCharacterEncoding(CHARACTER_ENCODING);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        int idTest = Integer.parseInt(request.getParameter(ATTRIBUTE_NAME_RADIOBUTTON));
        Test test;
        test = testService.showTestById(idTest);

//        request.setAttribute("test", test);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(ATTRIBUTE_NAME_TEST, test);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_PASS_TEST);
        requestDispatcher.forward(request, response);
    }
}
