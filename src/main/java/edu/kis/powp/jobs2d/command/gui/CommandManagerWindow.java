package edu.kis.powp.jobs2d.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import java.util.ArrayList;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.TransformerCommandVisitor;
import edu.kis.powp.jobs2d.command.TransformerCommandVisitorInterface;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.transformers.TransformerCommand;
import edu.kis.powp.jobs2d.command.transformers.TranslateCommand;
import edu.kis.powp.jobs2d.command.transformers.TranslateStrategy;
import edu.kis.powp.jobs2d.command.transformers.TrapezeTransitionStrategy;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private List<Subscriber> observerList;
	private boolean observersDeleted = false;
	private final CommandManager commandManager;

	private final JTextArea currentCommandField;
	private final JPanel currentCommandPreview;
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

		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addActionListener((ActionEvent e) -> this.translateCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnTranslate, c);

		JButton scaleFlip = new JButton("Scale");
		scaleFlip.addActionListener((ActionEvent e) -> this.scaleCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(scaleFlip, c);

		JButton rotateFlip = new JButton("Rotate");
		rotateFlip.addActionListener((ActionEvent e) -> this.rotateCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(rotateFlip, c);

		JButton btnFlip = new JButton("Flip");
		btnFlip.addActionListener((ActionEvent e) -> this.flipCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlip, c);

		JButton btnPerspectiveTransformation = new JButton("Perspective Transformation");
		btnPerspectiveTransformation.addActionListener((ActionEvent e) -> this.perspectiveTransformationCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnPerspectiveTransformation, c);

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

		DrawPanelController drawPanelController = new DrawPanelController();
		drawPanelController.initialize(currentCommandPreview);
		previewAdapter = new LineDriverAdapter(drawPanelController , LineFactory.getBasicLine(), "Preview");
	}

	private void runCommand() {


		commandManager.runCommand();

		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commandManager.getCurrentCommand());
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
		updateCurrentCommandPreview();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	private void translateCommand(){
		ComplexCommand currentCommand = (ComplexCommand) commandManager.getCurrentCommand();

		TranslateStrategy translateStrategy = new TranslateStrategy(5, 5);

		TransformerCommandVisitorInterface translateCommandVisitor = new TransformerCommandVisitor(translateStrategy);

		Iterator<DriverCommand> iterator = currentCommand.iterator();

				while(iterator.hasNext()) {
					iterator.next().accept(translateCommandVisitor);

				}
				
		commandManager.setCurrentCommand(translateCommandVisitor.createComplexCommand());

	}

	private void scaleCommand(){
		DriverCommand driverCommand = commandManager.getCurrentCommand();
//		String commandName = commandManager.getCurrentCommand().toString();
//		System.out.printf("scale command to %s %n", commandName);
	}

	private void rotateCommand(){
		DriverCommand driverCommand = commandManager.getCurrentCommand();
//		String commandName = commandManager.getCurrentCommand().toString();
//		System.out.printf("rotate command to %s %n", commandName);
	}

	private void flipCommand(){
		DriverCommand driverCommand = commandManager.getCurrentCommand();
//		String commandName = commandManager.getCurrentCommand().toString();
//		System.out.printf("flip command to %s %n", commandName);
	}

	private void perspectiveTransformationCommand(){
		ComplexCommand currentCommand = (ComplexCommand) commandManager.getCurrentCommand();

		Iterator<DriverCommand> iterator = currentCommand.iterator();

		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		while(iterator.hasNext()) {
			DriverCommand item = iterator.next();
			if(item.getPosY() < minY)
				minY = item.getPosY();

			if(item.getPosY() > maxY)
				maxY = item.getPosY();
		}


		TrapezeTransitionStrategy trapezeTransitionStrategy = new TrapezeTransitionStrategy(minY, maxY);
		TransformerCommandVisitorInterface translateCommandVisitor = new TransformerCommandVisitor(trapezeTransitionStrategy);

		iterator = currentCommand.iterator();

		while(iterator.hasNext()) {
			iterator.next().accept(translateCommandVisitor);
		}

		commandManager.setCurrentCommand(translateCommandVisitor.createComplexCommand());
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