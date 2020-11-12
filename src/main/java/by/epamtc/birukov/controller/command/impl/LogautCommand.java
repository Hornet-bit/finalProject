package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogautCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        HttpSession httpSession = request.getSession();
        httpSession.invalidate();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logination.jsp");
        requestDispatcher.forward(request, response);
    }
}
