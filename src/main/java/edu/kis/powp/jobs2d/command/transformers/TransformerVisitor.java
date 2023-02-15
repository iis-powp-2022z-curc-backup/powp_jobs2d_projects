package edu.kis.powp.jobs2d.command.transformers;

public interface TransformerVisitor {
    void visitRotateCommand(RotateCommand rotate);
    void visitScaleCommand(ScaleCommand scale);
    void visitTranslateCommand(TranslateCommand translate);
}
