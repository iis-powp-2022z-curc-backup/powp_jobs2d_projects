package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.List;

public class TransformerCommandVisitor implements TransformerCommandVisitorInterface{

    private final List<DriverCommand> driverCommandList = new ArrayList<>();
    private TransformStrategyInterface strategy;

    public TransformerCommandVisitor(TransformStrategyInterface strategy) {
        this.strategy = strategy;
    }

    public void run(OperateToCommand operateToCommand) {
        driverCommandList.add(strategy.execute(operateToCommand));
    }

    public void run(SetPositionCommand setPositionCommand) {
        driverCommandList.add(strategy.execute(setPositionCommand));
    }

    public ComplexCommand createComplexCommand() {
        return new ComplexCommand(driverCommandList, "Transformation command");
    }


    public List<DriverCommand> getDriverCommandList() { return this.driverCommandList; };
}
