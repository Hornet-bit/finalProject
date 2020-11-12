package by.epamtc.birukov.controller.command;

import by.epamtc.birukov.controller.command.impl.*;
import by.epamtc.birukov.controller.command.impl.go_to.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParametrName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(ParametrName.CREATE_NEW_USER, new CreateNewUserCommand());
        commands.put(ParametrName.AUTHORIZATION, new AuthenticationCommand());
        commands.put(ParametrName.GREETING, new GreetingPageCommand());
        commands.put(ParametrName.GO_TO_LOGIN, new GoToLoginCommand());
        commands.put(ParametrName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(ParametrName.GO_TO_SETTINGS, new GoToSettingsCommand());
        commands.put(ParametrName.GO_TO_CREATE_TEST, new GoToCreateTestCommand());
        commands.put(ParametrName.CREATE_QUESTION, new CreateQuestionCommand());
        commands.put(ParametrName.CREATE_TEST, new CreateTestCommand());
        commands.put(ParametrName.SHOW_ALL_TESTS, new ShowAllTestsCommand());
        commands.put(ParametrName.SHOW_TEST_BY_NAME, new ShowTestByNameCommand());
        commands.put(ParametrName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(ParametrName.GO_TO_APPOINT_TEST, new GoToAppointTestCommand());
        commands.put(ParametrName.APPOINT_TEST, new AppointTestCommand());
        commands.put(ParametrName.VIEW_ASSIGNED_TESTS, new ViewAssignedTestsCommand());
        commands.put(ParametrName.PASS_TEST, new PassTestCommand());
        commands.put(ParametrName.LOGOUT, new LogautCommand());
        commands.put(ParametrName.GO_TO_HALLO_PAGE, new GoToHalloPageCommand());
    }

    public Command getCommand(String commandName){

        Command command;
        ParametrName valueName;

        commandName = commandName.toUpperCase();
        valueName = ParametrName.valueOf(commandName);

        command = commands.get(valueName);

        return command;

    }
}
