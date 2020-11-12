package by.epamtc.birukov.controller.command.impl.go_to;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.RunTest;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToAppointTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();
        TestService testService = serviceProvider.getTestService();

        int idTest = Integer.parseInt(request.getParameter("radioBTN"));

        request.setAttribute("idTest", idTest);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("idTest", idTest);


        List<User> listOfUsers = new ArrayList<>();
        try {

            listOfUsers = clientService.showAllUsers();
        } catch (ServiceException e){
            e.printStackTrace();
            //todo log
        }
        request.setAttribute("user_list", listOfUsers);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list_of_users.jsp");
        requestDispatcher.forward(request, response);

//        List<BasicDescriptionTest> listOfQuestion= new ArrayList<>();
//        listOfQuestion = testService.showAllTests();
//        request.setAttribute("all_tests", listOfQuestion);
//
//        int countOfUsers = Integer.parseInt(request.getParameter("count"));



    }
}
