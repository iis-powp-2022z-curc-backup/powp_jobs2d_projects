package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.adapter.DistanceDriverAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceDriverActionListener implements ActionListener {

    private DistanceDriverAdapter adapter;

    public DistanceDriverActionListener(DistanceDriverAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adapter.resetDistance();
    }
}
