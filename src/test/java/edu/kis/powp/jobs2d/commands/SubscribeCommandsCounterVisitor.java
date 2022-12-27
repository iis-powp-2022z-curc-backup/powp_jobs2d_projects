package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.command.CommandsCounterVisitor;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import java.util.logging.Logger;

public class SubscribeCommandsCounterVisitor implements Subscriber {
	private DriverCommandManager manager;
	private Logger logger = Logger.getLogger("global");
	private CommandsCounterVisitor commandsCounterVisitor;
	public SubscribeCommandsCounterVisitor(CommandsCounterVisitor commandsCounterVisitor, DriverCommandManager driverManager) {
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
