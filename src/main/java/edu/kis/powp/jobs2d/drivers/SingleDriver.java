package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public interface SingleDriver extends Cloneable {

	public void execute(Job2dDriver driver);
	public Object clone();

	public void accept(Visitor visitor);
}
