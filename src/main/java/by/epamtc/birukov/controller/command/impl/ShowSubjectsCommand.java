package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Subject;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowSubjectsCommand implements Command {
    private final static String PAGE_LIST_OF_SUBJECTS = "WEB-INF/jsp/show_subjects.jsp";
    private final static String ATTRIBUTE_NAME_LIST = "subjects";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        List<Subject> subjects = new ArrayList<>();
        subjects = testService.showSubjects();

        request.setAttribute(ATTRIBUTE_NAME_LIST, subjects);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_LIST_OF_SUBJECTS);
        requestDispatcher.forward(request, response);

    }
}
