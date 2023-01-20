package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

import static java.lang.Math.abs;

public class CommandBoundariesCheckVisitor implements Visitor{
	private final ICanvas canvas;
	private boolean isExceedingBoundaries = false;
	private final ICanvasBoundariesCheckStrategy strategy;

	public CommandBoundariesCheckVisitor(ICanvas canvas, ICanvasBoundariesCheckStrategy strategy) {
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
		isExceedingBoundaries = strategy.checkExceedingBoundaries(canvas, operateToCommand.getPosX(), operateToCommand.getPosY());
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		isExceedingBoundaries = strategy.checkExceedingBoundaries(canvas, setPositionCommand.getPosX(), setPositionCommand.getPosY());
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}
}
