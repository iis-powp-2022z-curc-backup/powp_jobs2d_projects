package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.transformers.ComplexTransformerCommand;
import edu.kis.powp.jobs2d.command.transformers.TransformerCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.VisitableDriver;
import edu.kis.powp.jobs2d.drivers.adapter.TransformerDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectTransformCommandOptionListener implements ActionListener {

    private final DriverManager driverManager;
    private final TransformerCommand transformerCommands;
    private final String name;

    public SelectTransformCommandOptionListener(DriverManager driverManager, TransformerCommand commands, String name) {
        this.driverManager = driverManager;
        this.transformerCommands = commands;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.setCurrentDriver((VisitableDriver) new TransformerDriver(driverManager.getCurrentDriver(), transformerCommands, name));
    }
}