package edu.kis.powp.jobs2d.command;

public class RectangularCanvasCheckStrategy implements ICommandBoundariesCheckStrategy {
	@Override
	public boolean checkExceedingBoundaries(ICanvas canvas, int X, int Y) {
		return (X < canvas.getHeight() / 2) || (Y < canvas.getWidth() / 2);
	}
}
