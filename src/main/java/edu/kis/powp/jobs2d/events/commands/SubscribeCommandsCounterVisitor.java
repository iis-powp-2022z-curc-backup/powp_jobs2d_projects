package edu.kis.powp.jobs2d.events.commands;

import edu.kis.powp.jobs2d.command.CommandsCounterDriverCommandVisitor;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class SubscribeCommandsCounterVisitor implements Subscriber {
	private DriverCommandManager manager;
	private Logger logger = Logger.getLogger("global");
	private CommandsCounterDriverCommandVisitor commandsCounterVisitor;
	public SubscribeCommandsCounterVisitor(CommandsCounterDriverCommandVisitor commandsCounterVisitor, DriverCommandManager driverManager) {
		this.commandsCounterVisitor = commandsCounterVisitor;
		this.manager = driverManager;
	}

	@Override
	public void update() {
		manager.getCurrentCommand().accept(commandsCounterVisitor);

		this.logger.info("OperateToCommand wywołano " + commandsCounterVisitor.getOperateToCommandCount()  + " razy");
		this.logger.info("SetPositionCommand wywołano " + commandsCounterVisitor.getSetPositionCommandCount()  + " razy");
	}
}
