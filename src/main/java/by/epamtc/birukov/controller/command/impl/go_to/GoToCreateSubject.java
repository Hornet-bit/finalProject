package by.epamtc.birukov.controller.command.impl.go_to;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToCreateSubject implements Command {
    private final static String CREATE_SUBJECT_PAGE = "WEB-INF/jsp/create_subject.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        request.getRequestDispatcher(CREATE_SUBJECT_PAGE).forward(request, response);

    }
}
