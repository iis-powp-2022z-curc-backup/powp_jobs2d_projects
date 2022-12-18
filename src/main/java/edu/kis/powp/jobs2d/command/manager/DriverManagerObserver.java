package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

public class DriverManagerObserver implements Subscriber {

    public DriverManagerObserver(){

    }
    @Override
    public void update() {
        DriverFeature.updateDriverInfo();
    }
}
