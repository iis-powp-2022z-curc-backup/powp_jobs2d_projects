package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.TransformStrategyInterface;

    public class FlipStrategy implements TransformStrategyInterface {

        public FlipStrategy() {}

        public OperateToCommand execute(OperateToCommand operateToCommand) {
            int finalX = -(operateToCommand.getPosX());
            int finalY = operateToCommand.getPosY();

            return new OperateToCommand(finalX, finalY);
        }

        public SetPositionCommand execute(SetPositionCommand setPositionCommand) {

            int finalX = -(setPositionCommand.getPosX());
            int finalY = setPositionCommand.getPosY();

            return new SetPositionCommand(finalX, finalY);
        }
    }


