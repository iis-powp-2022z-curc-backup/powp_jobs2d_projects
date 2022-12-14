package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectRectangleCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commandList = new ArrayList<>();
		commandList.add(new SetPositionCommand(-100, -100));
		commandList.add(new OperateToCommand(-100, -100));
		commandList.add(new OperateToCommand(-100, 100));
		commandList.add(new OperateToCommand(200, 100));
		commandList.add(new OperateToCommand(200, -100));
		commandList.add(new OperateToCommand(-100, -100));

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commandList, "RectangleCommand");
	}
}
