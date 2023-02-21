package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.TransformStrategyInterface;

    public class FlipStrategy implements TransformStrategyInterface {

        public FlipStrategy() {}

        public OperateToCommand execute(OperateToCommand operateToCommand) {
            Integer[]  newCoordinates = setNewCoordinates(operateToCommand);

            return new OperateToCommand(newCoordinates[0], newCoordinates[1]);
        }

        public SetPositionCommand execute(SetPositionCommand setPositionCommand) {
            Integer[] newCoordinates = setNewCoordinates(setPositionCommand);

            return new SetPositionCommand(newCoordinates[0], newCoordinates[1]);
        }

        private Integer[] setNewCoordinates(DriverCommand command) {
            Integer[] coordinates = new Integer[2];

            coordinates[0] = -(command.getPosX());
            coordinates[1] = command.getPosY();

            return coordinates;
        }
    }


