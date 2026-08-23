package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * חלון דיאלוג קופץ המשמש כמסך הפתיחה והגדרת המערכת של הסימולציה
 * החלון מאפשר למשתמש לקבוע את ממדי המפה ויוצר את כל רכיבי הליבה והתצוגה הנדרשים להפעלת המשחק
 */
public class Start extends JDialog {
    private JTextField rowInput;
    private JTextField colInput;


    /**
     * בונה ומציג את חלון הפתיחה של הסימולציה
     * הבנאי קובע את פריסת הרכיבים מאתחל את שדות הקלט מגדיר גודל קבוע לחלון ומציג אותו במרכז המסך
     * @param parent חלון האב הגרפי שממנו נפתח דיאלוג הפתיחה
     */
    public Start(JFrame parent){
        super(parent,"The Reserve", true);
        setLayout(new BorderLayout());
        initComponents();
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }


    /**
     * מאתחלת את רכיבי חלון הפתיחה ומגדירה את פעולת כפתור ההתחלה
     * בעת לחיצה על כפתור הסטארט הפונקציה קוראת את גודל המפה מהשדות סוגרת את חלון הפתיחה ומאתחלת את העולם המנוע והבקר הראשי של הסימולציה
     */
    private void initComponents() {
        add(new JLabel("Set size of the map  "), BorderLayout.NORTH);
        add(createFieldsPanel(), BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rows = getRows();
                    int cols = getCols();
                    dispose();
                    Environment env = new Environment(rows, cols);
                    SimulationEngine engine = new SimulationEngine(env);
                    SimulationView view = new SimulationView(env);
                    new SimulationController(view, view.getControlPanel(), env, engine);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Start.this, "Enter numbers only");
                }
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }


    /**
     * מייצרת פאנל המכיל את שדות הזנת המידע עבור ממדי המפה
     * הפאנל מסדר ברשת תיבות טקסט ותוויות להזנת מספר השורות ומספר העמודות הרצויים
     * @return פאנל המכיל את שדות הקלט של שורות ועמודות
     */
    private JPanel createFieldsPanel() {
        JPanel fieldPanel = new JPanel(new GridLayout(2,2));
        fieldPanel.add(new JLabel("  Row:"));
        rowInput = new JTextField();
        fieldPanel.add(rowInput);

        fieldPanel.add(new JLabel("  Col:"));
        colInput = new JTextField();
        fieldPanel.add(colInput);
        return fieldPanel;
    }


    /**
     * קוראת את הטקסט משדה קלט השורות וממירה אותו למספר שלם
     * @return מספר השורות שהמשתמש הזן עבור המפה
     */
    private int getRows() {
        return Integer.parseInt(rowInput.getText());
    }


    /**
     * קוראת את הטקסט משדה קלט העמודות וממירה אותו למספר שלם
     * @return מספר העמודות שהמשתמש הזן עבור המפה
     */
    private int getCols() {
        return Integer.parseInt(colInput.getText());
    }
}
