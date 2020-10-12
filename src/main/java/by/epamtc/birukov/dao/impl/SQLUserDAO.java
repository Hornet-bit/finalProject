package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.UserDAO;
import by.epamtc.birukov.entity.User;
import by.epamtc.birukov.entity.UserRegForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {

    private static final String AUTHENTHCATION = "SELECT * FROM users WHERE login = ? ";

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

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();//todo передать параметры
            }

        } catch (SQLException e){
            //todo log4j
            e.printStackTrace();
        }

        finally {
            //close connection
        }

        return user;
    }

    @Override
    public boolean registration(UserRegForm user) throws DAOException {
        return false;
    }


}
