package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.TransformStrategyInterface;

public class ScaleStrategy implements TransformStrategyInterface {

    private int x = 0;
    private int y = 0;

    public ScaleStrategy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public OperateToCommand execute(OperateToCommand operateToCommand) {
        return new OperateToCommand(operateToCommand.getPosX() * x, operateToCommand.getPosY() * y);
    }

    public SetPositionCommand execute(SetPositionCommand setPositionCommand) {
        return new SetPositionCommand(setPositionCommand.getPosX() * x, setPositionCommand.getPosY() * y);
    }
}
