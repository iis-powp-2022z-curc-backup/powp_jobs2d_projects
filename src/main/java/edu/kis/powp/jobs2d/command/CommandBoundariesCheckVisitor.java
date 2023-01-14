package edu.kis.powp.jobs2d.command;

import java.awt.*;
import java.util.Iterator;

public class CommandBoundariesCheckVisitor implements Visitor{
	private Dimension operateToDimensions = new Dimension();
	private Dimension setPositionDimensions = new Dimension();

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
		this.operateToDimensions.width = operateToCommand.getPosX();
		this.operateToDimensions.height = operateToCommand.getPosY();
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		this.setPositionDimensions.width = setPositionCommand.getPosX();
		this.setPositionDimensions.height = setPositionCommand.getPosX();
	}

	public Dimension getOperateToDimensions(){
		return this.operateToDimensions;
	}

	public Dimension getSetPositionDimensions(){
		return this.setPositionDimensions;
	}

	public boolean getResult(){
		return false;
	}
}
