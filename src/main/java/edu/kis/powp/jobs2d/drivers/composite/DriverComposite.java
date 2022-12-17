package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements Job2dDriver{
	private List<Job2dDriver> DriverLists = new ArrayList<Job2dDriver>();


	public void add(Job2dDriver driver){
		this.DriverLists.add(driver);
	}

	public void remove(Job2dDriver driver){
		this.DriverLists.remove(driver);
	}


	@Override
	public void setPosition(int x, int y) {
		for (Job2dDriver driver : DriverLists){
			driver.setPosition(x,y);
		}
	}

	@Override
	public void operateTo(int x, int y) {
		for (Job2dDriver driver: DriverLists) {
			driver.operateTo(x,y);
		}
	}

	@Override
	public String toString() {
		return DriverLists.toString();
	}
}
