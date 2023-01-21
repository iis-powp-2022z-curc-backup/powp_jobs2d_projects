package edu.kis.powp.jobs2d.command;

import static java.lang.Math.abs;

public class RectangularCanvasBoundariesCheckStrategy implements ICanvasBoundariesCheckStrategy {
	private final ICanvas canvas;

	public RectangularCanvasBoundariesCheckStrategy(ICanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean checkExceedingBoundaries(int X, int Y) {
		return (abs(X) > this.canvas.getHeight() / 2) || (abs(Y) > this.canvas.getWidth() / 2);
	}
}
