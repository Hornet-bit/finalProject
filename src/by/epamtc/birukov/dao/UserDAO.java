package by.epamtc.birukov.dao;

import by.epamtc.birukov.entity.User;

public interface UserDAO {

    User authentication(String login, String password);

    boolean registration(User user);


}
