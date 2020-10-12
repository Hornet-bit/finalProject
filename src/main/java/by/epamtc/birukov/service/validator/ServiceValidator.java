package by.epamtc.birukov.service.validator;

public class ServiceValidator {

    private static final int LENGTH_PASSWORD = 3;
    private static final int LENGTH_LOGIN = 3;

    public static boolean isDataCorrect(String login, String password) {
        return isLoginCorrect(login) && isPasswordCorrect(password);
    }

    private static boolean isLoginCorrect(String login) {
        if (login.length() < LENGTH_LOGIN) {
            return false;
        }
        return true;
    }

    private static boolean isPasswordCorrect(String password) {
        if (password.length() < LENGTH_PASSWORD) {
            return false;
        }
        return true;

    }

}
