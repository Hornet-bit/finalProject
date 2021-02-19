package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.*;
import by.epamtc.birukov.service.ServiceException;
import by.epamtc.birukov.service.TestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestServiceImpl implements TestService {

    @Override
    public void createTest(Test test) throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        try {
            sqlTestDAO.createTest(test);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List showAllTests() throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<BasicDescriptionTest> listOfTests = new ArrayList<>();
        try {


            listOfTests = sqlTestDAO.showAllTestsName();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return listOfTests;
    }

    @Override
    public Test showTestById(int id) throws ServiceException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        Test test = new Test();
        try {
            test = sqlTestDAO.showTestById(id);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return test;
    }

    @Override
    public void appointTest(RunTest runTest) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        try {
            sqlTestDAO.appointTest(runTest);
        } catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    @Override
    public List<BasicDescriptionTest> showMyTests(String login) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<BasicDescriptionTest> bdt = new ArrayList<>();
        try {
            bdt = sqlTestDAO.showMyTests(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return bdt;
    }

    @Override
    public List<VerifiedAnswer> checkTest(Test test, String[] multipleSelectionAnswers, String[] singleSelectionAnswers) throws ServiceException{

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
    public double takeMarkForTest(List<VerifiedAnswer> verifiedAnswers) throws ServiceException{
        double totalAnswers = verifiedAnswers.size();
        double rightAnswer = 0;

        for (VerifiedAnswer answer:
             verifiedAnswers) {
            if(answer.isRightAnswer()){
                rightAnswer++;
            }
        }

        double result = rightAnswer/totalAnswers * 100;
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


    @Override
    public void createSubject(Subject subject) throws ServiceException{

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        try {
            sqlTestDAO.CreateSubject(subject);
        } catch (DAOException e){
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Subject> showSubjects() throws ServiceException{
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<Subject> subjects = new ArrayList<>();
        try {
            subjects = sqlTestDAO.showSubjects();
        } catch (DAOException e){
            throw new ServiceException(e);
        }
        return subjects;
    }

    @Override
    public List<BasicDescriptionTest> getTestsOfSubject(String subjectName) throws ServiceException{
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        List<BasicDescriptionTest> bdt = new ArrayList<>();
        try {
            bdt = sqlTestDAO.getTestsOfSubject(subjectName);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bdt;
    }

    @Override
    public void deleteAppointTest(int id_test, int id_user) throws ServiceException{
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        try {
            sqlTestDAO.deleteAppointTest(id_test, id_user);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void putMarkInJournal(int idUser, int idTest, int mark) throws ServiceException{
        //todo валидация
        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        try {
            sqlTestDAO.putMarkInJournal(idUser, idTest, mark);
        } catch (DAOException e){
            throw new ServiceException(e);
        }
    }


}
