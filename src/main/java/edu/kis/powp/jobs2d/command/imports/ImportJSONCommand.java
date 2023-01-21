package edu.kis.powp.jobs2d.command.imports;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.imports.json.Command;
import edu.kis.powp.jobs2d.command.imports.json.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ImportJSONCommand implements ImportCommandInterface {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public List<DriverCommand> importCommand(String fileContent) {
        List<DriverCommand> commands = new ArrayList<>();
        try {
            Commands mappedText = map(fileContent);
            commands = parse(mappedText);
        } catch (Exception ex) {
            logger.info("An exception occurred while trying to parse file. " + ex);
            ex.printStackTrace();
        }

        return commands;
    }

    private Commands map(String text) throws Exception {
        try {
            return new ObjectMapper().readValue(text, Commands.class);
        } catch (IOException ex) {
            throw new Exception("Incorrect input text. " + ex);
        }
    }

    private List<DriverCommand> parse(Commands mappedCommands) throws Exception {
        List<DriverCommand> parsedCommands = new ArrayList<>();
        for (Command command : mappedCommands.getCommands()) {
            parsedCommands.add(chooseCommand(command));
        }

        return parsedCommands;
    }

    private DriverCommand chooseCommand(Command command) throws Exception {
        Integer x = command.position.x;
        Integer y = command.position.y;
        switch (command.type) {
            case OperateTo: return new OperateToCommand(x, y);
            case SetPosition: return new SetPositionCommand(x, y);
            default: throw new Exception("Unsupported command type: " + command.type);
        }
    }
}
