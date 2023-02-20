package edu.kis.powp.jobs2d.command.transformers;

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
            double cos = Math.cos(Math.toRadians(angle));
            double sin = Math.sin(Math.toRadians(angle));

            int finalX = (int) (operateToCommand.getPosX() * cos - operateToCommand.getPosY() * sin);
            int finalY = (int) (operateToCommand.getPosX() * sin + operateToCommand.getPosY() * cos);

            return new OperateToCommand(finalX, finalY);
        }

        public SetPositionCommand execute(SetPositionCommand setPositionCommand) {
            double cos = Math.cos(Math.toRadians(angle));
            double sin = Math.sin(Math.toRadians(angle));

            int finalX = (int) (setPositionCommand.getPosX() * cos - setPositionCommand.getPosY() * sin);
            int finalY = (int) (setPositionCommand.getPosX() * sin + setPositionCommand.getPosY() * cos);

            return new SetPositionCommand(finalX, finalY);
        }
    }

