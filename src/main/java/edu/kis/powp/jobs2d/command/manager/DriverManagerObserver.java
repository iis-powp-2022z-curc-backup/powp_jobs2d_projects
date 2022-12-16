package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import java.awt.dnd.DragGestureEvent;

public class DriverManagerObserver implements Subscriber {
    private DriverManager driverManager;
    private static Application app;
    public DriverManagerObserver(DriverManager driverManager, Application application){
        app =application;
        driverManager.getChangePublisher().addSubscriber(this);
        this.driverManager = driverManager;
    }
    @Override
    public void update() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }
}
