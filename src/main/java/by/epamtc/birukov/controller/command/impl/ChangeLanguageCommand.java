package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle bundleDefault = ResourceBundle.getBundle("resources/localization");
        ResourceBundle bundleRu = ResourceBundle.getBundle("resources/localization", new Locale("ru", "RU"));
        ResourceBundle bundleEn = ResourceBundle.getBundle("resources/localization", new Locale("en", "UC"));


    }
}
