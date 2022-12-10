package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverChangeObserver implements Subscriber {

    @Override
    public void update() {
        System.out.println("Driver changed");
    }

    public String toString() {
        return "Driver Change Observer";
    }
}
