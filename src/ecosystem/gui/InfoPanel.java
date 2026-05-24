package ecosystem.gui;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import javax.swing.*;
import java.awt.*;

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


    /**
     * בונה פאנל מידע חדש ומעצב אותו
     * הבנאי קובע את צורת הסידור של התוויות בטור אחד מוסיף מסגרת עם כותרת מגדיר צבע רקע אפור בהיר ומאתחל את התוויות
     */

    public InfoPanel(){
        setLayout(new GridLayout(0,1 ,0 ,2));
        setBorder(BorderFactory.createTitledBorder("Entity Info"));
        setBackground(new Color(225,225,225));
        setPreferredSize(new Dimension(150, 0));
        initLabels();

    }

    /**
     * מייצרת את כל תוויות הטקסט הריקות של הפאנל ומכניסה אותן לתצוגה
     * התוויות שנוצרות מיועדות להצגת שם הישות המיקום שלה מצב החיות שלה האנרגיה הנוכחית והאנרגיה המקסימלית
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
    }

    /**
     * מעדכנת את הפאנל להציג את הנתונים של הישות שנבחרה במפה
     * אם לא נבחרה ישות הפאנל מציג שמדובר באדמה רגילה ומסתיר את שאר הנתונים ואם יש ישות הוא מציג את השם של המחלקה שלה ומעדכן את שאר הפרטים
     * @param entity הישות שרוצים להציג את המידע שלה על המסך
     */

    public void showEntity(AbstractEntity entity){
        if(entity == null){
            name.setText("Ground");
            name.setVisible(true);
            setLabelVisibility(false);
        }
        else{
            name.setText(entity.getClass().getSimpleName());
            setGetters(entity);
        }

    }

    /**
     * קובעת את מצב הנראות של תוויות המידע בפאנל בבת אחת
     * @param flag true כדי להציג את התוויות הנוספות או false כדי להסתיר אותן מהמסך
     */

    private void setLabelVisibility(boolean flag){
        position.setVisible(flag);
        aliveState.setVisible(flag);
        energy.setVisible(flag);
        maxEnergy.setVisible(flag);
    }

    /**
     * קוראת את הנתונים מתוך הישות ומעדכנת את הטקסט של התוויות בהתאם
     * המתודה מציגה מיקום ומצב חיות ואם מדובר בישות חיה בעלת אנרגיה היא מציגה גם את נתוני האנרגיה הנוכחית והמקסימלית שלה
     * @param entity הישות שממנה שולפים את הנתונים לעדכון הפאנל
     */

    private void setGetters(AbstractEntity entity){
        position.setText("Position: " + entity.getPosition());
        aliveState.setText("Alive State: " + entity.isAlive());
        if(entity instanceof LivingEntity living){
            energy.setText("Energy: " + (living.getEnergy()));
            maxEnergy.setText("Max Energy: " + ((living.getMaxEnergy())));
            setLabelVisibility(true);
        }
        else {
            energy.setVisible(false);
            maxEnergy.setVisible(false);
        }
    }
}
