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
        setResizable(false);
        initComponents();
        layoutComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void initComponents() {
        controlPanel = new ControlPanel();
        infoPanel = new InfoPanel();
        statsPanel =  new StatsPanel( environment);
        mapPanel = new MapPanel(environment , infoPanel);
    }

    private void layoutComponents() {
        JScrollPane scrollPane = new JScrollPane(mapPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel(new BorderLayout());
        JLabel logo = new JLabel(ImageLoader.getImage("logo"));
        eastPanel.add(logo, BorderLayout.NORTH);
        eastPanel.add(infoPanel, BorderLayout.CENTER);
        eastPanel.add(statsPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

}