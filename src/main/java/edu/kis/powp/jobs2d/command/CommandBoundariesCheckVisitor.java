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
		checkExceedingBoundaries(abs(operateToCommand.getPosX()),abs(operateToCommand.getPosY()));
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		checkExceedingBoundaries(abs(setPositionCommand.getPosX()),abs(setPositionCommand.getPosY()));
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}

	@Override
	public void checkExceedingBoundaries(int X, int Y) {
		if(X<canvas.getHeight()/2 || Y <canvas.getWidth()/2){
			isExceedingBoundaries = true;
		}
	}
}
