package edu.kis.powp.jobs2d.command;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

import static java.lang.Math.abs;

public class CommandBoundariesCheckVisitor implements Visitor{
	private final JPanel canvas;
	private boolean isExceedingBoundaries = false;

	public CommandBoundariesCheckVisitor(JPanel canvas) {
		this.canvas = canvas;
	}

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
		if(abs(operateToCommand.getPosX()) > canvas.getWidth()/2 || abs(operateToCommand.getPosY()) > canvas.getHeight()/2){
			isExceedingBoundaries = true;
		}
	}

	@Override
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		if(abs(setPositionCommand.getPosX()) > canvas.getWidth()/2 || abs(setPositionCommand.getPosY()) > canvas.getHeight()/2){
			isExceedingBoundaries = true;
		}
	}

	public boolean isExceedingCanvasBoundaries(){
		return isExceedingBoundaries;
	}
}
