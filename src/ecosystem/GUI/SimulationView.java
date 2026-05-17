package ecosystem.GUI;

import ecosystem.core.Environment;

import javax.swing.*;

public class SimulationView extends JFrame implements WorldObserver {
    private final Environment environment; //
    private MapPanel mapPanel;             // פאנל המפה
    private ControlPanel controlPanel;     // כפתורי שליטה
    private StatsPanel statsPanel;         // סטטיסטיקות
    private InfoPanel infoPanel;// פרטי ישות נבחרת

    public SimulationView(Environment environment) {
        super("Nature Reserve Simulation");
        this.environment = environment;
        environment.addObserver(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        layoutComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        mapPanel = new MapPanel(environment);
        controlPanel = new ControlPanel();
        statsPanel = new StatsPanel(environment);
        infoPanel = new InfoPanel();
    }

    private void layoutComponents() {
        add(mapPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(statsPanel, BorderLayout.WEST);
        southPanel.add(controlPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onWorldChanged() {
        mapPanel.repaint();
        statsPanel.update();
    }


    }





















    @Override
    public void onWorldChanged() {

    }
}
