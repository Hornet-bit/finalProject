package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ChangeLanguageCommand implements Command {

    private static final String ATTRIBUTE_NAME_LOCALE = "local";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String PATH = "/WEB-INF/jsp/";

        HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME_LOCALE, request.getParameter(ATTRIBUTE_NAME_LOCALE));

        String page = (String) session.getAttribute("page");
        PATH+=page;

        request.getRequestDispatcher(PATH).forward(request, response);

//        response.sendRedirect("/WEB-INF/index.jsp");
//        response.sendRedirect(request.getContextPath()+ "/temp_show_tests.jsp");


    }
}
