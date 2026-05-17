package ecosystem.GUI;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import javax.swing.*;
import java.awt.*;

/**
 * פאנל המציג סטטיסטיקות על מצב הסימולציה.
 */
public class StatsPanel extends JPanel {

    private final Environment environment;
    private JLabel lionCount;
    private JLabel deerCount;
    private JLabel rabbitCount;
    private JLabel flowerCount;
    private JLabel oakTreeCount;
    private JLabel tickCount;
    private int ticks = 0;

    /**
     * בונה את פאנל הסטטיסטיקות.
     * @param environment הסביבה שאנו עוקבים אחריה
     */
    public StatsPanel(Environment environment) {
        this.environment = environment;
        setLayout(new GridLayout(6, 1));
        setBackground(new Color(30, 30, 30));

        initLabels();
        update();
    }

    /**
     * יוצר את התוויות.
     */
    private void initLabels() {
        lionCount   = new JLabel();
        deerCount   = new JLabel();
        rabbitCount = new JLabel();
        flowerCount = new JLabel();
        oakTreeCount = new JLabel();
        tickCount   = new JLabel();

        // צבע טקסט לבן
        lionCount.setForeground(Color.WHITE);
        deerCount.setForeground(Color.WHITE);
        rabbitCount.setForeground(Color.WHITE);
        flowerCount.setForeground(Color.WHITE);
        oakTreeCount.setForeground(Color.WHITE);
        tickCount.setForeground(Color.WHITE);

        add(lionCount);
        add(deerCount);
        add(rabbitCount);
        add(flowerCount);
        add(oakTreeCount);
        add(tickCount);
    }

    /**
     * מעדכן את הסטטיסטיקות — נקרא מ-onWorldChanged.
     */
    public void update() {
        int lions = 0, deer = 0, rabbits = 0, flowers = 0, oaks = 0;

        for (AbstractEntity e : environment.getEntities()) {
            if (e instanceof Lion)    lions++;
            if (e instanceof Deer)    deer++;
            if (e instanceof Rabbit)  rabbits++;
            if (e instanceof Flower)  flowers++;
            if (e instanceof OakTree) oaks++;
        }

        ticks++;
        lionCount.setText("🦁 אריות: "  + lions);
        deerCount.setText("🦌 צבאים: "  + deer);
        rabbitCount.setText("🐰 ארנבים: " + rabbits);
        flowerCount.setText("🌸 פרחים: " + flowers);
        oakTreeCount.setText("🌳 עצים: "  + oaks);
        tickCount.setText("⏱ Ticks: "   + ticks);
    }
}