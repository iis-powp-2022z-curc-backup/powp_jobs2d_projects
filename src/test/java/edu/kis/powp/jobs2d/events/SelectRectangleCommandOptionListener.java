package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRectangleCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ComplexCommand complexCommand = ComplexCommand.builder().addCommand(new SetPositionCommand(-100, -100))
				.addCommand(new OperateToCommand(-100, -100)).addCommand(new OperateToCommand(-100, 100))
				.addCommand(new OperateToCommand(200, 100)).addCommand(new OperateToCommand(200, -100))
				.addCommand(new OperateToCommand(-100, -100)).setName("RectangleCommand").build();

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(complexCommand);
	}
}
