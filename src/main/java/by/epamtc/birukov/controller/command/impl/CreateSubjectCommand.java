package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Subject;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateSubjectCommand implements Command {
    private final static String PARAM_SUBJ_NAME = "name";
    private final static String PARAM_DESCRIPTION = "description";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    //todo нет форварда
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Subject subject = new Subject();
        subject.setName(request.getParameter(PARAM_SUBJ_NAME));
        subject.setDescription(request.getParameter(PARAM_DESCRIPTION));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        String page = null;
        try {
            testService.createSubject(subject);
        } catch (ServiceException e){
            page = ERROR_PAGE;
        }



    }
}
