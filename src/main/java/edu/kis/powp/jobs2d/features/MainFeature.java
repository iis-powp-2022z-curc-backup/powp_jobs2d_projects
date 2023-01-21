package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.LoggerDriver;

import java.awt.event.ActionListener;

public class MainFeature {
	public static boolean loggerActive = false;
	private static final LoggerDriver loggerDriver = new LoggerDriver();

	public static void setupFeaturePlugin(Application application) {
		application.addComponentMenu(MainFeature.class, "Feature");
		application.addComponentMenuElementWithCheckBox(MainFeature.class, "Logger", getLoggerActionListener(), loggerActive);
	}

	private static ActionListener getLoggerActionListener() {
		return e -> {
			loggerActive = !loggerActive;
			if (loggerActive) {
				DriverFeature.getDriverManager().addFeatureDriver(loggerDriver);
			} else {
				DriverFeature.getDriverManager().removeFeatureDriver(loggerDriver);
			}
		};
	}
}
