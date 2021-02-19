package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.entity.VerifiedAnswer;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SendAnswersCommand implements Command {

    private static final String ENCODING_PARAM = "UTF-8";
    private static final String ATTRIBUTE_TEST_NAME = "test";
    private static final String ATTRIBUTE_NAME_CHECK = "check";
    private static final String ATTRIBUTE_NAME_ANSWER = "answer";
    private static final String ATTRIBUTE_NAME_LIST_OF_VERIFIED_ANSWERS = "answers";
    private static final String PAGE_RESULT_TEST = "/WEB-INF/jsp/result_test.jsp";
    private static final String ATTRIBUTE_NAME_MARK = "mark";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING_PARAM);
        HttpSession httpSession = request.getSession();

        Test test = (Test) httpSession.getAttribute(ATTRIBUTE_TEST_NAME);
        httpSession.removeAttribute(ATTRIBUTE_TEST_NAME);


        String[] multipleSelectionAnswers = request.getParameterValues(ATTRIBUTE_NAME_CHECK);
        String[] singleSelectionAnswers = request.getParameterValues(ATTRIBUTE_NAME_ANSWER);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        List<VerifiedAnswer> verifiedAnswers = null;
        String page = PAGE_RESULT_TEST;

        try {
            verifiedAnswers = testService.checkTest(test, multipleSelectionAnswers, singleSelectionAnswers);
            double mark = testService.takeMarkForTest(verifiedAnswers);
            test.setResultTest((int) mark);

            request.setAttribute(ATTRIBUTE_NAME_MARK, mark);
            request.setAttribute(ATTRIBUTE_NAME_LIST_OF_VERIFIED_ANSWERS, verifiedAnswers);

            AuthenticationData auth = (AuthenticationData) httpSession.getAttribute("user");
            testService.putMarkInJournal(auth.getId(), test.getIdTest(), test.getResultTest());
            testService.deleteAppointTest(test.getIdTest(), auth.getId());
        } catch (ServiceException e){
            page = ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);


    }
}
