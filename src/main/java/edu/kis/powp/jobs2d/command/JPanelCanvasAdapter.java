package edu.kis.powp.jobs2d.command;

import javax.swing.*;

public class JPanelCanvasAdapter implements IRectangularCanvas {
	private final JPanel panel;

	public JPanelCanvasAdapter(JPanel panel) {
		this.panel = panel;
	}

	@Override
	public int getWidth() {
		return panel.getWidth();
	}

	@Override
	public int getHeight() {
		return panel.getHeight();
	}
}
