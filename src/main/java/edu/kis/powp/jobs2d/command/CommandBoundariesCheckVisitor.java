package edu.kis.powp.jobs2d.command;

import java.util.Iterator;

import static java.lang.Math.abs;

public class CommandBoundariesCheckVisitor implements Visitor, ICommandBoundariesCheckStrategy{
	private final ICanvas canvas;
	private boolean isExceedingBoundaries = false;

	public CommandBoundariesCheckVisitor(ICanvas canvas) {
		this.canvas = canvas;
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
		isExceedingBoundaries = abs(operateToCommand.getPosX()) > canvas.getWidth() / 2 || abs(operateToCommand.getPosY()) > canvas.getHeight() / 2;
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		isExceedingBoundaries = abs(setPositionCommand.getPosX()) > canvas.getWidth() / 2 || abs(setPositionCommand.getPosY()) > canvas.getHeight() / 2;
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}

	@Override
	public void checkExceedingBoundaries() {

	}
}
