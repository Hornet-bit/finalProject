package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/testing";
    private static final String URL_PARAMS = "?useSSL=false&serverTimezone=UTC&useUnicode=true";
    private static final String USER = "root";
    private static final String PASSWORD = "minimal16";


    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }


    private BlockingQueue<Connection> freeConnection;
    private Queue<Connection> givenAwayConnection;

    private static final int POOL_SIZE = 30;

    private ConnectionPool() {

        try {


            initPoolConnection();
        } catch (DAOException e){
            e.printStackTrace();
            //todo log
        }
    }

    public Connection getConnection() throws DAOException {

        Connection connection = null;

        try {

            connection = freeConnection.take();
            givenAwayConnection.offer(connection);

        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new DAOException(e);


        }
        return connection;
    }

    public void initPoolConnection() throws DAOException {

        try {

            Class.forName(DRIVER_NAME);

            freeConnection = new LinkedBlockingDeque<>(POOL_SIZE);
            givenAwayConnection = new ArrayDeque<>();

            for (int i = 0; i < POOL_SIZE; i++) {

                Connection connection = DriverManager.getConnection(URL + URL_PARAMS, USER, PASSWORD);
                freeConnection.add(connection);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        givenAwayConnection.remove(connection);
        freeConnection.offer(connection);
    }

    public void destroyPool() throws DAOException {
        for (int i = 0; i < POOL_SIZE; i++) {

            try {
                freeConnection.take().close();

            } catch (InterruptedException e) {
                //todo log4j
                e.printStackTrace();
                throw new DAOException(e);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(e);
            }
        }
    }


}
