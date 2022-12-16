package edu.kis.powp.jobs2d.command;

public class CommandVisitor implements Visitor {

	public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {};
	public void visitOperateToCommand(OperateToCommand operateToCommand) {};
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {};

}
