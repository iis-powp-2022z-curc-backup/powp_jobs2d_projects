package edu.kis.powp.jobs2d.command;

public interface DriverCommandVisitor {

	public void visitICompoundCommand(ICompoundCommand iCompoundCommand);
	public void visitOperateToCommand(OperateToCommand operateToCommand);
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand);

}
