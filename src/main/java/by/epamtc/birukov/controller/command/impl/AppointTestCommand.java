package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.RunTest;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppointTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        HttpSession httpSession = request.getSession();
        int idTest = (int)httpSession.getAttribute("idTest");
        httpSession.removeAttribute("idTest");

        RunTest runTest = new RunTest();
        runTest.setIdTest(idTest);

        //todo продумать парсер

        int countOfUsers = Integer.parseInt(request.getParameter("count"));

        for (int i = 1; i <= countOfUsers; i++) {

            String parameterName = "set_check" + i;

            if (request.getParameter(parameterName) != null) {

                int idUser = Integer.parseInt(request.getParameter(parameterName));

                runTest.setStudent(idUser);
//                runTest.setIdUser(idUser);
            }

        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();



    }
}
