package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.RunTest;
import by.epamtc.birukov.entity.Subject;
import by.epamtc.birukov.entity.Test;

import java.util.List;

public interface TestDAO {

    void createTest(Test test) throws DAOException;

    void redactTest();
    //todo что-то будет принимать

    void deleteTest();

    void showTestWithAnswer() throws DAOException;

    List<BasicDescriptionTest> showAllTestsName() throws DAOException;

    Test showTestById(int id) throws DAOException;

    void appointTest(RunTest runTest) throws DAOException;

    List<BasicDescriptionTest> showMyTests(String login) throws DAOException;

    void CreateSubject(Subject subject) throws DAOException;

    List<Subject> showSubjects() throws DAOException;

    List<BasicDescriptionTest> getTestsOfSubject(String subjectName) throws DAOException;

    void deleteAppointTest(int id_test, int id_user) throws DAOException;

    void putMarkInJournal(int idUser, int idTest, int mark) throws DAOException;

}
