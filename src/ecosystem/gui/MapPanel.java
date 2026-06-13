package ecosystem.gui;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ecosystem.gui.ImageLoader.getImage;

/**
 * מחלקה שמייצגת את לוח התצוגה הגרפי של המפה בסימולציה
 * הפאנל מציג רשת משבצות של העולם ומעדכן את עצמו באופן אוטומטי בכל פעם שחל שינוי בסביבה האקולוגית
 */

public class MapPanel extends JPanel implements WorldObserver  {
    private final Environment environment;
    private final int rows;
    private final int cols;
    private final InfoPanel infoPanel;
    private JLabel selectedCell = null;


    /**
     * בונה לוח מפה חדש ומאשר את הגדלים שלו
     * הבנאי קובע את פריסת הרשת לפי שורות ועמודות העולם מחשב את ממדי המסך הנדרשים בפיקסלים בונה את הלוח הראשי ומחבר את המפה כמאזין לשינויים בעולם
     * @param environment סביבת העולם שממנה קוראים את נתוני הישויות והמפה
     * @param infoPanel פאנל המידע הצדדי שאותו נעדכן כאשר המשתמש לוחץ על משבצת
     */
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

    /**
     * מייצרת ומאכלסת את רשת המשבצות הגרפית של המפה ומצמידה מאזיני לחיצה דינמיים
     * המתודה עוברת בלולאה על כל המשבצות במפה מתאימה לכל משבצת את האייקון המעודכן שלה ומצמידה מאזין לחיצה השולף את הישות ישירות מתוך המודל בזמן אמת כדי להבטיח תצוגה עקבית של האפקטים והדקורטורים החדשים שהוחלו
     */

    private void buildGrid() {
        for (int i=0 ; i< rows ; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel cell = new JLabel();
                cell.setPreferredSize(new Dimension(64, 64));
                cell.setMinimumSize(new Dimension(64, 64));
                cell.setMaximumSize(new Dimension(64, 64));
                AbstractEntity entity = environment.getEntityAt(i, j);
                if (entity != null) {
                    String name = entity.getImageName();
                    cell.setIcon(getImage(name));
                    cell.setToolTipText(entity.toString());
                }
                else
                    cell.setIcon(getImage("ground"));

                final int row = i;
                final int col = j;

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedCell != null) {
                            selectedCell.setBorder(null);
                        }
                        selectedCell =cell;
                        selectedCell.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                        infoPanel.showEntity(environment.getEntityAt(row, col));
                    }
                });

                add(cell);
            }
        }
    }

    /**
     * מתודת עדכון שמופעלת באופן אוטומטי כאשר חל שינוי כלשהו במודל של העולם
     * המתודה מבצעת את רענון המפה והרכיבים הגרפיים בצורה בטוחה על גבי ה-Event Dispatch Thread של Swing כדי למנוע Race Conditions או קריסות תצוגה הנובעות מכך שהסימולציה והישויות רצות ומעדכנות את המידע מתוך תהליכונים עצמאיים ומקבילים ברקע
     */

    @Override
    public void onWorldChanged() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                selectedCell = null;
                removeAll();
                buildGrid();
                revalidate();
                repaint();
            }
        });
    }
}
