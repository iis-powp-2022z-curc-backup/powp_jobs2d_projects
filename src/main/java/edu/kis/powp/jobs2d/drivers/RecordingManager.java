package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.enums.RECORDING_STATUS;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.util.List;

public class RecordingManager {

	private RECORDING_STATUS status = RECORDING_STATUS.STOPPED;
	private Job2dDriver replacedDriver = null;
	private final CommandsRecorderDriver recorderDriver = new CommandsRecorderDriver();

	public void startRecording() {
		if (isAlreadyReplaced()) return;

		status = RECORDING_STATUS.IN_PROGRESS;
		Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).add(recorderDriver);
		} else {
			replacedDriver = currentDriver;
			DriverComposite driverComposite = new DriverComposite();
			driverComposite.add(currentDriver);
			driverComposite.add(recorderDriver);
			DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
		}

		DriverFeature.updateDriverInfo();
	}

	public void resumeRecording() {
		if (isAlreadyReplaced()) return;

		status = RECORDING_STATUS.IN_PROGRESS;
		Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).add(recorderDriver);
		} else {
			replacedDriver = currentDriver;
			DriverComposite driverComposite = new DriverComposite();
			driverComposite.add(currentDriver);
			driverComposite.add(recorderDriver);
			DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
		}

		DriverFeature.updateDriverInfo();
	}

	public void stopRecording() {
		status = RECORDING_STATUS.STOPPED;
		Job2dDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).remove(recorderDriver);
		} else {
			DriverFeature.getDriverManager().setCurrentDriver(replacedDriver);
		}

		DriverFeature.updateDriverInfo();
	}

	public void loadRecording() {
		List<DriverCommand> commands = recorderDriver.getCommands();
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(new ComplexCommand(commands, "Recorded commands"));
	}

	public RECORDING_STATUS getStatus() {
		return status;
	}

	public void clearRecording() {
		recorderDriver.clear();
	}

	private boolean isAlreadyReplaced() {
		Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();

		if (driver instanceof CommandsRecorderDriver) {
			return true;
		}

		if (driver instanceof DriverComposite) {
			boolean hasAlreadyRecorder = ((DriverComposite) driver).anyMatch(d -> d instanceof CommandsRecorderDriver);
			return hasAlreadyRecorder;
		}

		return false;
	}
}
