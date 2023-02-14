package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

public class DriverCounterVisitor implements VisitorDriver {
	private int counterCommandsRecorderDriver = 0;
	private int counterLineDriverAdapter = 0;
	private int counterDriverComposite = 0;

	@Override
	public void visitCommandsRecorderDriver(Job2dDriver job2dDriver) {
		this.counterCommandsRecorderDriver++;
	}

	@Override
	public void visitLineDriverAdapter(Job2dDriver job2dDriver) {
		this.counterLineDriverAdapter++;
	}

	@Override
	public void visitDriverComposite(Job2dDriver job2dDriver) {
		this.counterDriverComposite++;
	}

	public int getCommandsRecorderDriver() {
		return this.counterCommandsRecorderDriver;
	};
	public int getLineDriverAdapter() {
		return this.counterLineDriverAdapter;
	};
	public int getDriverComposite() {
		return this.counterDriverComposite;
	};


}