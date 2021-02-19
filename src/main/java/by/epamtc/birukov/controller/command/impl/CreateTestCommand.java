package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.ParserQuestion;
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
import java.io.IOException;

//@MultipartConfig
public class CreateTestCommand implements Command {

    private static final String PAGE_FILING_QUESTIONS = "/WEB-INF/jsp/includs/question.jsp";
    private static final String PAGE_TEST_IS_CREATED = "/WEB-INF/jsp/test_is_created.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Test test = new Test();

        ParserQuestion parserQuestion = new ParserQuestion();
        test = parserQuestion.getTest(request);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        String page = PAGE_TEST_IS_CREATED;
        try {
            testService.createTest(test);
        } catch (ServiceException e){

            page = ERROR_PAGE;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);

    }
}
