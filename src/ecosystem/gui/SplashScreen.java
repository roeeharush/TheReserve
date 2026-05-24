package ecosystem.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame {
    private ImageIcon splashImage;
    private JButton startButton;
    private start act;


    public SplashScreen(){
        super("WELCOME TO OUR GAME!");
        setLayout(  new BorderLayout());
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void initComponents() {
        splashImage = ImageLoader.getImage("start");
        JLabel imageLabel = new JLabel(splashImage);
        add(imageLabel, BorderLayout.CENTER);

        startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new start();
            }
        });
        add(startButton, BorderLayout.SOUTH);

    }
}
