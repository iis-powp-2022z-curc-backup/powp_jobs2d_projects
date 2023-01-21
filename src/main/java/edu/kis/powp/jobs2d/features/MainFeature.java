package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFeature {
	public static void setupFeaturePlugin(Application application) {
		application.addComponentMenu(MainFeature.class, "Feature");
		addFeatureCheckBox(application, "Logger", new LoggerDriver());
	}

	public static void addFeatureCheckBox(Application application, String label, Job2dDriver driver) {
		application.addComponentMenuElementWithCheckBox(MainFeature.class, label, new FeatureListener(driver), false);
	}

	static class FeatureListener implements ActionListener {
		private boolean featureActive = false;
		private final Job2dDriver driver;

		public FeatureListener(Job2dDriver driver) {
			this.driver = driver;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			featureActive = !featureActive;
			if (featureActive) {
				DriverFeature.getDriverManager().addFeatureDriver(driver);
			} else {
				DriverFeature.getDriverManager().removeFeatureDriver(driver);
			}
		}
	}
}
