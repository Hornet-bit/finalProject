package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.UserDAO;
import by.epamtc.birukov.entity.AuthenticationData;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {

    private static final String DEFAULT_ROLE = "8";
    //todo баг sql

    private static final String AUTHENTHCATION = "SELECT U.id, username, role FROM users U JOIN role R ON username = ? AND password= ? AND  U.role_id=R.id";
    private static final String AUTHORIZATION = "SELECT * FROM users U JOIN role R on username = ? AND  U.role_id=R.id";
    private static final String SHOW_ALL_USERS = "SELECT U.id, username, role FROM users U JOIN role R on U.role_id=R.id";
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private final static String INSERT_REGISTRATION_FORM = "INSERT INTO users(email, password, username, role_id) " +
            "values(?, ?, ?,? )";

    @Override
    public AuthenticationData authentication(String login, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AuthenticationData authenticationData = null;

        //todo почему не могу aud = null, не даёт зарегаться

        try {
            connection = pool.getConnection();

            preparedStatement = connection.prepareStatement(AUTHENTHCATION);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                authenticationData = new AuthenticationData();
                String username = resultSet.getString("username");
                authenticationData.setUsername(username);
                authenticationData.setId(resultSet.getInt("id"));
                int i = resultSet.getInt("id");
                authenticationData.setUserRole(resultSet.getString("role"));

            } else {
                //todo log
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
            //todo log4j

        } finally {
            pool.releaseConnection(connection);
        }

        return authenticationData;
    }

    @Override
    public boolean registration(UserRegForm user) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = pool.getConnection();

        try {

            connection.setAutoCommit(false);


            preparedStatement = connection.prepareStatement(INSERT_REGISTRATION_FORM);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, DEFAULT_ROLE);

            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public User getSettings(int id) {

        return null;
    }

//    @Override
//    public User authorization(String login) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        connection = pool.getConnection();
//
//
//        User user = null;
//        try {
//
//            preparedStatement = connection.prepareStatement(AUTHORIZATION);
//
//            preparedStatement.setString(1, login);
//
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//
//                user = new User();
//
//                user.setEmail(resultSet.getString("email"));
//                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//                user.setRole(resultSet.getString("role"));
//                System.out.println(user.getRole());
//
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DAOException(e);
//        }
//
//        return user;
//    }


    @Override
    public List<User> showAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = pool.getConnection();
        List<User> listOfUsers = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement(SHOW_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));

                listOfUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }
        return listOfUsers;
    }

    private final static String GET_ROLE_BY_USERNAME = "SELECT role_id FROM users WHERE username = ?";
    private final static String CHANGE_ROLE = "";
    private static final String ADMIN_ROLE = "7";

    @Override
    public void changeRole(String login) throws DAOException {

        int role = getRole(login);
        if (role == Integer.parseInt(ADMIN_ROLE)) {
            makeAnUser(login);
        } else if (role == Integer.parseInt(DEFAULT_ROLE)) {
            makeAnAdmin(login);
        }


    }

    private int getRole(String login) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int roleId = 0;
        try {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ROLE_BY_USERNAME);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                roleId = resultSet.getInt("role_id");
            }

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            pool.releaseConnection(connection);
        }
        return roleId;
    }



    private void makeAnAdmin(String login) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(MAKE_AN_USER);
            preparedStatement.setInt(1, Integer.parseInt(ADMIN_ROLE));
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();

        } catch (DAOException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private static final String MAKE_AN_USER = "UPDATE users SET role_id = ? WHERE username = ?";

    private void makeAnUser(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(MAKE_AN_USER);
            preparedStatement.setInt(1, Integer.parseInt(DEFAULT_ROLE));
//            preparedStatement.setString(1, ADMIN_ROLE);
            preparedStatement.setString(2, login);

            preparedStatement.executeUpdate();
        } catch (DAOException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            pool.releaseConnection(connection);
        }
    }


}
