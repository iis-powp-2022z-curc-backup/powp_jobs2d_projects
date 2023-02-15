package edu.kis.powp.jobs2d.command.transformers;

public class ComplexTransformerVisitor implements TransformerVisitor{
    private int counterRotateCommand = 0;
    private int counterScaleCommand = 0;
    private int counterTranslateCommand = 0;


    @Override
    public void visitRotateCommand(RotateCommand rotate) {
        counterRotateCommand++;
    }

    @Override
    public void visitScaleCommand(ScaleCommand scale) {
        counterScaleCommand++;
    }

    @Override
    public void visitTranslateCommand(TranslateCommand translate) {
        counterTranslateCommand++;
    }

    public int getCounterRotateCommand() {
        return counterRotateCommand;
    }

    public int getCounterScaleCommand() {
        return counterScaleCommand;
    }

    public int getCounterTranslateCommand() {
        return counterTranslateCommand;
    }
}
