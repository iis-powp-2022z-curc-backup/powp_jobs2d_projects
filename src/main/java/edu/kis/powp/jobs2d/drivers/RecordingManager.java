package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
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
		VisitableDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).add((VisitableDriver) recorderDriver);
		} else {
			replacedDriver = currentDriver;
			DriverComposite driverComposite = new DriverComposite();
			driverComposite.add(currentDriver);
			driverComposite.add((VisitableDriver) recorderDriver);
			DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
		}

		DriverFeature.updateDriverInfo();
	}

	public void resumeRecording() {
		if (isAlreadyReplaced()) return;

		status = RECORDING_STATUS.IN_PROGRESS;
		VisitableDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).add((VisitableDriver) recorderDriver);
		} else {
			replacedDriver = currentDriver;
			DriverComposite driverComposite = new DriverComposite();
			driverComposite.add(currentDriver);
			driverComposite.add((VisitableDriver) recorderDriver);
			DriverFeature.getDriverManager().setCurrentDriver(driverComposite);
		}

		DriverFeature.updateDriverInfo();
	}

	public void stopRecording() {
		status = RECORDING_STATUS.STOPPED;
		VisitableDriver currentDriver = DriverFeature.getDriverManager().getCurrentDriver();

		if (currentDriver instanceof DriverComposite) {
			((DriverComposite) currentDriver).remove(recorderDriver);
		} else {
			DriverFeature.getDriverManager().setCurrentDriver((VisitableDriver) replacedDriver);
		}

		DriverFeature.updateDriverInfo();
	}

	public void loadRecording() {
		List<DriverCommand> commands = recorderDriver.getCommands();
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "Recorded commands");
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
