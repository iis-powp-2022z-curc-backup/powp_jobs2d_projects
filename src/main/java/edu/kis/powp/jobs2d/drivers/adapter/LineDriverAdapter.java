package edu.kis.powp.jobs2d.drivers.adapter;

import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Line adapter - Job2dDriver with DrawPanelController object.
 */
public class LineDriverAdapter implements Job2dDriver {
	private ILine line;
	private int startX = 0, startY = 0;
	private String name;

	private DrawPanelController drawController;
	Logger logger = Logger.getLogger("global");
	private static int totalDistance = 0;

	public LineDriverAdapter(DrawPanelController drawController, ILine line, String name) {
		super();
		this.drawController = drawController;
		this.line = line;
		this.name = name;
	}

	@Override
	public void setPosition(int x, int y) {
		this.startX = x;
		this.startY = y;
	}

	@Override
	public void operateTo(int x, int y) {
		int distance = (int) Math.sqrt(Math.pow(this.startX -x, 2)+Math.pow(this.startY -y, 2));
		logger.info("total distance: " + (LineDriverAdapter.totalDistance += distance));
		line.setStartCoordinates(this.startX, this.startY);
		this.setPosition(x, y);
		line.setEndCoordinates(x, y);

		drawController.drawLine(line);
	}

	@Override
	public String toString() {
		return "2d device simulator - " + name;
	}
}
