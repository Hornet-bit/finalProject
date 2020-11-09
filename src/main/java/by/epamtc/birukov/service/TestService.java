package by.epamtc.birukov.service;

import by.epamtc.birukov.dao.DAOException;
import by.epamtc.birukov.entity.Test;

public interface TestService {
    void createTest(Test test) throws DAOException;
}
