package ecosystem.GUI;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * פאנל שמציג את המפה כגריד של תמונות.
 * כל תא מציג את הישות שנמצאת בו.
 */
public class MapPanel extends JPanel {

    private final Environment environment;
    private Position selectedPosition = null;
    private AbstractEntity selectedEntity = null;

    /**
     * בונה את פאנל המפה.
     * @param environment הסביבה שאנו מציגים
     */
    public MapPanel(Environment environment) {
        this.environment = environment;

        setPreferredSize(new Dimension(
                environment.getCols() * ImageLoader.CELL_SIZE,
                environment.getRows() * ImageLoader.CELL_SIZE));

        setBackground(new Color(0, 300, 20));

        // טיפול בלחיצה על תא
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / ImageLoader.CELL_SIZE;
                int row = e.getY() / ImageLoader.CELL_SIZE;
                selectedPosition = new Position(row, col);
                selectedEntity = environment.getEntityAt(row, col);
                repaint();
            }
        });

        // טיפול בריחוף מעל תא
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int col = e.getX() / ImageLoader.CELL_SIZE;
                int row = e.getY() / ImageLoader.CELL_SIZE;
                AbstractEntity entity = environment.getEntityAt(row, col);
                if (entity != null) {
                    setToolTipText(entity.toString());
                } else {
                    setToolTipText(null);
                }
            }
        });
    }

    /**
     * מחזיר את הישות הנבחרת כרגע.
     * @return הישות הנבחרת או null אם אין
     */
    public AbstractEntity getSelectedEntity() {
        return selectedEntity;
    }

    /**
     * מצייר את המפה.
     * @param g אובייקט הציור
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = ImageLoader.CELL_SIZE;

        for (int row = 0; row < environment.getRows(); row++) {
            for (int col = 0; col < environment.getCols(); col++) {

                int x = col * size;
                int y = row * size;

                // רקע ירוק לכל תא
                g.setColor(new Color(34, 139, 34));
                g.fillRect(x, y, size, size);

                // גבול לכל תא
                g.setColor(new Color(0, 100, 0));
                g.drawRect(x, y, size, size);

                // ציור הישות אם יש
                AbstractEntity entity = environment.getEntityAt(row, col);
                if (entity != null) {
                    ImageIcon icon = ImageLoader.get(entity.getImageName());
                    if (icon != null) {
                        g.drawImage(icon.getImage(), x, y, size, size, this);
                    }
                }

                // מסגרת צהובה על התא הנבחר
                if (selectedPosition != null
                        && selectedPosition.getRow() == row
                        && selectedPosition.getCol() == col) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.YELLOW);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRect(x + 1, y + 1, size - 2, size - 2);
                }
            }
        }
    }
}