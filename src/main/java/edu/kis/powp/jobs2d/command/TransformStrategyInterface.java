package edu.kis.powp.jobs2d.command;

public interface TransformStrategyInterface {
    public OperateToCommand execute(OperateToCommand operateToCommand);

    public SetPositionCommand execute(SetPositionCommand setPositionCommand);
}
