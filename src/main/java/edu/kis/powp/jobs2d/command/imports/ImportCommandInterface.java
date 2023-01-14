package edu.kis.powp.jobs2d.command.imports;

import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;

public interface ImportCommandInterface {

	List<DriverCommand> importCommand(String text);
}
