package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

public class CommandBoundariesCheckVisitor implements Visitor{
	@Override
	public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> iterator = iCompoundCommand.iterator();

		while(iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();

			driverCommand.accept(this);
		}
	}

	@Override
	public void visitOperateToCommand(OperateToCommand operateToCommand) {
		System.out.println("VISIT OPERATE TO COMMAND");

	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		System.out.println("VISIT SET POSITION COMMAND");

	}

	public boolean getResult() {
		return false;
	}
}
