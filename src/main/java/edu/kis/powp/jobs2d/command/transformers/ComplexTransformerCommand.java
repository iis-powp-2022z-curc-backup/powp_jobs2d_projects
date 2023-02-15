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
        ComplexTransformerVisitor visitor = new ComplexTransformerVisitor();
        for (TransformerCommand command : transformerCommands){
            transformedCoords = command.execute(transformedCoords);
            command.accept(visitor);
        }

        this.logger.info("Rotacje wykonano " + visitor.getCounterRotateCommand()  + " razy");
        this.logger.info("Przeniesienie wykonano " + visitor.getCounterTranslateCommand()  + " razy");
        this.logger.info("Skalowanie " + visitor.getCounterScaleCommand()  + " razy");

        return transformedCoords;
    }

    @Override
    public void accept(TransformerVisitor visitor) {}
}