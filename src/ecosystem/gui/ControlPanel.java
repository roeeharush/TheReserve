package ecosystem.gui;

import javax.swing.*;
import java.awt.*;

    public class ControlPanel extends JPanel {

        private JButton tickButton;
        private JButton runButton;
        private JButton stopButton;
        private JButton resetButton;
        private JButton addEntityButton;


        public ControlPanel() {
            setLayout(new FlowLayout( ));
            setBackground(new Color(225, 225, 225));

            initButtons();
        }


        private void initButtons() {
            tickButton = new JButton("Tick");
            runButton = new JButton("Run");
            stopButton = new JButton("Stop");
            resetButton = new JButton("Reset");
            addEntityButton = new JButton("Add Entity");

            add(tickButton);
            add(runButton);
            add(stopButton);
            add(resetButton);
            add(addEntityButton);
        }

        public JButton getTickButton(){
            return tickButton;
        }

        public JButton getRunButton() {
            return runButton;
        }

        public JButton getStopButton() {
            return stopButton;
        }

        public JButton getResetButton() {
            return resetButton;
        }

        public JButton getAddEntityButton() {
            return addEntityButton;
        }
    }

