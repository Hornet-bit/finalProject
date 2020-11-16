package by.epamtc.birukov.controller.command;

import by.epamtc.birukov.controller.command.impl.*;
import by.epamtc.birukov.controller.command.impl.go_to.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(ParameterName.CREATE_NEW_USER, new CreateNewUserCommand());
        commands.put(ParameterName.AUTHORIZATION, new AuthenticationCommand());
        commands.put(ParameterName.GREETING, new GreetingPageCommand());
        commands.put(ParameterName.GO_TO_LOGIN, new GoToLoginCommand());
        commands.put(ParameterName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(ParameterName.GO_TO_SETTINGS, new GoToSettingsCommand());
        commands.put(ParameterName.GO_TO_CREATE_TEST, new GoToCreateTestCommand());
        commands.put(ParameterName.CREATE_QUESTION, new CreateQuestionCommand());
        commands.put(ParameterName.CREATE_TEST, new CreateTestCommand());
        commands.put(ParameterName.SHOW_ALL_TESTS, new ShowAllTestsCommand());
        commands.put(ParameterName.SHOW_TEST_BY_NAME, new ShowTestByNameCommand());
        commands.put(ParameterName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(ParameterName.GO_TO_APPOINT_TEST, new GoToAppointTestCommand());
        commands.put(ParameterName.APPOINT_TEST, new AppointTestCommand());
        commands.put(ParameterName.VIEW_ASSIGNED_TESTS, new ViewAssignedTestsCommand());
        commands.put(ParameterName.PASS_TEST, new PassTestCommand());
        commands.put(ParameterName.LOGOUT, new LogautCommand());
        commands.put(ParameterName.GO_TO_HALLO_PAGE, new GoToHalloPageCommand());
        commands.put(ParameterName.SEND_ANSWERS, new SendAnswersCommand());
        commands.put(ParameterName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
    }

    public Command getCommand(String commandName){

        Command command;
        ParameterName valueName;

        commandName = commandName.toUpperCase();
        valueName = ParameterName.valueOf(commandName);

        command = commands.get(valueName);

        return command;

    }
}
