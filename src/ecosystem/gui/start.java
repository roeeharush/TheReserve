package ecosystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class start extends JDialog {
    private JTextField rowInput;
    private JTextField colInput;

    public start(){
        super((JFrame) null, "The Reserve", true);
        setLayout(new BorderLayout());
        initComponents();
        pack();
        setResizable(false);
        setVisible(true);

    }

    private void initComponents() {
        add(new JLabel("Set size of the map  "), BorderLayout.NORTH);
        add(createFieldsPanel(), BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
