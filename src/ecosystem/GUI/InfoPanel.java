package ecosystem.GUI;


import ecosystem.entities.AbstractEntity;
import javax.swing.*;
import java.awt.*;

    /**
     * פאנל המציג פרטים על הישות הנבחרת במפה.
     */
    public class InfoPanel extends JPanel {

        private JLabel titleLabel;
        private JTextArea infoArea;

        /**
         * בונה את פאנל המידע.
         */
        public InfoPanel() {
            setLayout(new BorderLayout());
            setBackground(new Color(30, 30, 30));
            setPreferredSize(new Dimension(200, 0));

            initComponents();
        }

        /**
         * יוצר את הרכיבים.
         */
        private void initComponents() {
            titleLabel = new JLabel("בחרי ישות");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

            infoArea = new JTextArea();
            infoArea.setEditable(false);
            infoArea.setBackground(new Color(50, 50, 50));
            infoArea.setForeground(Color.WHITE);
            infoArea.setLineWrap(true);

            add(titleLabel, BorderLayout.NORTH);
            add(new JScrollPane(infoArea), BorderLayout.CENTER);
        }

        /**
         * מציג את פרטי הישות הנבחרת.
         * נקרא כשהמשתמש לוחץ על תא במפה.
         * @param entity הישות שנבחרה
         */
        public void showEntity(AbstractEntity entity) {
            if (entity == null) {
                titleLabel.setText("תא ריק");
                infoArea.setText("");
            } else {
                titleLabel.setText(entity.getClass().getSimpleName());
                infoArea.setText(entity.toString());
            }
        }
    }

