package edu.kis.powp.jobs2d.command;

public interface ICanvasBoundariesCheckStrategy {
	boolean checkExceedingBoundaries(ICanvas canvas, int X, int Y);
}
