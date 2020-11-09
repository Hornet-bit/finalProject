package by.epamtc.birukov.service.impl;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.dao.DAOProvider;
import by.epamtc.birukov.dao.TestDAO;
import by.epamtc.birukov.dao.impl.SQLTestDAO;
import by.epamtc.birukov.entity.Test;
import by.epamtc.birukov.service.TestService;

public class CreateTestImpl implements TestService {

    @Override
    public void createTest(Test test) throws DAOException {

        DAOProvider daoProvider = DAOProvider.getInstance();
        TestDAO sqlTestDAO = daoProvider.getTestDAO();

        sqlTestDAO.createTest(test);

    }
}
