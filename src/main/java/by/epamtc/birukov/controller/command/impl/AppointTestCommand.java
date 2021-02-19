package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.RunTest;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppointTestCommand implements Command {

    private static final String ID_TEST_ATTRIBUTE = "idTest";
    private static final String COUNT_OF_USERS_ATTRIBUTE = "count";
    private static final String CHECK_NAME_ATTRIBUTE = "set_check";

    private static final String PAGE_TEST_WAS_APPOINT = "/WEB-INF/jsp/test_was_appoint.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        HttpSession httpSession = request.getSession();
        int idTest = (int)httpSession.getAttribute(ID_TEST_ATTRIBUTE);
        httpSession.removeAttribute(ID_TEST_ATTRIBUTE);

        RunTest runTest = new RunTest();
        runTest.setIdTest(idTest);

        int countOfUsers = Integer.parseInt(request.getParameter(COUNT_OF_USERS_ATTRIBUTE));

        for (int i = 1; i <= countOfUsers; i++) {

            String parameterName = CHECK_NAME_ATTRIBUTE + i;

            if (request.getParameter(parameterName) != null) {

                int idUser = Integer.parseInt(request.getParameter(parameterName));
                runTest.setStudent(idUser);
            }

        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        String page = PAGE_TEST_WAS_APPOINT;
        try {
            testService.appointTest(runTest);
        } catch (ServiceException e){
            page = ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);

    }
}
