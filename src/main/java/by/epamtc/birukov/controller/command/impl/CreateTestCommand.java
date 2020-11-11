package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.ParserQuestion;
import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        Test test = new Test();

        ParserQuestion parserQuestion = new ParserQuestion();
        test = parserQuestion.getTest(request);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        try {


            testService.createTest(test);
        } catch (DAOException e){
            e.printStackTrace();
            //todo log
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/test_is_created.jsp");
        requestDispatcher.forward(request, response);



        RequestDispatcher dispatcher = request.getRequestDispatcher(FILING_QUESTIONS);
        dispatcher.forward(request, response);
    }
}
