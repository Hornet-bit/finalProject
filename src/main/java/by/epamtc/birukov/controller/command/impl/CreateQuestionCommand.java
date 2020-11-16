package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateQuestionCommand implements Command {

    private final static String PARAM_OF_RESPONSE = "text/html;charset=UTF-8";

    private final static String PAGE_CREATE_TEST = "/WEB-INF/jsp/create_test.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(PARAM_OF_RESPONSE);
        PrintWriter out = response.getWriter();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_CREATE_TEST);
        out = addInput(out);
//        request.setAttribute("que", "/WEB-INF/jsp/includs/question.jsp");
        requestDispatcher.include(request, response);
//        requestDispatcher.forward(request, response);

    }

    private PrintWriter addInput(PrintWriter out) {
        String smth = "sms to add";

        out.println(smth);

        return out;
    }
}
