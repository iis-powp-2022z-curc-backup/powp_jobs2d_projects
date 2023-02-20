package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ComplexCommand class to execute any command chain.
 */
public class ComplexCommand implements ICompoundCommand {
	private final List<DriverCommand> driverCommandList;
	private final String name;

	public ComplexCommand(List<DriverCommand> driverCommandList, String name) {
		if (driverCommandList == null) {
			this.driverCommandList = new LinkedList<>();
		} else {
			this.driverCommandList = driverCommandList;
		}
		this.name = name;
	}

	public static ComplexCommandBuilder builder() {
		return new ComplexCommandBuilder();
	}

	@Override
	public void execute(Job2dDriver driver) {
		driverCommandList.forEach(driverCommand -> driverCommand.execute(driver));
	}

	@Override
	public Iterator<DriverCommand> iterator() {
		return driverCommandList.iterator();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Object clone() {
		List<DriverCommand> commandsListToClone = new LinkedList<>();
		for (DriverCommand driverCommand : driverCommandList) {
			commandsListToClone.add((DriverCommand) driverCommand.clone());
		}
		return new ComplexCommand(commandsListToClone, this.name);
	}

	@Override
	public void accept(TransformerCommandVisitorInterface transformerCommandVisitor) {

	}

	@Override
	public int getPosX() {
		return 0;
	}

	@Override
	public int getPosY() {
		return 0;
	}

	public static class ComplexCommandBuilder {
		private final List<DriverCommand> commands = new LinkedList<>();
		private String name;

		public ComplexCommandBuilder addCommand(final DriverCommand command) {
			this.commands.add(command);
			return this;
		}

		public ComplexCommandBuilder setName(final String name) {
			this.name = name;
			return this;
		}

		public ComplexCommand build() {
			return new ComplexCommand(commands, name);
		}
	}
}
