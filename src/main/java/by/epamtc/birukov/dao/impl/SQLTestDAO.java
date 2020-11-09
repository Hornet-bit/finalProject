package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_ID_TEST = "SELECT id FROM questions WHERE name = ?";
    private static final String GET_ID_QUESTION = "SELECT id_test FROM tests WHERE name = ?";
    @Override
    public void createTest(Test test) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idTest = 0;

        connection = pool.getConnection();

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO tests (name, description) values (?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setString(2, test.getDescription());

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_ID_TEST);
            preparedStatement.setString(1, test.getName());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                idTest = resultSet.getInt("id_test");
                System.out.println(idTest);
            }
            connection.setAutoCommit(true);
            addQuestionToTable(test, idTest);


        } catch (SQLException e) {
            e.printStackTrace();
            //todo log & DAOExep
        }
    }

    private void addQuestionToTable(Test test, int idTest) throws DAOException {

        int question = 0;

        try {

            while (question < test.getCountOfQuestion()) {
                PreparedStatement preparedStatement = null;
                Connection connection = null;
                connection = pool.getConnection();
                String sql = "INSERT INTO questions (id_test, question) values (?, ?)";


                preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, test.getName());
                preparedStatement.setInt(1, idTest);
                preparedStatement.setString(2, test.getQuestion(question).getTextQuestion());
                preparedStatement.executeUpdate();



                //вызов addAnswer()

                question++;
            }
        } catch (SQLException e) {
            e.printStackTrace();//todo log
        }
    }

    private void addAnswerToTable() throws DAOException {
        Connection connection = null;
        ResultSet resultSet = null;
        connection = pool.getConnection();

    }

    @Override
    public void redactTest() {

    }

    @Override
    public void deleteTest() {

    }
}
