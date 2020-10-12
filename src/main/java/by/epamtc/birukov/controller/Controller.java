package by.epamtc.birukov.controller;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.controller.command.CommandProvider;
import by.epamtc.birukov.entity.UserRegForm;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        process(request, response);
        System.out.println("doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        process(request, response);
        System.out.println("doGet");
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        CommandProvider provider = new CommandProvider();

        String currentCommandName;

        currentCommandName = request.getParameter("command");

        Command command;

        command = provider.getCommand(currentCommandName);
        command.execute(request, response);


    }


}
