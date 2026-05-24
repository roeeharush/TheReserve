package ecosystem.gui;

import ecosystem.core.Environment;
import ecosystem.core.SimulationEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class start extends JDialog {
    private JTextField rowInput;
    private JTextField colInput;

    public start(JFrame parent){
        super(parent,"The Reserve", true);
        setLayout(new BorderLayout());
        initComponents();
        pack();
        pack();
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);

    }

    private void initComponents() {
        add(new JLabel("Set size of the map  "), BorderLayout.NORTH);
        add(createFieldsPanel(), BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = getRows();
                int cols = getCols();
                dispose();
                Environment env = new Environment(rows, cols);
                SimulationEngine engine = new SimulationEngine(env);
                SimulationView view = new SimulationView(env);
                new SimulationController(view, view.getControlPanel(), env, engine);
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }


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

    public int getRows() {
        return Integer.parseInt(rowInput.getText());
    }

    public int getCols() {
        return Integer.parseInt(colInput.getText());
    }







}
