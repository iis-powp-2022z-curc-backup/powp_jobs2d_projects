package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

public class DriverCounterVisitor implements VisitorDriver {

	private int counterLineDriverAdapter = 0;
	private int counterDriverComposite = 0;
	private int counterDriverLoggerDecorator = 0;



	@Override
	public void visitLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
		this.counterLineDriverAdapter++;
	}

	@Override
	public void visitDriverComposite(DriverComposite driverComposite) {
		this.counterDriverComposite++;
		for (VisitableDriver item : driverComposite.getDriverList()){
			item.accept(this);
		}
	}

	@Override
	public void visitLoggerDriverDecorator(LoggerDriverDecorator loggerDriverDecorator) {
		this.counterDriverLoggerDecorator++;
	}

	public int getLineDriverAdapter() {
		return this.counterLineDriverAdapter;
	};
	public int getDriverComposite() {
		return this.counterDriverComposite;
	};
	public int getLoggerDriverDecorator() {return this.counterDriverLoggerDecorator; };

	public void reset() {
		counterLineDriverAdapter = 0;
		counterDriverComposite = 0;
		counterDriverLoggerDecorator = 0;
	}


}