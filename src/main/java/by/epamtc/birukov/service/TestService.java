package by.epamtc.birukov.service;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.*;

import java.util.List;

public interface TestService {

    void createTest(Test test) throws DAOException;

    List<BasicDescriptionTest> showAllTests();

    Test showTestById(int id) throws DAOException;

    void appointTest(RunTest runTest) throws DAOException;

    List<BasicDescriptionTest> showMyTests(String login);

    List<VerifiedAnswer> checkTest(Test test, String[] multipleSelectionAnswers, String[] singleSelectionAnswers);

    double takeMarkForTest(List<VerifiedAnswer> verifiedAnswers);

    void createSubject(Subject subject);

    List<Subject> showSubjects();

    List<BasicDescriptionTest> getTestsOfSubject(String subjectName);

    void deleteAppointTest(int id_test, int id_user);

    void  putMarkInJournal(int idUser, int idTest, int mark);

}
