package edu.kis.powp.jobs2d.command.transformers;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.TransformStrategyInterface;

    public class RotateStrategy implements TransformStrategyInterface {

        private int x = 0;
        private int y = 0;

        private double angle = 0;

        public RotateStrategy(int x, int y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        public OperateToCommand execute(OperateToCommand operateToCommand) {
            Integer[] newCoordinates = setNewCoordinates(operateToCommand);

            return new OperateToCommand(newCoordinates[0], newCoordinates[1]);
        }

        public SetPositionCommand execute(SetPositionCommand setPositionCommand) {
            Integer[] newCoordinates = setNewCoordinates(setPositionCommand);

            return new SetPositionCommand(newCoordinates[0], newCoordinates[1]);
        }

        private Integer[] setNewCoordinates(DriverCommand command) {
            Integer[] coordinates = new Integer[2];

            double cos = Math.cos(Math.toRadians(angle));
            double sin = Math.sin(Math.toRadians(angle));

            coordinates[0] = (int) (command.getPosX() * cos - command.getPosY() * sin);
            coordinates[1] = (int) (command.getPosX() * sin + command.getPosY() * cos);

            return coordinates;
        }
    }

