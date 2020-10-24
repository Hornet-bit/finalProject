package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateQuestionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/create_test.jsp");
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
