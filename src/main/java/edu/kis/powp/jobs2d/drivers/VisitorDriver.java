package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

public interface VisitorDriver {

    void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter);

    void visitDriverComposite(DriverComposite driverComposite);


    void visitLoggerDriverDecorator(LoggerDriverDecorator loggerDriverDecorator);
}
