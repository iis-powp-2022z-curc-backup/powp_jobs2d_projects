package edu.kis.powp.jobs2d.command.transformers;

public class TrapezeTransitionCommand implements TransformerCommand {

        private final double x;
        private final double y;

        public TrapezeTransitionCommand(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public TransformedCoords execute(TransformedCoords coords) {
            return coords.transumeByTrapeze(x, y);
        }


}
