package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.observer.Publisher;

/**
 * Driver manager provides means to setup the driver. It also enables other components and features of the application to react on
 * configuration changes.
 */
public class DriverManager {

	private Job2dDriver currentDriver = new LoggerDriver();
	private final Publisher changePublisher = new Publisher();
	private final DriverComposite featureDrivers = new DriverComposite();

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(Job2dDriver driver) {
		currentDriver = driver;
		changePublisher.notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized Job2dDriver getCurrentDriver() {
		final DriverComposite driverComposite = new DriverComposite();
		driverComposite.add(currentDriver);
		driverComposite.add(featureDrivers);
		return driverComposite;
	}

	public synchronized void addFeatureDriver(Job2dDriver driver) {
		featureDrivers.add(driver);
	}

	public synchronized void removeFeatureDriver(Job2dDriver driver) {
		featureDrivers.remove(driver);
	}

	public Publisher getChangePublisher() {
		return changePublisher;
	}
}
