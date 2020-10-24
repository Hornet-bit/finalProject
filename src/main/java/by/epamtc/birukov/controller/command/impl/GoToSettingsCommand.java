package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSettingsCommand implements Command {
    private static final String PARAMETER_SEARCH = "login";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        User user = null;
        try {
            user = clientService.getSettings(request.getParameter(PARAMETER_SEARCH));
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);

        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ServiceException e){
            e.printStackTrace();
            //log
            //todo возможно выброс еррор страницы

        }
    }
}
