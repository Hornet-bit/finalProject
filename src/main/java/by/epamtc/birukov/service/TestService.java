package by.epamtc.birukov.service;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.*;

import java.util.List;

public interface TestService {

    void createTest(Test test) throws ServiceException;

    List<BasicDescriptionTest> showAllTests() throws ServiceException;

    Test showTestById(int id) throws ServiceException;

    void appointTest(RunTest runTest) throws ServiceException;

    List<BasicDescriptionTest> showMyTests(String login) throws ServiceException;

    List<VerifiedAnswer> checkTest(Test test, String[] multipleSelectionAnswers, String[] singleSelectionAnswers) throws ServiceException;

    double takeMarkForTest(List<VerifiedAnswer> verifiedAnswers) throws ServiceException;

    void createSubject(Subject subject) throws ServiceException;

    List<Subject> showSubjects() throws ServiceException;

    List<BasicDescriptionTest> getTestsOfSubject(String subjectName) throws ServiceException;

    void deleteAppointTest(int id_test, int id_user) throws ServiceException;

    void putMarkInJournal(int idUser, int idTest, int mark) throws ServiceException;

}
