package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.drivers.VisitorDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DriverComposite implements VisitableDriver {
	private List<VisitableDriver> DriverLists = new ArrayList<VisitableDriver>();


	public void add(VisitableDriver driver){
		this.DriverLists.add(driver);
	}

	public void remove(Job2dDriver driver){
		this.DriverLists.remove(driver);
	}

	public boolean anyMatch(Predicate<Job2dDriver> predicate) { return this.DriverLists.stream().anyMatch(predicate); }

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

	public void accept(VisitorDriver visitor){
		visitor.visitDriverComposite(this);


	}

	public List<VisitableDriver> getDriverList(){
		return DriverLists;
	}
}
