package ecosystem.gui;
import ecosystem.core.Environment;
import javax.swing.*;
import java.awt.*;

/**
 * חלון התצוגה הראשי של סימולציית שמורת הטבע
 * המחלקה יורשת מחלון גרפי ומאחדת בתוכה את כל הפאנלים השונים כולל המפה הגרפית לוח הבקרה פאנל הסטטיסטיקה ופאנל המידע הצדדי
 */

public class SimulationView extends JFrame  {
    private final Environment environment;
    private MapPanel mapPanel;
    private ControlPanel controlPanel;
    private StatsPanel statsPanel;
    private InfoPanel infoPanel;


    /**
     * בונה ומציג את חלון הסימולציה הראשי
     * הבנאי קובע את כותרת החלון מגדיר פריסה מרכזית מונע שינוי גודל מאתחל ומסדר את כל רכיבי הממשק ומציג את החלון במרכז המסך
     * @param environment סביבת העולם שממנה הרכיבים השונים שואבים את הנתונים שלהם
     */

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

    /**
     * מאתחלת את כל פאנלי המשנה של המערכת הגרפית
     * המתודה מייצרת מופעים חדשים של לוח הבקרה פאנל המידע פאנל הסטטיסטיקה ולוח המפה ומקשרת ביניהם
     */

    private void initComponents() {
        controlPanel = new ControlPanel();
        infoPanel = new InfoPanel(environment);
        statsPanel =  new StatsPanel( environment);
        mapPanel = new MapPanel(environment , infoPanel);
    }

    /**
     * קובעת את המיקום והסידור של כל הפאנלים בתוך החלון הראשי
     * המתודה שמה את המפה הנגללת במרכז המסך מארגנת בצד ימין את הלוגו פאנל המידע ופאנל הסטטיסטיקה זה תחת זה וממקמת את כפתורי השליטה בתחתית החלון
     */

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

    /**
     * מחזיר את פאנל כפתורי השליטה של החלון
     * מתודה זו מאפשרת לבקר החיצוני לגשת לכפתורים ולהצמיד להם מאזיני לחיצה
     * @return פאנל השליטה והבקרה של הסימולציה
     */

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}