package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

public class CommandBoundariesCheckVisitor implements Visitor{

	private boolean isExceedingBoundaries = false;
	private final ICanvasBoundariesCheckStrategy strategy;

	public CommandBoundariesCheckVisitor(ICanvasBoundariesCheckStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
		Iterator<DriverCommand> iterator = iCompoundCommand.iterator();

		while(iterator.hasNext()) {
			DriverCommand driverCommand = iterator.next();
			driverCommand.accept(this);
			if(isExceedingBoundaries){
				break;
			}
		}
	}

	@Override
	public void visitOperateToCommand(OperateToCommand operateToCommand) {
		isExceedingBoundaries = strategy.checkExceedingBoundaries(operateToCommand.getPosX(), operateToCommand.getPosY());
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		isExceedingBoundaries = strategy.checkExceedingBoundaries(setPositionCommand.getPosX(), setPositionCommand.getPosY());
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}
}
