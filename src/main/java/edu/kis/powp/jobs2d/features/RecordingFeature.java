package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverChangeObserver;
import edu.kis.powp.jobs2d.drivers.RecordingManager;

public class RecordingFeature {

	private static RecordingManager recordingManager;

	/**
	 * Setup Recording Plugin and add to application.
	 */
	public static void setupRecordingPlugin() {
		recordingManager = new RecordingManager();

		DriverChangeObserver driverObserver = new DriverChangeObserver();
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverObserver);
	}

	public static RecordingManager getRecordingManager() {
		return recordingManager;
	}
}
