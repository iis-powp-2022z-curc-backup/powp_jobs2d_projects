package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.TransformStrategyInterface;

public class TrapezeTransitionStrategy implements TransformStrategyInterface {

    private final int height;

    public TrapezeTransitionStrategy(int minY, int maxY){
        this.height = maxY - minY;
    }

    private int calculateNewPosX(int posX, int posY, int height){
        float factor = (1 / (float)(posY + height)) * height;

        return (int)(posX / factor);
    }

    @Override
    public OperateToCommand execute(OperateToCommand operateToCommand) {
        int newPosX = calculateNewPosX(operateToCommand.getPosX(), operateToCommand.getPosY(), this.height);
        return new OperateToCommand(newPosX, operateToCommand.getPosY());
    }

    @Override
    public SetPositionCommand execute(SetPositionCommand setPositionCommand) {
        int newPosX = calculateNewPosX(setPositionCommand.getPosX(), setPositionCommand.getPosY(), this.height);
        return new SetPositionCommand(newPosX, setPositionCommand.getPosY());
    }
}
