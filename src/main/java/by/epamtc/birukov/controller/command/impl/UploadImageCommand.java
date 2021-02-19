package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.service.ClientService;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
public class UploadImageCommand implements Command {
    private static final String HELLO_PAGE = "controller?command=go_to_hallo_page";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

        HttpSession session = request.getSession();
        AuthenticationData user = (AuthenticationData) session.getAttribute("user");


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();





        String locationImage = null;

//todo поправить название параметров
        try {
            locationImage = clientService.uploadAvatar(user, filePart);
            session.setAttribute("pa", "");

            session.setAttribute("pa", "img/avatars/ingv_avatar.jpeg");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        session.setAttribute("avatar", locationImage);

        response.sendRedirect(HELLO_PAGE);


    }
}
