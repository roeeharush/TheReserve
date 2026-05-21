package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import static ecosystem.gui.ImageLoader.getImage;

public class MapPanel extends JPanel implements WorldObserver  {
    private final Environment environment;
    private final int rows;
    private final int cols;
    private final InfoPanel infoPanel;
    private JLabel selectedCell = null;

    public MapPanel(Environment environment, InfoPanel infoPanel){
        this.environment = environment;
        this.rows = environment.getRows();
        this.cols = environment.getCols();
        this.infoPanel = infoPanel;
        setLayout(new GridLayout(rows, cols));


        int size = 64;
        setPreferredSize(new Dimension(cols * size, rows * size));
        setMaximumSize(new Dimension(cols * size, rows * size));
        setMinimumSize(new Dimension(cols * size, rows * size));

        buildGrid();
        environment.addObserver(this);
    }

    private void buildGrid() {
        for (int i=0 ; i< rows ; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel cell = new JLabel();
                cell.setPreferredSize(new Dimension(64, 64));
                cell.setMinimumSize(new Dimension(64, 64));
                cell.setMaximumSize(new Dimension(64, 64));
                AbstractEntity entity = environment.getEntityAt(i, j);
                if (entity != null) {
                    String name = entity.getClass().getSimpleName();
                    cell.setIcon(getImage(name));
                    cell.setToolTipText(entity.toString());
                }
                else
                    cell.setIcon(getImage("ground"));

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedCell != null) {
                            selectedCell.setBorder(null);
                        }
                        selectedCell =cell;
                        selectedCell.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                        infoPanel.showEntity(entity);
                    }
                });

                add(cell);
            }
        }
    }

    @Override
    public void onWorldChanged() {
        selectedCell = null;
        removeAll();
        buildGrid();
        revalidate();
        repaint();
    }
}
