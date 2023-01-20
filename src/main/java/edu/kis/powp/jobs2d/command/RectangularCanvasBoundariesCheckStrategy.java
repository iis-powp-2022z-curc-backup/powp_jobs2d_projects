package edu.kis.powp.jobs2d.command;

import static java.lang.Math.abs;

public class RectangularCanvasBoundariesCheckStrategy implements ICanvasBoundariesCheckStrategy {
	@Override
	public boolean checkExceedingBoundaries(ICanvas canvas, int X, int Y) {
		return (abs(X) < canvas.getHeight() / 2) || (abs(Y) < canvas.getWidth() / 2);
	}
}
