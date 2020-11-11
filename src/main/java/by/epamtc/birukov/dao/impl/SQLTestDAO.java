package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_ID_TEST = "SELECT id_test FROM tests WHERE name = ?";
    private static final String GET_TESTPARAM_BY_ID = "SELECT id_test, name, description FROM tests AS t WHERE t.id_test = ?";
    private final static String GET_QUESTION_FROM_TEST = "SELECT id, question, content FROM questions AS q WHERE q.id_test = ?";
    private final static String GET_ANSWER_FROM_QUESTION = "SELECT id_ans, content, result FROM answers AS ans WHERE ans.id_q = ?";

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
//todo сделать внутренний селект

            preparedStatement = connection.prepareStatement(GET_ID_TEST);
            preparedStatement.setString(1, test.getName());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idTest = resultSet.getInt("id_test");
                System.out.println(idTest);
            }
            connection.setAutoCommit(true);
            addQuestionToTable(test, idTest);


            pool.releaseConnection(connection);


        } catch (SQLException e) {
            e.printStackTrace();
            //todo log & DAOExep
        }
    }

    private void addQuestionToTable(Test test, int idTest) throws DAOException {

        int question = 0;
        int generatedIdQuestion = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        connection = pool.getConnection();

        try {

            while (question < test.getCountOfQuestion()) {

                String sql = "INSERT INTO questions (id_test, question) values (?, ?)";


                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//todo сделать так же для теста
                preparedStatement.setInt(1, idTest);
                preparedStatement.setString(2, test.getQuestion(question).getTextQuestion());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = null;
                generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    generatedIdQuestion = generatedKeys.getInt(1);
                }
                addAnswerToTable(generatedIdQuestion, question, test);


                question++;
            }
        } catch (SQLException e) {
            e.printStackTrace();//todo log
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private void addAnswerToTable(int idQuestion, int question, Test test) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        int answer = 0;


        while (answer <= test.getQuestion(question).getCountOfAnswer()) {


            String ADD_ANSWER = "INSERT INTO answers (content, result, id_q) values (?,?,?)";//todo вынести в константы

            try {

                preparedStatement = connection.prepareStatement(ADD_ANSWER);

                preparedStatement.setString(1, test.getQuestion(question).getAnswer(answer).getTextAnswer());
                preparedStatement.setBoolean(2, test.getQuestion(question).getAnswer(answer).isRightAnswer());
                preparedStatement.setInt(3, idQuestion);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                //todo log
            } finally {
                pool.releaseConnection(connection);
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

            while (resultSet.next()) {

                BasicDescriptionTest bDT = new BasicDescriptionTest();

                bDT.setId(resultSet.getInt("id_test"));
                bDT.setName(resultSet.getString("name"));
                bDT.setDescription(resultSet.getString("description"));

                listOfTests.add(bDT);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }

        return listOfTests;
    }

    @Override
    public Test showTestById(int id) throws DAOException {

        Test test;
        test = takeTestInfoFromDB(id);


        return test;
    }


    private Test takeTestInfoFromDB(int id) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        Test test = new Test();

        try {

            preparedStatement = connection.prepareStatement(GET_TESTPARAM_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                test.setName(resultSet.getString("name"));
                test.setDescription(resultSet.getString("description"));
                test.setIdTest(resultSet.getInt("id_test"));
            }

            takeQuestionFromDB(id, test);

        } catch (SQLException e) {
            e.printStackTrace();
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }
        //todo func

        return test;
    }


    private void takeQuestionFromDB(int idTest, Test test) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;


        try {
            preparedStatement = connection.prepareStatement(GET_QUESTION_FROM_TEST);
            preparedStatement.setInt(1, idTest);
            resultSet = preparedStatement.executeQuery();
            int countOfQuestion = 0;
            while (resultSet.next()) {


                Question question = new Question();

                question.setTextQuestion(resultSet.getString("question"));
                question.setContent(resultSet.getString("content"));

                int idQuestion = resultSet.getInt("id");
                question.setId(idQuestion);

                takeAnswerFromBD(idQuestion, question);
                test.setQuestion(question);

                countOfQuestion++;
                test.setCountOfQuestion(countOfQuestion);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }

    }

    private Answer takeAnswerFromBD(int idQuestion, Question question) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Answer answer = null;
        try {

            preparedStatement = connection.prepareStatement(GET_ANSWER_FROM_QUESTION);
            preparedStatement.setInt(1, idQuestion);
            resultSet = preparedStatement.executeQuery();
            int countOfAnswer = 0;
            while (resultSet.next()) {
                countOfAnswer++;
                answer = new Answer();
                answer.setTextAnswer(resultSet.getString("content"));
                answer.setRightAnswer(resultSet.getBoolean("result"));
                answer.setId(resultSet.getInt("id_ans"));
                question.setAnswer(answer);
                question.setCountOfAnswer(countOfAnswer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //todo log
        } finally {
            pool.releaseConnection(connection);
        }

        return answer;
    }
private static final String INSERT_ID_TO_RUN_TEST = "INSERT INTO run_tests (?) VALUES (54)";
//private static final String INSERT_ID_TO_RUN_TEST = "INSERT INTO run_tests (?) VALUES (54)";
    @Override
    public void appointTest(RunTest runTest) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();

    }

}
