package edu.kis.powp.jobs2d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.*;
import edu.kis.powp.jobs2d.events.commands.SubscribeCommandBoundariesCheckVisitor;
import edu.kis.powp.jobs2d.command.CommandsCounterDriverCommandVisitor;
import edu.kis.powp.jobs2d.features.MainFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.commands.SubscribeCommandsCounterVisitor;
import edu.kis.powp.jobs2d.command.manager.DriverInfoUpdateObserver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.command.transformers.*;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.events.SelectLoadSecretCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigure2OptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;


public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
	}

	/**
	 * Setup test using driver commands in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();

		Publisher publisher = manager.getChangePublisher();

		CommandsCounterDriverCommandVisitor commandsCounterVisitor = new CommandsCounterDriverCommandVisitor();
		publisher.addSubscriber(new SubscribeCommandsCounterVisitor(commandsCounterVisitor, manager));

		IRectangularCanvas canvas = new JPanelCanvasAdapter(application.getFreePanel());
		ICanvasBoundariesCheckStrategy strategy = new RectangularCanvasBoundariesCheckStrategy(canvas);
		CommandBoundariesCheckDriverCommandVisitor commandBoundariesCheckVisitor = new CommandBoundariesCheckDriverCommandVisitor(strategy);
		publisher.addSubscriber(new SubscribeCommandBoundariesCheckVisitor(commandBoundariesCheckVisitor, manager));

		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());
		application.addTest("Load rectangle command", new SelectRectangleCommandOptionListener());
		application.addTest("Load exceeding basic canvas command", new SelectExceedingBasicCanvasCommandOptionListener());

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

		TransformerCommand TranslateCommand = new TranslateCommand(10,10);
		application.addTest("Translate",
				new SelectTransformCommandOptionListener(
						DriverFeature.getDriverManager(), TranslateCommand, "Translate"));

		TransformerCommand ScaleCommand = new ScaleCommand(1.5,1.2);
		application.addTest("Scale",
				new SelectTransformCommandOptionListener(
						DriverFeature.getDriverManager(), ScaleCommand, "Scale"));

		TransformerCommand RotateCommand = new RotateCommand(270);
		application.addTest("Rotate",
				new SelectTransformCommandOptionListener(
						DriverFeature.getDriverManager(), RotateCommand, "Rotate"));

		TransformerCommand ReflectionCommand = new ReflectionCommand();
		application.addTest("Flip", new SelectTransformCommandOptionListener(
				DriverFeature.getDriverManager(), ReflectionCommand, "Flip"
		));

		List<TransformerCommand> complexTransformerCommands = new ArrayList<>();
		complexTransformerCommands.add(new TranslateCommand(50, 50));
		complexTransformerCommands.add(new ScaleCommand(0.5, 0.5));
		complexTransformerCommands.add(new RotateCommand(180));
		complexTransformerCommands.add(new ReflectionCommand());
		ComplexTransformerCommand complexTransformerCommand =
				new ComplexTransformerCommand(complexTransformerCommands);
		application.addTest("Complex transform",
				new SelectTransformCommandOptionListener(
						DriverFeature.getDriverManager(), complexTransformerCommand, "Transform"));
		DriverFeature.updateDriverInfo();
	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DriverInfoUpdateObserver driverObserver = new DriverInfoUpdateObserver();
		DriverFeature.getDriverManager().getChangePublisher().addSubscriber(driverObserver);

		Job2dDriver loggerDriver = new LoggerDriver();

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver1 = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		Job2dDriver driver2 = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Line Simulator", driver1);
		DriverFeature.getDriverManager().setCurrentDriver(driver1);

		DriverFeature.addDriver("Special line Simulator", driver2);

		DriverComposite compositeLoggerSpecialLineComboDriver = new DriverComposite();
		compositeLoggerSpecialLineComboDriver.add(loggerDriver);
		compositeLoggerSpecialLineComboDriver.add(driver1);

		DriverComposite compositeDoubleLineComboDriver = new DriverComposite();
		compositeDoubleLineComboDriver.add(driver1);
		compositeDoubleLineComboDriver.add(driver2);

		DriverFeature.addDriver("Logger and line driver combo",compositeLoggerSpecialLineComboDriver);
		DriverFeature.addDriver("Double line driver combo",compositeDoubleLineComboDriver);

		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);

	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	private static void setupMouseDrawer(Application app) {
		DrawLineMouseListener.activate(app.getFreePanel(), DriverFeature.getDriverManager());
	}

	/**
	 * Setup menu option for start/stop commands recording
	 *
	 * @param application Application context
	 */
	private static void setupRecording(Application application) {
		application.addComponentMenu(RecordingFeature.class, "Recording");
		application.addComponentMenuElement(RecordingFeature.class, "Start recording",
				(ActionEvent e) -> RecordingFeature.getRecordingManager().startRecording());
		application.addComponentMenuElement(RecordingFeature.class, "Stop recording",
				(ActionEvent e) -> RecordingFeature.getRecordingManager().stopRecording());
		application.addComponentMenuElement(RecordingFeature.class, "Load recording",
				(ActionEvent e) -> RecordingFeature.getRecordingManager().loadRecording());
		application.addComponentMenuElement(RecordingFeature.class, "Clear recording",
				(ActionEvent e) -> RecordingFeature.getRecordingManager().clearRecording());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				CommandsFeature.setupCommandManager();
				DriverFeature.setupDriverPlugin(app);
				RecordingFeature.setupRecordingPlugin();
				MainFeature.setupFeaturePlugin(app);

				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupRecording(app);
				setupWindows(app);

				app.setVisibility(true);
				setupMouseDrawer(app);
			}
		});
	}
}
