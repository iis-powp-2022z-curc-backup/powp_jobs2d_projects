package edu.kis.powp.jobs2d.command.imports;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class ImportTxtCommand implements ImportCommandInterface {

	@Override
	public List<DriverCommand> importCommand(String fileContent) {
		List<DriverCommand> commandList = new ArrayList<>();

		Scanner scanner = new Scanner(fileContent.toString());
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			logger.info(line);

			String[] splittedLine = line.split(" ");
			String command = splittedLine[0];
			int x = Integer.valueOf(splittedLine[1]);
			int y = Integer.valueOf(splittedLine[2]);

			if (command.equals("OperateTo"))
				commandList.add(new OperateToCommand(x, y));
			else if (command.equals("SetPosition"))
				commandList.add(new SetPositionCommand(x, y));
		}

		return commandList;
	}
}
