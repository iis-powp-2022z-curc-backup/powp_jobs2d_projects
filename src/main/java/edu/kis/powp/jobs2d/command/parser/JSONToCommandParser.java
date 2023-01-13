package edu.kis.powp.jobs2d.command.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONToCommandParser {
    public static List<DriverCommand> getCommands(String fileContent) {
        List<DriverCommand> commands = new ArrayList<>();
        try {
            Commands mappedText = JSONToCommandParser._map(fileContent);
            commands = JSONToCommandParser._parse(mappedText);
        } catch (Exception ex) {
            System.out.println("An exception occurred while trying to parse file. " + ex);
            ex.printStackTrace();
        }

        return commands;
    }

    private static Commands _map(String text) throws Exception {
        try {
            return new ObjectMapper().readValue(text, Commands.class);
        } catch (IOException ex) {
            throw new Exception("Incorrect input text. " + ex);
        }
    }

    private static List<DriverCommand> _parse(Commands mappedCommands) throws Exception {
        List<DriverCommand> parsedCommands = new ArrayList<>();
        for (Command command : mappedCommands.getCommands()) {
            parsedCommands.add(_chooseCommand(command));
        }

        return parsedCommands;
    }

    private static DriverCommand _chooseCommand(Command command) throws Exception {
        Integer x = command.position.x;
        Integer y = command.position.y;
        switch (command.type) {
            case OperateTo: return new OperateToCommand(x, y);
            case SetPosition: return new SetPositionCommand(x, y);
            default: throw new Exception("Unsupported command type: " + command.type);
        }
    }
}
