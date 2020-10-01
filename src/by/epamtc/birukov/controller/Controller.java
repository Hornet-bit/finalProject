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

        try {


            InitialContext initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/pool");


            PreparedStatement st = null;
            ResultSet rs = null;

            Connection conn = ds.getConnection();
            st = conn.prepareStatement("");
            rs = st.executeQuery();

            while (rs.next()){
                String tmp = rs.getString(2);
                System.out.println(tmp);
            }
        } catch (NamingException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        CommandProvider provider = new CommandProvider();

        String currentCommandName;

        currentCommandName = request.getParameter("command");
        System.out.println(currentCommandName);
        Command command;
//
        command = provider.getCommand(currentCommandName);
        command.execute(request, response);
        


        response.setContentType("text/html");

        UserRegForm user = new UserRegForm();
        user.setAge(20);
        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

//        Connection con = null;
//        PreparedStatement ps = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/epamuser?useSSL=false&serverTimezone=UTC&useUnicode=true", "root", "minimal16");
//            String sql = "INSERT INTO users(login, password) VALUES(?,?)";
//            ps = con.prepareStatement(sql);
//
//            ps.setString(1,user.getName());
//            ps.setString(2,user.getPassword());
//
//            ps.executeUpdate();
//            ResultSet rs = ps.executeQuery("SELECT * FROM users");



//            while (rs.next()){
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//            }

//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }





        request.setAttribute("user", user);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");

        requestDispatcher.forward(request, response);


    }
}
