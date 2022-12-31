package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.enums.RECORDING_STATUS;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

public class MaintainRecordingAfterDriverChangeObserver implements Subscriber {

	@Override
	public void update() {
		RecordingManager recordingManager = RecordingFeature.getRecordingManager();

		if (recordingManager.getStatus() == RECORDING_STATUS.IN_PROGRESS) {
			recordingManager.resumeRecording();
		}
	}

	public String toString() {
		return "Driver Change Observer";
	}
}
