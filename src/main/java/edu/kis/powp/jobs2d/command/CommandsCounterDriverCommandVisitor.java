package edu.kis.powp.jobs2d.command;


import java.util.Iterator;

public class CommandsCounterDriverCommandVisitor implements DriverCommandVisitor {
	private int counterOperateToCommand = 0;
	private int counterSetPositionCommand = 0;


	public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> iterator = iCompoundCommand.iterator();

		while(iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();

			driverCommand.accept(this);
		}
	}

	public void visitOperateToCommand(OperateToCommand operateToCommand) {
		this.counterOperateToCommand++;
	}

	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		this.counterSetPositionCommand++;
	}

	public int getOperateToCommandCount() {
		return this.counterOperateToCommand;
	}

	public int getSetPositionCommandCount() {
		return this.counterSetPositionCommand;
	}


}
