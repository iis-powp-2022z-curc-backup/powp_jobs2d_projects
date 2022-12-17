package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.enums.RECORDING_STATUS;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        RecordingManager recordingManager = RecordingFeature.getRecordingManager();

        if (recordingManager.getStatus() == RECORDING_STATUS.IN_PROGRESS && shouldResumeRecording()) {
            recordingManager.resumeRecording();
        }
    }

    private boolean shouldResumeRecording() {
        Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();

        if (driver instanceof CommandsRecorderDriver) {
            return false;
        }

        if (driver instanceof DriverComposite) {
            boolean alreadyHasRecorder = ((DriverComposite) driver).anyMatch(d -> d instanceof CommandsRecorderDriver);
            return !alreadyHasRecorder;
        }

        return true;
    }

    public String toString() {
        return "Driver Change Observer";
    }
}
