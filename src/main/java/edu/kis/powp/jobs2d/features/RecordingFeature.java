package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.enums.RECORDING_STATUS;

public class RecordingFeature {

    private RECORDING_STATUS status = RECORDING_STATUS.STOPPED;
    private static RecordingManager recordingManager;
    /**
     * Setup Recording Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupRecordingPlugin(Application application) {
        recordingManager = new RecordingManager();

        DriverChangeObserver driverObserver = new DriverChangeObserver();
        DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverObserver);
    }

    public static RecordingManager getRecordingManager() {
        return recordingManager;
    }

}
