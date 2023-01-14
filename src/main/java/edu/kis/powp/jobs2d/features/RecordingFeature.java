package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.drivers.MaintainRecordingAfterDriverChangeObserver;
import edu.kis.powp.jobs2d.drivers.RecordingManager;

public class RecordingFeature {

	private static RecordingManager recordingManager;

	/**
	 * Setup Recording Plugin and add to application.
	 */
	public static void setupRecordingPlugin() {
		recordingManager = new RecordingManager();

		MaintainRecordingAfterDriverChangeObserver driverObserver = new MaintainRecordingAfterDriverChangeObserver();
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverObserver);
	}

	public static RecordingManager getRecordingManager() {
		return recordingManager;
	}
}
