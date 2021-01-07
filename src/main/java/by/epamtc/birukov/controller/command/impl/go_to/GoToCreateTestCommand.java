package by.epamtc.birukov.controller.command.impl.go_to;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.entity.Subject;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToCreateTestCommand implements Command {
    private static final String PAGE_CREATE_TEST ="/WEB-INF/jsp/create_test.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();
        List<Subject> subjectList = testService.showSubjects();
        request.setAttribute("subjects", subjectList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_CREATE_TEST);
        requestDispatcher.forward(request, response);
    }
}
