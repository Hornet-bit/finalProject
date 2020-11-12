package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthenticationCommand implements Command {

//private static final Logger logger = LogManager.getLogger(AuthenticationCommand.class);

    private static final String  PARAMETER_LOGIN = "username";
    private static final String  PARAMETER_PASSWORD = "password";

    private static final String LOGIN_PAGE = "/WEB-INF/jsp/logination.jsp";
    private static final String ERROR_PAGE = "/error.jsp";
    private static final String HELLO_PAGE = "/WEB-INF/jsp/hello.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        String login;
        String password;

        login = request.getParameter(PARAMETER_LOGIN);
        password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();


///////////////работа с сессией

        User user = null;
        String page = null;
        AuthenticationData authenticationData;
        try {

            authenticationData = clientService.authentication(login, password);

            if (authenticationData == null){
                request.setAttribute("error", "wrong login or password");
                page = LOGIN_PAGE;


            } else {


//                authenticationData.setUsername();
//                request.setAttribute("user", user);
                page = HELLO_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("user", authenticationData);
                session.setAttribute("login", authenticationData.getUsername());
                session.setAttribute("role", authenticationData.getUserRole());
            }
        } catch (ServiceException e){
            page = ERROR_PAGE;
            e.printStackTrace();
//            logger.debug(e);

            throw new DAOException(e);

            //todo log4j
        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        }



    }
}
