package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectTestFigure3OptionListener implements ActionListener {

	private final DriverManager driverManager;

	public SelectTestFigure3OptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commandList = new ArrayList<>();
		commandList.add(new SetPositionCommand(-100, -100));
		commandList.add(new OperateToCommand(-100, -100));
		commandList.add(new OperateToCommand(-100, 100));
		commandList.add(new OperateToCommand(200, 100));
		commandList.add(new OperateToCommand(200, -100));
		commandList.add(new OperateToCommand(-100, -100));
		ComplexCommand complexCommand = new ComplexCommand(commandList);
		complexCommand.execute(driverManager.getCurrentDriver());
	}
}
