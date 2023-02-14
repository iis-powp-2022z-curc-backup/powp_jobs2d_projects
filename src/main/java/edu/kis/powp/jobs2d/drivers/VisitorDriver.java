package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public interface VisitorDriver {
    public void visitCommandsRecorderDriver(Job2dDriver job2dDriver);
    public void visitLineDriverAdapter(Job2dDriver job2dDriver);
    public void visitDriverComposite(Job2dDriver job2dDriver);

}
