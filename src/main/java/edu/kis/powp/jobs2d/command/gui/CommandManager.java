package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.observer.Publisher;

import java.util.List;

public interface CommandManager {
    void runCommand();
    void clearCurrentCommand();
    void setCurrentCommand(DriverCommand driverCommand);
    void setCurrentCommand(List<DriverCommand> driverCommand, String name);
    String getCurrentCommandString();
    Publisher getChangePublisher();
    DriverCommand getCurrentCommand();
}
