package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.*;

import java.util.ArrayList;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.command.imports.ImportCommandFactory;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private List<Subscriber> observerList;
	private boolean observersDeleted = false;
	private final CommandManager commandManager;

	private final JTextArea currentCommandField;
	private JTextArea importCommandField;	private final JPanel currentCommandPreview;

	private String observerListString;
	private final JTextArea observerListField;

	private Job2dDriver previewAdapter;

	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(CommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(800, 800);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		currentCommandPreview = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 3;
		content.add(currentCommandPreview, c);

		importCommandField = new JTextArea("");
		importCommandField.setEditable(true);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(importCommandField, c);

		JButton btnImportCommand = new JButton("Import command");
		btnImportCommand.addActionListener((ActionEvent e) -> this.importCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnImportCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers(btnClearObservers));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearObservers, c);

		JButton btnRunCommand = new JButton("Run command");
		btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRunCommand, c);
	}

	private void runCommand() {
		commandManager.runCommand();
		DrawPanelController drawPanelController = new DrawPanelController();
		drawPanelController.initialize(currentCommandPreview);
		previewAdapter = new LineDriverAdapter(drawPanelController , LineFactory.getBasicLine(), "Preview");
	}

	private void importCommand() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.info(path);

			try {
				String[] pathTokenized = path.split("\\.");
				String extension = pathTokenized[pathTokenized.length-1];
				List<DriverCommand> commandList = new ImportCommandFactory()
						.importer(extension)
						.importCommand(getFileContent(path));
				commandManager.setCurrentCommand(new ComplexCommand(commandList, path));
			} catch (Exception ex) {
				logger.info("Exception in command importing. " + ex);
			}

			updateCurrentCommandField();
		}
	}

	private String getFileContent(String path) {
		StringBuilder fileContent = new StringBuilder();
		try {
			Stream<String> stream = Files.lines(Paths.get(path));
			stream.forEach(s -> fileContent.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContent.toString();
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
		updateCurrentCommandPreview();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}


	public void deleteObservers(JButton resetButton) {
		if (observersDeleted) {
			resetObservers(resetButton);
		} else {
			this.observerList = new ArrayList<Subscriber>(this.commandManager.getChangePublisher().getSubscribers());
			commandManager.getChangePublisher().clearObservers();
			this.updateObserverListField();
			observersDeleted = true;
			resetButton.setText("Reset observers");
		}
	}

	public void resetObservers(JButton deleteButton) {
		commandManager.getChangePublisher().clearObservers();
		observersDeleted = false;
		deleteButton.setText("Delete observers");

		if (observerList != null) {
			for (Subscriber subscriber : observerList) {
				this.commandManager.getChangePublisher().addSubscriber(subscriber);
			}
		}
		this.updateObserverListField();
	}

	public void updateCurrentCommandPreview() {
		clearCurrentCommandPreview();
		DriverCommand command = commandManager.getCurrentCommand();
		if (command != null) {
			command.execute(previewAdapter);
		}
	}

	private void clearCurrentCommandPreview() {
		currentCommandPreview.getGraphics().clearRect(0, 0, getWidth(), getHeight());
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		this.setVisible(!this.isVisible());
	}

}