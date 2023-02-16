package edu.kis.powp.jobs2d.command.transformers;

import java.util.List;
import java.util.logging.Logger;

public class ComplexTransformerCommand implements TransformerCommand {
    private final List<TransformerCommand> transformerCommands;
    private Logger logger = Logger.getLogger("global");
    public ComplexTransformerCommand(List<TransformerCommand> transformerCommands) {
        this.transformerCommands = transformerCommands;
    }

    @Override
    public TransformedCoords execute(TransformedCoords coords) {
        TransformedCoords transformedCoords = coords;

        for (TransformerCommand command : transformerCommands){
            transformedCoords = command.execute(transformedCoords);
        }

        return transformedCoords;
    }

}