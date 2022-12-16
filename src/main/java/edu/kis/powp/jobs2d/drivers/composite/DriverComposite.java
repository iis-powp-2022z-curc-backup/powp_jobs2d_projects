package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements Job2dDriver{
	private List<Job2dDriver> driverList = new ArrayList<Job2dDriver>();


	public void add(Job2dDriver driver){
		this.driverList.add(driver);
	}

	public void remove(Job2dDriver driver){
		this.driverList.remove(driver);
	}

	@Override
	public void setPosition(int x, int y) {
		for (Job2dDriver driver : driverList){
			driver.setPosition(x,y);
		}
	}

	@Override
	public void operateTo(int x, int y) {
		for (Job2dDriver driver: driverList) {
			driver.operateTo(x,y);
		}
	}
}
