package ecosystem.gui;

import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController {
    private Environment environment;
    private ControlPanel controlPanel;
    private SimulationView simulationView;
    private SimulationEngine engine;
    private Timer timer;


    public SimulationController(SimulationView view, ControlPanel controlPanel, Environment environment, SimulationEngine engine) {
        this.environment = environment;
        this.simulationView = view;
        this.controlPanel = controlPanel;
        this.engine = engine;

         timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.Tick();
            }
        });

         connectingButtons();
    }

    public void connectingButtons(){
        controlPanel.getTickButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.Tick();
            }
        });

        controlPanel.getAddEntityButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEntity(simulationView ,environment);
            }
        });

        controlPanel.getRunButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        controlPanel.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        controlPanel.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                simulationView.dispose();
                new SimulationView(environment);


            }
        });

    }
}
