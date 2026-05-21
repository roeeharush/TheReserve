package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;
import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel implements WorldObserver {
    private final Environment environment;
    private JLabel lionCount;
    private JLabel deerCount;
    private JLabel rabbitCount;
    private JLabel flowerCount;
    private JLabel oakTreeCount;
    private JLabel tickCount;
    private JLabel totalEnergy;
    private int ticks = 0;

    public StatsPanel(Environment environment) {
        this.environment = environment;
        setLayout(new GridLayout(0, 1));
        setBackground(new Color(225, 225, 225));
        initLabels();
        environment.addObserver(this);

    }

    private void initLabels() {
        lionCount = new JLabel();
        add(lionCount);
        deerCount = new JLabel();
        add(deerCount);
        rabbitCount = new JLabel();
        add(rabbitCount);
        flowerCount = new JLabel();
        add(flowerCount);
        oakTreeCount = new JLabel();
        add(oakTreeCount);
        tickCount = new JLabel();
        add(tickCount);
        totalEnergy = new JLabel();
        add(totalEnergy);
    }


    @Override
    public void onWorldChanged() {
        int lions = 0;
        int deers = 0;
        int rabbit = 0;
        int flower = 0;
        int oaktree = 0;
        double energy =0;

        for(AbstractEntity entity : environment.getEntities()){
            if(entity instanceof LivingEntity living) {
                 energy += living.getEnergy();
            }
            if(entity instanceof Lion)
                lions++;
            if(entity instanceof Deer)
                deers++;
            if(entity instanceof Rabbit)
                rabbit++;
            if(entity instanceof Flower)
                flower++;
            if(entity instanceof OakTree)
                oaktree++;
        }

        ticks++;
        lionCount.setText("lion:" + lions);
        deerCount.setText("deer:" + deers);
        rabbitCount.setText(" rabbit:" + rabbit);
        flowerCount.setText("flower:" + flower);
        oakTreeCount.setText(" oaktree" + oaktree);
        tickCount.setText("⏱:" + environment.getTicks());
        totalEnergy.setText("⚡:" + energy);
    }


}
