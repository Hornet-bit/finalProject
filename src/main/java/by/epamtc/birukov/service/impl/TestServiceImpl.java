package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.*;
import by.epamtc.birukov.service.TestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestServiceImpl implements TestService {

    @Override
    public void createTest(Test test) throws DAOException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        sqlTestDAO.createTest(test);

    }

    @Override
    public List showAllTests() {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<BasicDescriptionTest> listOfTests = new ArrayList<>();
        try {


            listOfTests = sqlTestDAO.showAllTestsName();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return listOfTests;
    }

    @Override
    public Test showTestById(int id) throws DAOException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        Test test = new Test();
        test = sqlTestDAO.showTestById(id);

        return test;
    }

    @Override
    public void appointTest(RunTest runTest) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        sqlTestDAO.appointTest(runTest);


    }

    @Override
    public List<BasicDescriptionTest> showMyTests(String login) {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<BasicDescriptionTest> bdt = new ArrayList<>();
        try {
            bdt = sqlTestDAO.showMyTests(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return bdt;
    }

    @Override
    public List<VerifiedAnswer> checkTest(Test test, String[] multipleSelectionAnswers, String[] singleSelectionAnswers) {

        List<String> listOfSingleUserAnswers = new ArrayList<>();

        if (singleSelectionAnswers != null) {
            Collections.addAll(listOfSingleUserAnswers, singleSelectionAnswers);
        }

        List<VerifiedAnswer> verifiedAnswers = new ArrayList<>();

        for (int i = 0; i < test.getCountOfQuestion(); i++) {
            int count = test.getQuestion(i).getCountOfAnswer();

            if (count != 1) {
                checkMultipleAnswer(test, multipleSelectionAnswers, i, verifiedAnswers);
            } else if (count == 1) {
                checkSingleAnswer(test, listOfSingleUserAnswers, i, verifiedAnswers);
            }
        }

        return verifiedAnswers;
    }

    @Override
    public double takeMarkForTest(List<VerifiedAnswer> verifiedAnswers) {
        double totalAnswers = verifiedAnswers.size();
        double rightAnswer = 0;

        for (VerifiedAnswer answer:
             verifiedAnswers) {
            if(answer.isRightAnswer()){
                rightAnswer++;
            }
        }

        double result = rightAnswer/totalAnswers;
        return result;
    }

    private void checkMultipleAnswer(Test test, String[] multipleSelectionAnswers, int numQuestion, List<VerifiedAnswer> listOfVerifiedAnswers) {

        VerifiedAnswer verifiedAnswer = new VerifiedAnswer();
        String textOfQuestion = test.getQuestion(numQuestion).getTextQuestion();

        verifiedAnswer.setTextQuestion(textOfQuestion);

        String realRightAnswer = "";
        for (int i = 0; i < test.getQuestion(numQuestion).getCountOfAnswer(); i++) {

            if (test.getQuestion(numQuestion).getAnswer(i).isRightAnswer()) {
                realRightAnswer += test.getQuestion(numQuestion).getAnswer(i).getTextAnswer();//хранит только правильные ответы
            }

        }

        String strOfUserChoice = "";
        for (int j = 0; j < multipleSelectionAnswers.length; j++) {

            String[] result = multipleSelectionAnswers[j].split("_");
            int questionIndex = Integer.parseInt(result[0]);

            if (questionIndex == numQuestion) {

                strOfUserChoice += result[1].trim();
            }
        }


        if (realRightAnswer.equalsIgnoreCase(strOfUserChoice)) {

            verifiedAnswer.setRightAnswer(true);
            System.out.println(numQuestion + "Верный выбор");
        } else {
            verifiedAnswer.setRightAnswer(false);
            System.out.println(numQuestion + "Неверно");
        }

        listOfVerifiedAnswers.add(verifiedAnswer);
        System.out.println();

    }

    private void checkSingleAnswer(Test test, List<String> listOfSingleUserAnswers, int numQuestion, List<VerifiedAnswer> listOfVerifiedAnswers) {

        String rightAnswer = test.getQuestion(numQuestion).getAnswer(0).getTextAnswer();

        VerifiedAnswer verifiedAnswer = new VerifiedAnswer();
        String textOfQuestion = test.getQuestion(numQuestion).getTextQuestion();

        verifiedAnswer.setTextQuestion(textOfQuestion);

        if (rightAnswer.equalsIgnoreCase(listOfSingleUserAnswers.get(0))) {

            listOfSingleUserAnswers.remove(0);
            verifiedAnswer.setRightAnswer(true);

            System.out.println(numQuestion + " ответ правильный");
        } else {
            verifiedAnswer.setRightAnswer(false);
            System.out.println(numQuestion+ " Ответ неверный");
        }

        listOfVerifiedAnswers.add(verifiedAnswer);
    }
}
