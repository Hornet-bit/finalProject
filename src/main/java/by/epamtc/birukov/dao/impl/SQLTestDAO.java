package by.epamtc.birukov.dao.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class SQLTestDAO implements TestDAO {

    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String GET_ID_TEST = "SELECT id_test FROM tests WHERE name = ?";
    private static final String GET_TESTPARAM_BY_ID = "SELECT id_test, name, description FROM tests AS t WHERE t.id_test = ?";
    private final static String GET_QUESTION_FROM_TEST = "SELECT id, question, content FROM questions AS q WHERE q.id_test = ?";
    private final static String GET_ANSWER_FROM_QUESTION = "SELECT id_ans, content, result FROM answers AS ans WHERE ans.id_q = ?";
    private final static String GET_ID_USER = "SELECT id FROM users WHERE username = ?";
    private final static String GET_TESTS_USER = "SELECT id_test FROM run_tests WHERE id_user = ?";
    private final static String GET_SHORT_INFO_TESTS_USER = "SELECT id_test, name, description FROM tests WHERE id_test = ?";



    @Override
    public void createTest(Test test) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedTestId = null;
        int idTest = 0;

        connection = pool.getConnection();


        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO tests (name, description) values (?, ?)";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setString(2, test.getDescription());

            preparedStatement.executeUpdate();

            generatedTestId = preparedStatement.getGeneratedKeys();
            if (generatedTestId.next()) {
                idTest = generatedTestId.getInt(1);
            }

            connection.setAutoCommit(true);
            addQuestionToTable(test, idTest);

            //addIdToRunTests(idTest);

            pool.releaseConnection(connection);


        } catch (SQLException e) {
            e.printStackTrace();
            //todo log & DAOExep
        }
    }

    private final static String SET_ID_TO_RUNTESTS = "INSERT INTO run_tests (test_id) values (?)";

    private void addIdToRunTests(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = pool.getConnection();

            preparedStatement = connection.prepareStatement(SET_ID_TO_RUNTESTS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //todo log
        } catch (DAOException e) {
            e.printStackTrace();
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


                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    private static final String INSERT_IDUSER_TO_RUN_TEST = "INSERT INTO run_tests (id_test, id_user) VALUES (?, ?)";


    @Override
    public void appointTest(RunTest runTest) throws DAOException {

        Connection connection = null;
        connection = pool.getConnection();

        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(INSERT_IDUSER_TO_RUN_TEST);
            for (int i = 0; i < runTest.getSize(); i++) {

                preparedStatement.setInt(1, runTest.getIdTest());
                preparedStatement.setInt(2, runTest.getIdUser(i));
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            //todo log
            pool.releaseConnection(connection);
        }
    }





    @Override
    public List<BasicDescriptionTest> showMyTests(String login) throws DAOException {


        List<BasicDescriptionTest> listOfTests = new ArrayList<>();

        int idUser = getIdUser(login);

        List<Integer> testsId = getIdTestsByUserId(idUser);//size 0

        for (int i = 0; i < testsId.size(); i++) {

            int idTest = testsId.get(i);

            BasicDescriptionTest bdt = getBasicTestInfoById(idTest);

            listOfTests.add(bdt);
        }

//todo вызов ещё 1 метода

        return listOfTests;
    }

    private BasicDescriptionTest getBasicTestInfoById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        BasicDescriptionTest bdt = new BasicDescriptionTest();
        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(GET_SHORT_INFO_TESTS_USER);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                bdt.setId(resultSet.getInt("id_test"));
                bdt.setName(resultSet.getString("name"));
                bdt.setDescription(resultSet.getString("description"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return bdt;
    }

    private List<Integer> getIdTestsByUserId(int idUser) {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> testsId = new ArrayList<>();
        try {

            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(GET_TESTS_USER);
            preparedStatement.setInt(1, idUser);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                testsId.add(resultSet.getInt("id_test"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            pool.releaseConnection(connection);
        }
        return testsId;

    }

    private int getIdUser(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int idUser = 0;

        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ID_USER);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                idUser = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return idUser;

    }

}
