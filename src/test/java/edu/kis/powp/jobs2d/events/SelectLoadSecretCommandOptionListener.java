package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoadSecretCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ComplexCommand complexCommand = ComplexCommand.builder()
				.addCommand(new SetPositionCommand(-20, -50))
				.addCommand(new OperateToCommand(-20, -50))
				.addCommand(new SetPositionCommand(-20, -40))
				.addCommand(new OperateToCommand(-20, 50))
				.addCommand(new SetPositionCommand(0, -50))
				.addCommand(new OperateToCommand(0, -50))
				.addCommand(new SetPositionCommand(0, -40))
				.addCommand(new OperateToCommand(0, 50))
				.addCommand(new SetPositionCommand(70, -50))
				.addCommand(new OperateToCommand(20, -50))
				.addCommand(new OperateToCommand(20, 0))
				.addCommand(new OperateToCommand(70, 0))
				.addCommand(new OperateToCommand(70, 50))
				.addCommand(new OperateToCommand(20, 50))
				.setName("TopSecretCommand")
				.build();

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(complexCommand);
	}
}
