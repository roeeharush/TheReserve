package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.decorators.EntityDecorator;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import ecosystem.decorators.PoisonedDecorator;
import ecosystem.decorators.SpeedBoostDecorator;


/**
 * מחלקה שמייצגת פאנל צדדי בממשק הגרפי להצגת נתונים על ישות שנבחרה
 * הפאנל מציג רשימה של תוויות טקסט שמתעדכנות בזמן אמת עם השם המיקום מצב החיות והאנרגיה של היצור
 */
public class InfoPanel extends JPanel {
    private JLabel name;
    private JLabel position;
    private JLabel aliveState;
    private JLabel energy;
    private JLabel maxEnergy;
    private JButton applyPoisonButton;
    private JButton applySpeedButton;
    private AbstractEntity selectedEntity;
    private final Environment environment;
    private static final Logger logger = Logger.getLogger(InfoPanel.class.getName());


    /**
     * בונה פאנל מידע חדש ומעצב אותו
     * @param environment סביבת העולם שאליה הפאנל מבצע שינויים בעת החלת אפקטים על ישויות
     */
    public InfoPanel(Environment environment){
        this.environment = environment;
        setLayout(new GridLayout(0,1 ,0 ,2));
        setBorder(BorderFactory.createTitledBorder("Entity Info"));
        setBackground(new Color(225,225,225));
        setPreferredSize(new Dimension(150, 0));
        initLabels();
        setupActionListeners();
    }


    /**
     * מייצרת את כל תוויות הטקסט הריקות של הפאנל ומכניסה אותן לתצוגה
     */
    private void initLabels(){
        name = new JLabel();
        add(name);
        position = new JLabel();
        add(position);
        aliveState = new JLabel();
        add(aliveState);
        energy = new JLabel();
        add(energy);
        maxEnergy = new JLabel();
        add(maxEnergy);
        applyPoisonButton = new JButton("Apply Poison");
        applySpeedButton = new JButton("Speed Boost");
        applyPoisonButton.setBackground(Color.ORANGE);
        applySpeedButton.setBackground(Color.CYAN);

        applyPoisonButton.setVisible(false);
        applySpeedButton.setVisible(false);
        add(applyPoisonButton);
        add(applySpeedButton);
    }


    /**
     * מגדירה את הלוגיקה שתתבצע בעת לחיצה על כפתורי האפקטים של הדקורטורים בממשק
     * בעת לחיצה על כפתור רעל או האצה המתודה בודקת האם נבחרה ישות חיה ואם כן היא עוטפת אותה בדקורטור המתאים ומעדכנת את מודל העולם בהחלפת הישות המקורית במעטפת החדשה שלה
     */
    private void setupActionListeners() {
        applyPoisonButton.addActionListener(e -> {
            if (selectedEntity instanceof LivingEntity living)
                applyDecorator(new PoisonedDecorator(living));
        });

        applySpeedButton.addActionListener(e -> {
            if (selectedEntity instanceof LivingEntity living)
                applyDecorator(new SpeedBoostDecorator(living));
        });
    }


    /**
     * מבצעת את הלוגיקה המשותפת להחלת כל סוג דקורטור על הישות הנבחרת כרגע
     * מסירה את הישות המקורית מהעולם, מוסיפה במקומה את הדקורטור העוטף, רושמת ללוג את תוצאת ההוספה, ומרעננת את תצוגת הפאנל
     * @param decorator הדקורטור החדש שיש להחיל במקום הישות שנבחרה
     */
    private void applyDecorator(EntityDecorator decorator) {
        environment.removeEntity(selectedEntity);
        boolean added = environment.addEntity(decorator);
        logger.info(decorator.getClass().getSimpleName() + " added: " + added);
        showEntity(decorator);
    }


    /**
     * מעדכנת את הפאנל להציג את הנתונים של הישות שנבחרה במפה ומנהלת את נראות כפתורי האפקטים
     * אם לא נבחרה ישות הפאנל מציג שמדובר באדמה ומסתיר את הכפתורים ואם נבחרה ישות היא מציגה את פרטיה ומדליקה את כפתורי הרעל וההאצה רק במידה ומדובר ביצור חי או בישות שכבר מעוטרת באפקט
     * @param entity הישות שרוצים להציג את המידע שלה על המסך
     */
    public void showEntity(AbstractEntity entity) {
        this.selectedEntity = entity;
        if (entity == null) {
            name.setText("Ground");
            name.setVisible(true);
            setLabelVisibility(false);
            setButtonsVisibility(false);
            return;
        }
        name.setText(entity.getClass().getSimpleName());
        setGetters(entity);

        boolean showButtons = entity instanceof LivingEntity;
        setButtonsVisibility(showButtons);
    }


    /**
     * קובעת את מצב הנראות של תוויות המידע בפאנל בבת אחת
     */
    private void setLabelVisibility(boolean flag){
        position.setVisible(flag);
        aliveState.setVisible(flag);
        energy.setVisible(flag);
        maxEnergy.setVisible(flag);
    }


    /**
     * שולפת את הנתונים המעודכנים מתוך הישות ומזריקה אותם לתוך רכיבי הטקסט הגרפיים
     * המתודה מציגה מיקום ומצב חיות ומבצעת בדיקה פולימורפית האם מדובר בישות חיה או בדקורטור שעוטף ישות חיה כדי לשלוף ולהציג את רמות האנרגיה הנוכחיות והמקסימליות בצורה נכונה
     * @param entity הישות שממנה שולפים את הנתונים לעדכון הפאנל
     */
    private void setGetters(AbstractEntity entity){
        position.setText("Position: " + entity.getPosition());
        aliveState.setText("Alive State: " + entity.isAlive());

        if (entity instanceof LivingEntity living) {
            energy.setText("Energy: " + living.getEnergy());
            maxEnergy.setText("Max Energy: " + living.getMaxEnergy());
            setLabelVisibility(true);
        } else if (entity instanceof EntityDecorator decorator &&
                decorator.getDecoratedEntity() instanceof LivingEntity living) {
            energy.setText("Energy: " + living.getEnergy());
            maxEnergy.setText("Max Energy: " + living.getMaxEnergy());
            setLabelVisibility(true);
        } else {
            energy.setVisible(false);
            maxEnergy.setVisible(false);
        }
    }


    /**
     * קובעת את מצב הנראות של כפתורי החלת האפקטים בפאנל בבת אחת
     * המתודה מדליקה או מכבה את כפתורי הרעל וההאצה בהתאם לכך האם הישות שנבחרה על ידי המשתמש היא ישות שניתן להחיל עליה אפקטים דינמיים
     * @param visible true כדי להציג את כפתורי האפקטים או false כדי להסתיר אותם מהמסך
     */
    private void setButtonsVisibility(boolean visible) {
        applyPoisonButton.setVisible(visible);
        applySpeedButton.setVisible(visible);
    }
}