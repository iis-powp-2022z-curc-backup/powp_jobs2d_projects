package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.LoggerDriver;

import java.util.logging.Logger;

public class LoggerDriverDecorator extends LoggerDriver implements VisitableDriver {
	Logger logger = Logger.getLogger("global");
	public LoggerDriverDecorator(){}

	@Override
	public void accept(VisitorDriver visitor) {
		visitor.visitLoggerDriverDecorator(this);
		this.logger.info("Working Logger");
	}
}
