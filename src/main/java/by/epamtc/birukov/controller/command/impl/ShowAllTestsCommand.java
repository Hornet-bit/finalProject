package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.service.ServiceProvider;
import by.epamtc.birukov.service.TestService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllTestsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        TestService testService = serviceProvider.getTestService();

        List<BasicDescriptionTest> listOfQuestion= new ArrayList<>();
        listOfQuestion = testService.showAllTests();

        request.setAttribute("all_tests", listOfQuestion);
        //////////////////
//        TempTestBasik tb1 = new TempTestBasik();
//        tb1.setId(1);
//        tb1.setDescription("Descr1");
//        tb1.setName("Name1");
//
//        TempTestBasik tb2 = new TempTestBasik();
//        tb2.setId(2);
//        tb2.setDescription("Descr2");
//        tb2.setName("Name2");
//
//        List<TempTestBasik> li = new ArrayList<>();
//        li.add(tb1);
//        li.add(tb2);
//
//        request.setAttribute("li", li);

        ////////////////////

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/temp_show_tests.jsp");
        dispatcher.forward(request, response);
    }
}
