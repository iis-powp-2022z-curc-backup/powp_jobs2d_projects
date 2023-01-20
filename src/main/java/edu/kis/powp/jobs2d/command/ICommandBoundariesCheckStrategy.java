package edu.kis.powp.jobs2d.command;

public interface ICommandBoundariesCheckStrategy {
	boolean checkExceedingBoundaries(ICanvas canvas, int X, int Y);
}
