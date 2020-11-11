package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.entity.BasicDescriptionTest;
import by.epamtc.birukov.entity.Question;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.service.TestService;

import java.util.ArrayList;
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
}
