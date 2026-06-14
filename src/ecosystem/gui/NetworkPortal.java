package ecosystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class NetworkPortal extends JFrame {
    private static final Logger logger = Logger.getLogger(NetworkPortal.class.getName());

    private JTextField ipField;
    private JTextField energyField;
    private JTextField rowField;
    private JTextField colField;
    private JComboBox<String> entitySelector;
    private JButton sendButton;

    public NetworkPortal() {
        setTitle("Network Portal");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("IP Address:"));
        ipField = new JTextField();
        add(ipField);

        add(new JLabel("Entity Type:"));
        entitySelector = new JComboBox<>(new String[]{"Lion", "Deer", "Rabbit", "Flower", "OakTree", "Water", "Rock"});
        add(entitySelector);

        add(new JLabel("Energy:"));
        energyField = new JTextField();
        add(energyField);

        add(new JLabel("Row:"));
        rowField = new JTextField();
        add(rowField);

        add(new JLabel("Col:"));
        colField = new JTextField();
        add(colField);

        add(new JLabel(""));
        sendButton = new JButton("Send");
        add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEntity();
            }
        });

        setVisible(true);
    }

    private void sendEntity() {
        String ip = ipField.getText();
        String type = (String) entitySelector.getSelectedItem();
        String energy = energyField.getText();
        String row = rowField.getText();
        String col = colField.getText();
        String message = "SPAWN," + type + "," + energy + "," + row + "," + col;

        try {
            Socket socket = new Socket(ip, 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
            socket.close();
            logger.info("Sent: " + message + " to " + ip);
        } catch (IOException e) {
            logger.warning("Connection error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to connect to: " + ip);
        }
    }



}



