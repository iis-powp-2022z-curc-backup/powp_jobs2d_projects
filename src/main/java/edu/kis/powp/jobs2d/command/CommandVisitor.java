package edu.kis.powp.jobs2d.command;

public class CommandVisitor implements Visitor {
    private int counterICompoundCommand = 0;
    private int counterOperateToCommand = 0;
    private int counterSetPositionCommand = 0;


    public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
        this.counterICompoundCommand++;
    };
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        this.counterOperateToCommand++;
    };
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        this.counterSetPositionCommand++;
    };


    public int sumAll() {
        return this.counterICompoundCommand + this.counterOperateToCommand + this.counterSetPositionCommand;
    }
}
