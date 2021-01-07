package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectDetailsCommand implements Command {
    private static final String PAGE_DETAILS_SUBJECT = "WEB-INF/jsp/subject_details.jsp";
    private static final String PARAM_SUBJ_NAME = "subj_name";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        String subject = request.getParameter(PARAM_SUBJ_NAME);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();



        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_DETAILS_SUBJECT);
        requestDispatcher.forward(request, response);

    }
}
