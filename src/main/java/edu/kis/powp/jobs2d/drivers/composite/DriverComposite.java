package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.List;

public class DriverComposite implements IDriverComposite {

    private List<IDriverComposite> CommandList;
    public DriverComposite(List<IDriverComposite> CommandList){
        this.CommandList = CommandList;
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.CommandList.forEach(driverCommand -> driverCommand.execute(driver));
    }
}
