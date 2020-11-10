package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_ID_TEST = "SELECT id_test FROM tests WHERE name = ?";

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
                preparedStatement.setInt(1, idTest);
                preparedStatement.setString(2, test.getQuestion(question).getTextQuestion());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = null;
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
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

    @Override
    public void showTestWithAnswer() throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT name, description FROM tests WHERE id = ?";



    }

    @Override
    public List<BasicDescriptionTest> showAllTestsName() throws DAOException {
//todo при показе выводить описание тестов
        Connection connection = null;
        connection = pool.getConnection();
        ResultSet resultSet = null;

        PreparedStatement preparedStatement = null;


        List<BasicDescriptionTest> listOfTests = new ArrayList<>();
        String SHOW_ALL_TESTS = "SELECT name, description, id_test FROM tests";
        try {

            preparedStatement = connection.prepareStatement(SHOW_ALL_TESTS);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                BasicDescriptionTest bDT = new BasicDescriptionTest();

                bDT.setId(resultSet.getInt("id_test"));
                bDT.setName(resultSet.getString("name"));
                bDT.setDescription(resultSet.getString("description"));

                listOfTests.add(bDT);
            }

        } catch (SQLException e){
            e.printStackTrace();
            //todo log

        }

        return listOfTests;
    }

    @Override
    public Test showTestById(int id) {

        Test test = new Test();
        String GET_TEST_BY_ID = "";


        return test;
    }
}
