package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * DriverCommand interface.
 */
public interface DriverCommand extends Cloneable {

	/**
	 * Execute command on driver.
	 * 
	 * @param driver driver.
	 */
	public void execute(Job2dDriver driver);
	public Object clone();

	public void accept(DriverCommandVisitor driverCommandVisitor);

	public void accept(TransformerCommandVisitorInterface transformerCommandVisitor);

	public int getPosX();
	public int getPosY();

}
