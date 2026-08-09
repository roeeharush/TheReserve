package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.factory.EntityFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * חלון דיאלוג קופץ שמאפשר למשתמש להוסיף ישות חדשה לעולם דרך הממשק הגרפי
 * החלון מציג תיבות טקסט להזנת מיקום ואנרגיה ותפריט בחירה של סוג הישות
 */

public class AddEntity extends JDialog {
    private final Environment environment;
    private final String [] entitiesNames = {"Lion", "Deer", "Rabbit", "Flower", "OakTree", "Water", "Rock"};
    private JComboBox<String> typeEntity;
    private JTextField rowInput;
    private JTextField colInput;
    private JTextField energyInput;


    /**
     * בונה ומציג את חלון הדיאלוג להוספת ישות חדשה
     * הבנאי מאתחל את כל הרכיבים הגרפיים קובע את המיקום של החלון יחסית לחלון הראשי ומציג אותו למשתמש
     * @param parent חלון האב הגרפי שממנו נפתח הדיאלוג הנוכחי
     * @param environment סביבת העולם שאליה נוסיף את הישות החדשה
     */

    public AddEntity(JFrame parent, Environment environment) {
        super(parent, "Add Entity", true);
        this.environment = environment;
        setLayout(new BorderLayout());
        initComponents();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);

    }

    /**
     * מאתחלת את הרכיבים של החלון ומסדרת אותם במסך
     * הפונקציה מחלקת את החלון לאזורים ומכניסה את שדות הקלט במרכז ואת כפתורי הפעולה בתחתית
     */

    public void initComponents(){
        add(new JLabel(" "), BorderLayout.NORTH);
        add(createFieldsPanel(), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);

    }

    /**
     * מייצרת פאנל שמכיל את כל שדות הקלט של החלון
     * השדות כוללים בחירה של סוג הישות ותיבות טקסט להזנת שורה עמודה וכמות אנרגיה
     * @return פאנל מעוצב עם כל שדות הקלט הנדרשים
     */

    private JPanel createFieldsPanel() {
        JPanel fieldPanel = new JPanel(new GridLayout(4,2));
        fieldPanel.add(new JLabel("  Type:"));
        typeEntity = new JComboBox<>(entitiesNames);
        fieldPanel.add(typeEntity);

        fieldPanel.add(new JLabel("  Row Number:"));
        rowInput = new JTextField();
        fieldPanel.add(rowInput);

        fieldPanel.add(new JLabel("  Col Number:"));
        colInput = new JTextField();
        fieldPanel.add(colInput);

        fieldPanel.add(new JLabel("  Energy:"));
        energyInput = new JTextField();
        fieldPanel.add(energyInput);

        return fieldPanel;
    }


    /**
     * מייצרת פאנל שמכיל את כפתורי האישור והביטול בתחתית החלון
     * הפונקציה מחברת לכל כפתור את פעולת הלוגיקה המתאימה לו בעת לחיצה
     * @return פאנל שמכיל את הכפתורים אישור וביטול
     */

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("CONFIRM");
        JButton cancelButton = new JButton("CANCEL");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirm();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonsPanel.add(confirmButton);
        buttonsPanel.add(cancelButton);
        return buttonsPanel;
    }


    /**
     * מנהלת את תהליך אישור הקלט והוספת הישות לעולם באמצעות שימוש במפעל הישויות הסטטי
     * הפונקציה קוראת את הנתונים שהמשתמש הקליד מבצעת בדיקות תקינות וגבולות מפה וקוראת למתודת הייצור של הפקטורי בצורה מנותקת קשר כדי לקבל את האובייקט המתאים ולהכניסו לעולם
     */

    private void onConfirm() {
        try {
            String type = (String) typeEntity.getSelectedItem();
            if (type == null) {
                JOptionPane.showMessageDialog(this, "Please select an entity type");
                return;
            }
            int row = Integer.parseInt(rowInput.getText());
            int col = Integer.parseInt(colInput.getText());
            double energy = 0;
            if(!energyInput.getText().isEmpty())
                energy = Double.parseDouble(energyInput.getText());
            if (row < 0 || row >= environment.getRows() || col < 0 || col >= environment.getCols()) {
                JOptionPane.showMessageDialog(this, "Row or col is out of bounds");
                return;
            }
            Position pos = new Position(row, col);
            AbstractEntity entity = EntityFactory.createEntity(type, pos, energy);

            if (!environment.addEntity(entity)) {
                JOptionPane.showMessageDialog(this, "Position is already taken");
                return;
            }
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter number only");
        }
    }
}
