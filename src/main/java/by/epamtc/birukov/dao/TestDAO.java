package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.Test;

public interface TestDAO {

    void createTest(Test test) throws DAOException;

    void redactTest();
    //todo что-то будет принимать

    void deleteTest();


}
