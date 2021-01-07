package by.epamtc.birukov.controller.command.impl;

import by.epamtc.birukov.controller.command.Command;
import by.epamtc.birukov.dao.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
public class UploadImageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        InputStream fileContent = filePart.getInputStream();
        byte[] array = fileContent.readAllBytes();
//        Files.copy(fileContent, )

        File dir = new File("web/img/avatars");
        dir.mkdirs();
        File tmp = new File(dir, "tmp.txt");
        tmp.createNewFile();

        File targetFile = new File("c:\\pictures\\image.png");
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(array);
        System.out.println(filePart.getContentType());
//        System.out.println(filePart.getHeader());

    }
}
