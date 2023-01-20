package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

public class DriverCounterVisitor implements Visitor {
	private int counterCommandsRecorderDriver = 0;
	private int counterLineDriverAdapter = 0;
	private int counterDriverComposite = 0;

	public void visitCommandsRecorderDriver(CommandsRecorderDriver commandsRecorderDriver) {
		this.counterCommandsRecorderDriver++;
	};

	public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
		this.counterLineDriverAdapter++;
	};

	public void visitDriverComposite(DriverComposite driverComposite) {

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