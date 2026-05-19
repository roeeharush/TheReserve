package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final Environment environment;
    private JLabel name;
    private JLabel position;
    private JLabel aliveState;
    private JLabel energy;
    private JLabel maxEnergy;

    public InfoPanel(Environment environment){
        this.environment = environment;
        setLayout(new GridLayout(0,1));
        setBackground(new Color(225,225,225));
        initLabels();

    }
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

    private void setLabelVisibility(boolean flag){
        position.setVisible(flag);
        aliveState.setVisible(flag);
        energy.setVisible(flag);
        maxEnergy.setVisible(flag);
    }

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
