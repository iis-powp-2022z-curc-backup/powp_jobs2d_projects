package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.command.transformers.ComplexTransformerCommand;
import edu.kis.powp.jobs2d.drivers.adapter.TransformerDriver;
import edu.kis.powp.observer.Publisher;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

	private VisitableDriver currentDriver = new LoggerDriverDecorator();
	private final Publisher changePublisher = new Publisher();
	private final DriverComposite featureDrivers = new DriverComposite();

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(VisitableDriver driver) {
		currentDriver = driver;
		changePublisher.notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized DriverComposite getCurrentDriver() {
		final DriverComposite driverComposite = new DriverComposite();
		driverComposite.add(currentDriver);
		driverComposite.add(featureDrivers);
		return driverComposite;
	}

	public synchronized void addFeatureDriver(Job2dDriver driver) {
		featureDrivers.add((VisitableDriver) driver);
	}

	public synchronized void removeFeatureDriver(Job2dDriver driver) {
		featureDrivers.remove(driver);
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}

	public Job2dDriver transformCurrentDriver(ComplexTransformerCommand transformerCommands, String name) {
		currentDriver = (VisitableDriver) new TransformerDriver(currentDriver, transformerCommands, name);
		return currentDriver;
	}
}
