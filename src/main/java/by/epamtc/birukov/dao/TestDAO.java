package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.Test;

import java.util.List;

public interface TestDAO {

    void createTest(Test test) throws DAOException;

    void redactTest();
    //todo что-то будет принимать

    void deleteTest();

    void showTestWithAnswer() throws DAOException;

    List<BasicDescriptionTest> showAllTestsName() throws DAOException;

    Test showTestById(int id);

}
