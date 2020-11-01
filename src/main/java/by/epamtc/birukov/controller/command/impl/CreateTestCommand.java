package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTestCommand implements Command {

    private static final String FILING_QUESTIONS = "/WEB-INF/jsp/includs/question.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String count = request.getParameter("count_of_question");
//        request.setAttribute("count", count);
        System.out.println(request.getParameter("question1"));
        System.out.println(request.getParameter("answer1"));
        System.out.println(request.getParameter("answer2"));
        System.out.println(request.getParameter("answer3"));


        System.out.println("Questions = "+request.getParameter("count"));





        RequestDispatcher dispatcher = request.getRequestDispatcher(FILING_QUESTIONS);
        dispatcher.forward(request, response);
    }
}
