package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ComplexCommand class to execute any command chain.
 */
public class ComplexCommand implements ICompoundCommand {
    private final List<DriverCommand> driverCommandList;

    public ComplexCommand(List<DriverCommand> driverCommandList) {
        if (driverCommandList == null) {
            this.driverCommandList = new LinkedList<>();
        } else {
            this.driverCommandList = driverCommandList;
        }
    }

    /**
     * @param command - command to add
     */
    public void addCommand(DriverCommand command) {
        this.driverCommandList.add(command);
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommandList.forEach(driverCommand -> driverCommand.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommandList.iterator();
    }
}
