package edu.kis.powp.jobs2d.command.transformers;

public class ReflectionCommand implements TransformerCommand {

        public ReflectionCommand() {}

        @Override
        public TransformedCoords execute(TransformedCoords coords) {
            return coords.flip();
        }

    }

