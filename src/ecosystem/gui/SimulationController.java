package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * מחלקת הבקר שמקשרת בין רכיבי הממשק הגרפי ללוגיקה של הסימולציה
 * הבקר מאזין ללחיצות על הכפתורים בפאנל השליטה ומפעיל את מנוע הסימולציה או משנה את מצב העולם בהתאם
 */

public class SimulationController {
    private Environment environment;
    private ControlPanel controlPanel;
    private SimulationView simulationView;
    private SimulationEngine engine;
    private Timer timer;

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
                engine.Tick();
            }
        });

         connectingButtons();
    }

    /**
     * מחברת מאזיני פעולה לכל כפתורי השליטה בממשק הגרפי ומנהלת את מחזור החיים של התהליכונים במערכת
     * המתודה מגדירה את התנהגות כפתורי הטיק וההוספה ובנוסף משלבת בין מנגנון השעון הטורי לבין הפעלת ועצירת התהליכונים המקביליים של הישויות ברקע בעת לחיצה על כפתורי הריצה העצירה והאתחול
     */

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
                engine.startAllThreads();
                timer.start();

            }
        });

        controlPanel.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.stopAllThreads();
                timer.stop();
            }
        });

        controlPanel.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                engine.stopAllThreads();
                environment.reset();
                SimulationView newView = new SimulationView(environment);
                new SimulationController(newView, newView.getControlPanel(), environment, engine);
                simulationView.dispose();


            }
        });

    }
}
