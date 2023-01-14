package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.command.CommandBoundariesCheckVisitor;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class SubscribeCommandBoundariesCheckVisitor implements Subscriber {
	private final DriverCommandManager manager;
	private final Logger logger = Logger.getLogger("global");
	private final CommandBoundariesCheckVisitor commandBoundariesCheckVisitor;

	public SubscribeCommandBoundariesCheckVisitor(CommandBoundariesCheckVisitor commandBoundariesCheckVisitor, DriverCommandManager driverManager) {
		this.commandBoundariesCheckVisitor = commandBoundariesCheckVisitor;
		this.manager = driverManager;
	}

	@Override
	public void update() {
		manager.getCurrentCommand().accept(commandBoundariesCheckVisitor);

		this.logger.info("Czy ta komenda rysuje poza canvas: " + commandBoundariesCheckVisitor.isExceedingCanvasBoundaries());
	}
}
