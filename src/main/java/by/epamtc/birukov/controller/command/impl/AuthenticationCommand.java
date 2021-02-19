package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthenticationCommand implements Command {

//private static final Logger log = LogManager.getLogger();

    private static final String  PARAMETER_LOGIN = "username";
    private static final String  PARAMETER_PASSWORD = "password";

    private static final String LOGIN_PAGE = "/WEB-INF/jsp/logination.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    private static final String HELLO_PAGE = "/WEB-INF/jsp/hello.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();

        User user = null;
        String page = null;
        AuthenticationData authenticationData = null;
        try {

            authenticationData = clientService.authentication(login, password);

            if (authenticationData == null){
                request.setAttribute("error", "wrong login or password");
                page = LOGIN_PAGE;

            } else {


                page = HELLO_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("user", authenticationData);
                session.setAttribute("login", authenticationData.getUsername());
                session.setAttribute("role", authenticationData.getUserRole());

                session.setAttribute("pa", "img/avatars/ingv_avatar.jpeg");
            }
        } catch (ServiceException e){
            page = ERROR_PAGE;
            e.printStackTrace();

//            log.debug(e);



            //todo log4j
        } finally {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
//                log.debug(e);
//                log.debug(e);

        }



    }
}
