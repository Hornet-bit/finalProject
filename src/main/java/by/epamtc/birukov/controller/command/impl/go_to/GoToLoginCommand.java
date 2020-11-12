package by.epamtc.birukov.controller.command.impl.go_to;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToLoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.getRequestDispatcher("/WEB-INF/jsp/logination.jsp").forward(request, response);
        }
        catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
