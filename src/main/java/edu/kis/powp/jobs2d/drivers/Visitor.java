package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

public interface Visitor {
    public void visitCommandsRecorderDriver(CommandsRecorderDriver commandsRecorderDriver);
    public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter);
    public void visitDriverComposite(DriverComposite driverComposite);

}
