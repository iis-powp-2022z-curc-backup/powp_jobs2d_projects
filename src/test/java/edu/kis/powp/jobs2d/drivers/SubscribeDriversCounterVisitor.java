package edu.kis.powp.jobs2d.drivers;


import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class SubscribeDriversCounterVisitor implements Subscriber {
	private VisitableDriver visitableDriver;
	private DriverManager manager;
	private Logger logger = Logger.getLogger("global");
	private DriverCounterVisitor driverCounterVisitor;
	public SubscribeDriversCounterVisitor(DriverCounterVisitor driverCounterVisitor, DriverManager driverManager, VisitableDriver visitableDriver) {
		this.driverCounterVisitor = driverCounterVisitor;
		this.visitableDriver = visitableDriver;
		this.manager = driverManager;
	}

	public void update() {
		manager.getCurrentDriver().accept(driverCounterVisitor);

		this.logger.info("driver composite wywołano: " + driverCounterVisitor.getDriverComposite()  + " razy");
		this.logger.info("line driver wywołano: " + driverCounterVisitor.getLineDriverAdapter()  + " razy");
		this.logger.info("loger driver wywołano: " + driverCounterVisitor.getLoggerDriverDecorator()  + " razy");
		driverCounterVisitor.reset();
	}
}
