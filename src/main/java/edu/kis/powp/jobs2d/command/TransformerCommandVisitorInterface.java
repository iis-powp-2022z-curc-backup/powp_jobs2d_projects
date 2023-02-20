package edu.kis.powp.jobs2d.command;


import java.util.List;

public interface TransformerCommandVisitorInterface {

        public void run(OperateToCommand operateToCommand);
        public void run(SetPositionCommand setPositionCommand);

        public List<DriverCommand> getDriverCommandList();

        public ComplexCommand createComplexCommand();

    }

