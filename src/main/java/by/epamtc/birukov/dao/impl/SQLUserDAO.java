package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.UserDAO;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {

    private static final String DEFAULT_ROLE = "4";
    //todo баг sql

    private static final String AUTHENTHCATION = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String AUTHORIZATION = "SELECT * FROM users U JOIN role R on username = ? AND  U.role_id=R.id";

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User authentication(String login, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        connection = pool.getConnection();

        try {

            preparedStatement = connection.prepareStatement(AUTHENTHCATION);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();
//                user.setUsername(resultSet.getString("username"));
                user = authorization(login);

                //todo передать параметры
            }

        } catch (SQLException e) {
            //todo log4j
            e.printStackTrace();
        } finally {
            //todo close connectionPool
            //close connection
        }

        return user;
    }

    @Override
    public boolean registration(UserRegForm user) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = pool.getConnection();

        try {

            connection.setAutoCommit(false);
            String sql = "INSERT INTO users(email, password, username, role_id) " +
                    "values(?, ?, ?,? )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, DEFAULT_ROLE);

            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);


        } catch (SQLException e) {
            e.printStackTrace();
            //todo log, выкинуть ДАОExep
        }

        return true;
    }

    @Override
    public User authorization(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = pool.getConnection();


        User user = null;
        try {

            preparedStatement = connection.prepareStatement(AUTHORIZATION);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                user = new User();

                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                System.out.println(user.getRole());

            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }

        return user;
    }

    private static final String SHOW_ALL_USERS = "SELECT U.id, username, role FROM users U JOIN role R on U.role_id=R.id";

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
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }
        return listOfUsers;
    }


}
