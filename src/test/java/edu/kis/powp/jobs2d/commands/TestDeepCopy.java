package edu.kis.powp.jobs2d.commands;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TestDeepCopy {

    public static void main(String[] args) {
        testDeepCopyOfSimpleCommand();
        testDeepCopyOfComplexCommand();
    }

    private static void testDeepCopyOfSimpleCommand() {
        System.out.println("Test that simple command (\"OperateToCommand\") can be copied");

        final MockDriver firstDriver = new MockDriver();
        final MockDriver secondDriver = new MockDriver();
        final DriverCommand command = new OperateToCommand(1,2);

        final DriverCommand commandCopy = (DriverCommand)command.clone();

        assert(commandCopy instanceof OperateToCommand);
        assert(commandCopy != command);

        command.execute(firstDriver);
        commandCopy.execute(secondDriver);
        assert(firstDriver.getList().size() == secondDriver.getList().size());
        assert(firstDriver.getList().equals(secondDriver.getList()));
    }

    private static void testDeepCopyOfComplexCommand() {
        System.out.println("Test deep copy of complex command");

        final MockDriver driver1 = new MockDriver();
        final MockDriver driver2 = new MockDriver();
        final String name = "UniqueName";

        final ComplexCommand command = new ComplexCommand(Stream.of(
                new OperateToCommand(1,2),
                new SetPositionCommand(3,4),
                new OperateToCommand(5,6),
                new ComplexCommand(Stream.of(
                        new OperateToCommand(1,2),
                        new SetPositionCommand(3,4)
                ).collect(toList()))
        ).collect(toList()));

        final ICompoundCommand commandCopy = (ICompoundCommand) command.clone();

        assert(commandCopy != null);
        assert(commandCopy != command);
        assert(command.toString().equals(commandCopy.toString()));

        command.execute(driver1);
        commandCopy.execute(driver2);
        assert(driver1.getList().size() == driver2.getList().size());
        assert(driver1.getList().equals(driver2.getList()));

        final List<DriverCommand> list = getAllObjects(commandCopy);
        assert(getAllObjects(command).stream().noneMatch(list::contains));
    }

    private static List<DriverCommand> getAllObjects(DriverCommand command) {
        final List<DriverCommand> list = new ArrayList<>();
        list.add(command);
        if(command instanceof ICompoundCommand) {
            ((ICompoundCommand) command).iterator().forEachRemaining(x -> list.addAll(getAllObjects(x)));
        }
        return list;
    }

    private static class MockDriver implements Job2dDriver {
        private final List<Integer> movements = new ArrayList<>();
        @Override
        public void setPosition(int x, int y) {
            movements.add(x);
            movements.add(y);
        }
        @Override
        public void operateTo(int x, int y) {
            setPosition(x,y);
        }
        public List<Integer> getList() {
            return movements;
        }
    }
}