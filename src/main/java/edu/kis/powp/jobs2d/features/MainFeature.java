package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

public class MainFeature {
	public static boolean loggerActive = false;

	public static void setupFeaturePlugin(Application application) {
		application.addComponentMenu(MainFeature.class, "Feature");
		application.addComponentMenuElementWithCheckBox(MainFeature.class, "Logger", e -> loggerActive = !loggerActive,
				loggerActive);
	}
}
