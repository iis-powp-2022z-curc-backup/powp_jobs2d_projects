package edu.kis.powp.jobs2d.drivers;


import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class SubscribeDriversCounterVisitor implements Subscriber {
	private VisitableDriver visitableDriver;
	private Logger logger = Logger.getLogger("global");
	private DriverCounterVisitor driverCounterVisitor;
	public SubscribeDriversCounterVisitor(DriverCounterVisitor driverCounterVisitor, VisitableDriver visitableDriver) {
		this.driverCounterVisitor = driverCounterVisitor;
		this.visitableDriver = visitableDriver;
	}

	public void update() {

		visitableDriver.accept(driverCounterVisitor);

		this.logger.info("driver composite wywołano " + driverCounterVisitor.getDriverComposite()  + " razy");
		this.logger.info("command recorder wywołano " + driverCounterVisitor.getCommandsRecorderDriver()  + " razy");
		this.logger.info("line driver wywołano " + driverCounterVisitor.getLineDriverAdapter()  + " razy");
	}
}
