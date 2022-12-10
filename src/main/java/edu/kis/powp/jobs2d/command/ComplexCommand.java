package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {
    private List<DriverCommand> commandsList;

    public ComplexCommand() {
        this.commandsList = new ArrayList<>();
    }

    public ComplexCommand(List<DriverCommand> existingCommandsList) {
        this.commandsList = new ArrayList<>(existingCommandsList);
    }

    public void addCommand(DriverCommand newCommandToAdd) {
        this.commandsList.add(newCommandToAdd);
    }

    public void addCommands(List<DriverCommand> existingCommandsList) {
        this.commandsList.addAll(existingCommandsList);
    }

    @Override
    public void execute(Job2dDriver driver) {
        for(DriverCommand command: commandsList) {
            command.execute(driver);
        }
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return commandsList.iterator();
    }

    @Override
    public Object clone() {
        List<DriverCommand> commandsListToClone = new ArrayList<>();
        for (DriverCommand driverCommand: commandsList) {
            commandsListToClone.add( (DriverCommand) driverCommand.clone());
        }
        return new ComplexCommand(commandsListToClone);
    }

}
