package edu.kis.powp.jobs2d.command;


public class CommandsCounterVisitor implements Visitor {
;
	private int counterOperateToCommand = 0;
	private int counterSetPositionCommand = 0;


	public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
		while(iCompoundCommand.iterator().hasNext()) {
			DriverCommand driverCommand = iCompoundCommand.iterator().next();
			driverCommand.accept(this);
		}
	};
	
	public void visitOperateToCommand(OperateToCommand operateToCommand) {
		this.counterOperateToCommand++;
	};
	public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
		this.counterSetPositionCommand++;
	};

	public int getOperateToCommandCount() {
		return this.counterOperateToCommand;
	};

	public int getSetPositionCommandCount() {
		return this.counterSetPositionCommand;
	};


	public int sumAll() {
		return  this.counterOperateToCommand + this.counterSetPositionCommand;
	}
}
