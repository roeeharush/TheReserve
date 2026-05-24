package ecosystem.gui;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEntity extends JDialog {
    private Environment environment;
    private String [] entitiesNames = {"Lion", "Deer", "Rabbit", "Flower", "OakTree", "Water", "Rock"};
    private JComboBox<String> typeEntity;
    private JTextField rowInput;
    private JTextField colInput;
    private JTextField energyInput;

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

    public void initComponents(){
        add(new JLabel(" "), BorderLayout.NORTH);
        add(createFieldsPanel(), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);

    }

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

    private void onConfirm() {
        try {
            String type = (String) typeEntity.getSelectedItem();
            int row = Integer.parseInt(rowInput.getText());
            int col = Integer.parseInt(colInput.getText());
            double energy = 0;
            if(!energyInput.getText().isEmpty())
                energy = Double.parseDouble(energyInput.getText());
            Position pos = new Position(row, col);
            AbstractEntity entity = null;

            switch (type) {
                case "Lion" -> entity = new Lion(pos, energy);
                case "Deer" -> entity = new Deer(pos, energy);
                case "Rabbit" -> entity = new Rabbit(pos, energy);
                case "Flower" -> entity = new Flower(pos, energy);
                case "OakTree" -> entity = new OakTree(pos, energy);
                case "Water" -> entity = new Water(pos);
                case "Rock" -> entity = new Rock(pos);
            }

            environment.addEntity(entity);
            dispose();


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter Number Only");
        }
    }
}
