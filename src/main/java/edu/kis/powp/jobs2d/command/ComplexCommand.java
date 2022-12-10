package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.LinkedList;
import java.util.List;

public class ComplexCommand implements DriverCommand {
    private final List<DriverCommand> driverCommandList = new LinkedList<>();

    public void addCommand(DriverCommand command) {
        this.driverCommandList.add(command);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommandList.forEach(driverCommand -> driverCommand.execute(driver));
    }
}
