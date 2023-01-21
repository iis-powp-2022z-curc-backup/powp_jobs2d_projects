package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DistanceDriverAdapter implements Job2dDriver {
    private static final int MAX_DISTANCE = 2000;

    private int startX = 0, startY = 0;
    private int totalDistance = 0;
    private String name;
    private List<Job2dDriver> driversList = new ArrayList<>();
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public DistanceDriverAdapter(String name) {
        super();
        this.name = name;
    }

    public void add(Job2dDriver driver) {
        driversList.add(driver);
    }

    public void resetDistance() {

        logger.info("Ink has been refilled");
        totalDistance = 0;
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
        for (Job2dDriver driver : driversList) {
            driver.setPosition(x, y);
        }
    }

    @Override
    public void operateTo(int x, int y) {
        int distance = (int) Math.sqrt(Math.pow(this.startX - x, 2)+Math.pow(this.startY - y, 2));
        if (totalDistance < MAX_DISTANCE) {
            logger.info("total distance: " + (totalDistance += distance));
            for (Job2dDriver driver : driversList) {
                driver.operateTo(x, y);
            }
        } else {
            logger.info("We have run out of ink!");
        }
    }

    @Override
    public String toString() {
        return "distance driver - " + name;
    }
}
