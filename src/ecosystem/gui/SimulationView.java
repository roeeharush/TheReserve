package ecosystem.gui;


import ecosystem.core.Environment;

import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame  {
    private final Environment environment;
    private MapPanel mapPanel;
    private ControlPanel controlPanel;
    private StatsPanel statsPanel;
    private InfoPanel infoPanel;



    public SimulationView(Environment environment){
        super("The Reserve Simulation");
        this.environment = environment;
        setLayout(new BorderLayout());
        initComponents();
        layoutComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void initComponents() {
        controlPanel = new ControlPanel();
        infoPanel = new InfoPanel();
        statsPanel =  new StatsPanel( environment);
        mapPanel = new MapPanel(environment , infoPanel);
    }

    private void layoutComponents() {
        add(mapPanel, BorderLayout.CENTER);
        add(infoPanel,BorderLayout.EAST);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(statsPanel, BorderLayout.WEST);
        southPanel.add(controlPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

}