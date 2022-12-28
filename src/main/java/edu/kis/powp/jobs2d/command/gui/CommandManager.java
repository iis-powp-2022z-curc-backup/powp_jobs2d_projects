package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.observer.Publisher;

public interface CommandManager {
    void runCommand();
    void clearCurrentCommand();
    String getCurrentCommandAsText();
    void setCurrentCommand(DriverCommand driverCommand);
    Publisher getChangePublisher();
}
