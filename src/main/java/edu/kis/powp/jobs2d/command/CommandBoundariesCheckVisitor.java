package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

import static java.lang.Math.abs;

public class CommandBoundariesCheckVisitor implements Visitor{
	private final ICanvas canvas;
	private boolean isExceedingBoundaries = false;
	private final ICommandBoundariesCheckStrategy strategy;

	public CommandBoundariesCheckVisitor(ICanvas canvas, ICommandBoundariesCheckStrategy strategy) {
		this.canvas = canvas;
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
		isExceedingBoundaries = strategy.checkExceedingBoundaries(canvas, abs(operateToCommand.getPosX()),abs(operateToCommand.getPosY()));
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		isExceedingBoundaries = strategy.checkExceedingBoundaries(canvas, abs(setPositionCommand.getPosX()),abs(setPositionCommand.getPosY()));
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}
}
