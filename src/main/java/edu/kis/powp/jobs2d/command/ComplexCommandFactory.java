package edu.kis.powp.jobs2d.command;

public class ComplexCommandFactory {
	public static ComplexCommand createRectangle() {
		return ComplexCommand.builder().addCommand(new SetPositionCommand(-100, -100)).addCommand(
				new OperateToCommand(-100, -100)).addCommand(new OperateToCommand(-100, 100)).addCommand(
				new OperateToCommand(200, 100)).addCommand(new OperateToCommand(200, -100)).addCommand(
				new OperateToCommand(-100, -100)).setName("RectangleCommand").build();
	}

	public static ComplexCommand createTopSecret() {
		return ComplexCommand.builder()
				.addCommand(new SetPositionCommand(-20, -50))
				.addCommand(new OperateToCommand(-20, -50))
				.addCommand(new SetPositionCommand(-20, -40))
				.addCommand(new OperateToCommand(-20, 50))
				.addCommand(new SetPositionCommand(0, -50))
				.addCommand(new OperateToCommand(0, -50))
				.addCommand(new SetPositionCommand(0, -40))
				.addCommand(new OperateToCommand(0, 50))
				.addCommand(new SetPositionCommand(70, -50))
				.addCommand(new OperateToCommand(20, -50))
				.addCommand(new OperateToCommand(20, 0))
				.addCommand(new OperateToCommand(70, 0))
				.addCommand(new OperateToCommand(70, 50))
				.addCommand(new OperateToCommand(20, 50))
				.setName("TopSecretCommand")
				.build();
	}
}
