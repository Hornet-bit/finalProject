package by.epamtc.birukov.controller.command.impl.go_to;

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
    private static final String SETTINGS_PAGE = "/WEB-INF/jsp/settings.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        User user = null;

        String page= SETTINGS_PAGE;
        try {
            user = clientService.getSettings(request.getParameter(PARAMETER_SEARCH));
        } catch (ServiceException e){
            page = ERROR_PAGE;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
