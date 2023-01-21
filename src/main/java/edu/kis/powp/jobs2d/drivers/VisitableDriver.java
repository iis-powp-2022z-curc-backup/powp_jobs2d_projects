package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public interface VisitableDriver extends Job2dDriver {
	public void accept(Visitor visitor);
}
