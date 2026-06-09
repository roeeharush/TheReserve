package ecosystem.gui;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import javax.swing.*;
import java.awt.*;
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
    private Environment environment;

    /**
     * בונה פאנל מידע חדש ומעצב אותו
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
     * מגדירה את הלוגיקה שתתבצע בעת לחיצה על כפתורי האפקטים
     */
    private void setupActionListeners() {
        // לוגיקת לחיצה על כפתור רעל
        applyPoisonButton.addActionListener(e -> {
            if (selectedEntity instanceof LivingEntity living) {
                // מעבירים ישירות את ה-living שג'אווה כבר יודעת שהוא חוקי
                PoisonedDecorator poisoned = new PoisonedDecorator(living);

                environment.removeEntity(selectedEntity);
                environment.addEntity(poisoned);
                showEntity(poisoned);
            }
        });

        // לוגיקת לחיצה על כפתור האצה
        applySpeedButton.addActionListener(e -> {
            if (selectedEntity instanceof LivingEntity living) {
                // מעבירים ישירות את ה-living שג'אווה כבר יודעת שהוא חוקי
                SpeedBoostDecorator boosted = new SpeedBoostDecorator(living);

                environment.removeEntity(selectedEntity);
                environment.addEntity(boosted);
                showEntity(boosted);
            }
        });
    }

    /**
     * מעדכנת את הפאנל להציג את הנתונים של הישות שנבחרה במפה
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
        setButtonsVisibility(entity instanceof LivingEntity);
    }

    private void setButtonsVisibility(boolean visible) {
        applyPoisonButton.setVisible(visible);
        applySpeedButton.setVisible(visible);
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