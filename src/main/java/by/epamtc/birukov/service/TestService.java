package by.epamtc.birukov.service;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.Question;
import by.epamtc.birukov.entity.Test;

import java.util.List;

public interface TestService {

    void createTest(Test test) throws DAOException;

    List<BasicDescriptionTest> showAllTests();

    Test showTestById(int id) throws DAOException;
}
