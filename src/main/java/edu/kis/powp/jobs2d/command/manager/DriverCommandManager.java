package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.jobs2d.command.gui.CommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements CommandManager {
	private DriverCommand currentCommand = null;
	private DriverManager driverManager = null;
	private Publisher changePublisher = new Publisher();

	/**
	 * Set current command.
	 * 
	 * @param commandList Set the command as current.
	 */
	@Override
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	/**
	 * Return current command.
	 * 
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return this.currentCommand;
	}

	public synchronized void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void runCommand() {
		getCurrentCommand().execute(DriverFeature.getDriverManager().getCurrentDriver());
	}

	@Override
	public synchronized void clearCurrentCommand() {
		this.currentCommand = null;
	}

	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return getCurrentCommand().toString();
	}

	public Publisher getChangePublisher() {
		return this.changePublisher;
	}
}