package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.Test;

import java.sql.*;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_ID_TEST = "SELECT id_test FROM tests WHERE name = ?";
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
        int generatedIdQuestion = 0;

        try {

            while (question < test.getCountOfQuestion()) {
                PreparedStatement preparedStatement = null;
                Connection connection = null;
                connection = pool.getConnection();
                String sql = "INSERT INTO questions (id_test, question) values (?, ?)";


                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, test.getName());
                preparedStatement.setInt(1, idTest);
                preparedStatement.setString(2, test.getQuestion(question).getTextQuestion());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = null;
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Generated key in Questions = " + generatedKeys.getInt(1));
                    generatedIdQuestion = generatedKeys.getInt(1);
                }
                addAnswerToTable(generatedIdQuestion, question ,test);
                //вызов addAnswer()

                question++;
            }
        } catch (SQLException e) {
            e.printStackTrace();//todo log
        }
    }

    private void addAnswerToTable(int idQuestion, int question ,Test test) throws DAOException {

        int answer = 0;


        while (answer <= test.getQuestion(question).getCountOfAnswer()) {//возм тут ошибка
            Connection connection = null;
            connection = pool.getConnection();

            PreparedStatement preparedStatement = null;

            String ADD_ANSWER = "INSERT INTO answers (content, result, id_q) values (?,?,?)";

            try {

                preparedStatement = connection.prepareStatement(ADD_ANSWER);

                preparedStatement.setString(1, test.getQuestion(question).getAnswer(answer).getTextAnswer());
                preparedStatement.setBoolean(2, test.getQuestion(question).getAnswer(answer).isRightAnswer());
                preparedStatement.setInt(3, idQuestion);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                //todo log
            }
            answer++;
        }

    }

    @Override
    public void redactTest() {

    }

    @Override
    public void deleteTest() {

    }
}
