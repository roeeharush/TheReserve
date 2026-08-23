package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;
import network.NetworkManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * מחלקת הבקר שמקשרת בין רכיבי הממשק הגרפי ללוגיקה של הסימולציה
 * הבקר מאזין ללחיצות על הכפתורים בפאנל השליטה ומפעיל את מנוע הסימולציה או משנה את מצב העולם בהתאם
 */

public class SimulationController {
    private final Environment environment;
    private final ControlPanel controlPanel;
    private final SimulationView simulationView;
    private final SimulationEngine engine;
    private final Timer timer;
    private final NetworkManager networkManager;

    /**
     * בונה בקר סימולציה חדש ומחבר את כל הרכיבים יחד
     * הבנאי מאתחל את השעון הפנימי שמריץ את התורות באופן אוטומטי ומפעיל את חיבור המאזינים לכפתורים
     * @param view חלון התצוגה הראשי של הסימולציה
     * @param controlPanel פאנל כפתורי השליטה
     * @param environment סביבת העולם והמפה של המערכת
     * @param engine מנוע הסימולציה שאחראי על קידום הזמן וניהול הישויות
     */

    public SimulationController(SimulationView view, ControlPanel controlPanel, Environment environment, SimulationEngine engine) {
        this.environment = environment;
        this.simulationView = view;
        this.controlPanel = controlPanel;
        this.engine = engine;

         timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.tick();
            }
        });
         connectingButtons();

        networkManager = new NetworkManager(8080, environment);
        networkManager.start();
    }

    /**
     * מחברת מאזיני פעולה לכל כפתורי השליטה בממשק הגרפי ומנהלת את מחזור החיים של התהליכונים במערכת
     * המתודה מגדירה את התנהגות כפתורי הטיק וההוספה ובנוסף משלבת בין מנגנון השעון הטורי לבין הפעלת ועצירת התהליכונים המקביליים של הישויות ברקע בעת לחיצה על כפתורי הריצה העצירה והאתחול
     */

    private void connectingButtons(){
        controlPanel.getTickButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.tick();
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
                if (!timer.isRunning()) {
                    engine.startAllThreads();
                    timer.start();
                }
            }
        });

        controlPanel.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.stopAllThreads();
                timer.stop();
                networkManager.stop();
            }
        });

        controlPanel.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                engine.stopAllThreads();
                networkManager.stop();
                environment.reset();
                SimulationView newView = new SimulationView(environment);
                new SimulationController(newView, newView.getControlPanel(), environment, engine);
                simulationView.dispose();
            }
        });

        controlPanel.getSendToPortalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NetworkPortal();
            }
        });
    }
}
