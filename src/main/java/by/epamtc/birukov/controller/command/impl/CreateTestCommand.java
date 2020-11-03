package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
//@MultipartConfig
public class CreateTestCommand implements Command {

    private static final String FILING_QUESTIONS = "/WEB-INF/jsp/includs/question.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String count = request.getParameter("count_of_question");
//        request.setAttribute("count", count);
//        Part filePart = request.getPart("image");
//        String fn = filePart.getName();
//        System.out.println("Имя файла "+fn);
////        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//        InputStream fileContent = filePart.getInputStream();
//        System.out.println(fileContent.read());

        int countOfQuestion = Integer.parseInt(request.getParameter("count"));



//        System.out.println(request.getParameter("image"));

        System.out.println("Questions = "+request.getParameter("count"));



        for (int i = 0; i < countOfQuestion; i++){
            System.out.println(request.getParameter("question"+i));
            System.out.println(request.getParameter("q"+i+"a0"));
            System.out.println(request.getParameter("q"+i+"a1"));
            System.out.println(request.getParameter("q"+i+"a2"));
            System.out.println(request.getParameter("q"+i+"a3"));
            System.out.println(request.getParameter("q"+i+"a4"));
            System.out.println(request.getParameter("q"+i+"a5"));
            System.out.println("-----------");
        }



        RequestDispatcher dispatcher = request.getRequestDispatcher(FILING_QUESTIONS);
        dispatcher.forward(request, response);
    }
}
