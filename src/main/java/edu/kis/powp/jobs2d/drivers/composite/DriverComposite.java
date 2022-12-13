package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements Job2dDriver{
    private List<Job2dDriver> test = new ArrayList<Job2dDriver>();


    public void add(Job2dDriver driver){
        this.test.add(driver);
    }


    @Override
    public void setPosition(int i, int i1) {
        for (Job2dDriver driver : test){
            driver.setPosition(i,i1);
        }
    }

    @Override
    public void operateTo(int i, int i1) {
        for (Job2dDriver driver: test) {
            driver.operateTo(i,i1);
        }
    }
}
