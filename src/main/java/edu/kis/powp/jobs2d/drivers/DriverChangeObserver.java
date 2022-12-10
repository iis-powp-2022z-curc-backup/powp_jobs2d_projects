package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.enums.RECORDING_STATUS;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        RecordingManager recordingManager = RecordingFeature.getRecordingManager();

        Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();
        if (recordingManager.getStatus() == RECORDING_STATUS.IN_PROGRESS
                && !driver.getClass().getSimpleName().equals("CommandsRecorderDriver")) {
            recordingManager.resumeRecording();
        }
    }

    public String toString() {
        return "Driver Change Observer";
    }
}
