package by.epamtc.birukov.controller.command;

import by.epamtc.birukov.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParametrName, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(ParametrName.CREATE_NEW_USER, new CreateNewUserCommand());
        commands.put(ParametrName.LOGINATION, new LoginCommand());
        commands.put(ParametrName.GREETING, new GreetingPageCommand());
        commands.put(ParametrName.GO_TO_LOGIN, new GoToLoginCommand());
        commands.put(ParametrName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
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
