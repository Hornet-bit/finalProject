package by.epamtc.birukov.controller.command.impl.go_to;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToHalloPageCommand implements Command {
    private static final String HALLO_PAGE = "/WEB-INF/jsp/hello.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(HALLO_PAGE);
        requestDispatcher.forward(request, response);
    }
}
