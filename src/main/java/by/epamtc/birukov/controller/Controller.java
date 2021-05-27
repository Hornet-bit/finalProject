package by.epamtc.birukov.controller;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.controller.command.CommandProvider;
import by.epamtc.birukov.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {
    private static final String PARAMETER_NAME_COMMAND = "command";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CommandProvider provider = new CommandProvider();

        String currentCommandName;
        currentCommandName = request.getParameter(PARAMETER_NAME_COMMAND);

        Command command = provider.getCommand(currentCommandName);

        try {
            command.execute(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
            //todo log
        }

    }

}
