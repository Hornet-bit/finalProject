package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ChangeLanguageCommand implements Command {

    private static final String ATTRIBUTE_NAME_LOCALE = "local";
    private static final String PAGE_GREETING = "/WEB-INF/jsp/greeting.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_LOCALE, request.getParameter(ATTRIBUTE_NAME_LOCALE));

        request.getRequestDispatcher(PAGE_GREETING).forward(request, response);


    }
}
