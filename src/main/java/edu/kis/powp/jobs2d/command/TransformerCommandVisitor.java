package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransformerCommandVisitor implements DriverCommandVisitor{

    private final List<DriverCommand> driverCommandList = new ArrayList<>();
    private TransformStrategyInterface strategy;

    public TransformerCommandVisitor(TransformStrategyInterface strategy) {
        this.strategy = strategy;
    }

    public ComplexCommand createComplexCommand() {
        return new ComplexCommand(driverCommandList, "Transformation command");
    }

    @Override
    public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
        Iterator<DriverCommand> iterator = iCompoundCommand.iterator();

        while(iterator.hasNext()) {
            iterator.next().accept(this);
        }

    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        driverCommandList.add(strategy.execute(operateToCommand));
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        driverCommandList.add(strategy.execute(setPositionCommand));
    }
}
