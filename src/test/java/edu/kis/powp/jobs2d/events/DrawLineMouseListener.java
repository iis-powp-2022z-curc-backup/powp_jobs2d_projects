package edu.kis.powp.jobs2d.events;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class DrawLineMouseListener extends MouseInputAdapter {

	private static DriverManager driverManager;
	private static Logger logger;
	private static int xOffset, yOffset;

	public static void activate(JPanel freePanel, DriverManager driverManager) {
		DrawLineMouseListener.driverManager = driverManager;

		Dimension size = freePanel.getSize();
		xOffset = size.width / 2;
		yOffset = size.height / 2;

		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		freePanel.addMouseListener(new DrawLineMouseListener());
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Job2dDriver driverInUse = driverManager.getCurrentDriver();

		int xPos = event.getX() - xOffset;
		int yPos = event.getY() - yOffset;

		switch (event.getButton()) {
		case MouseEvent.BUTTON1:
			driverInUse.operateTo(xPos, yPos);
			logger.info("Line drew to (" + xPos + "," + yPos + ")");
			break;
		case MouseEvent.BUTTON3:
			driverInUse.setPosition(xPos, yPos);
			logger.info("Device head position set to (" + xPos + "," + yPos + ")");
			break;
		default:
			break;
		}
	}
}
