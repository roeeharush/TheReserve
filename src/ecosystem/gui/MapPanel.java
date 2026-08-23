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
    private JLabel[][] cells;


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
     * בונה את רשת המשבצות הגרפית של המפה בפעם הראשונה בלבד, ומצמידה מאזיני לחיצה קבועים לכל תא
     * המתודה עוברת בלולאה על כל המשבצות, יוצרת עבור כל אחת תווית עם אייקון מתאים, שומרת הפניה אליה במערך cells לצורך עדכונים עתידיים, ומצמידה מאזין לחיצה השולף את הישות ישירות מתוך המודל בזמן אמת
     */
    private void buildGrid() {
        cells = new JLabel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel cell = new JLabel();
                cell.setPreferredSize(new Dimension(64, 64));
                cell.setMinimumSize(new Dimension(64, 64));
                cell.setMaximumSize(new Dimension(64, 64));
                updateCellIcon(cell, i, j);

                final int row = i;
                final int col = j;

                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedCell != null) {
                            selectedCell.setBorder(null);
                        }
                        selectedCell = cell;
                        selectedCell.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                        infoPanel.showEntity(environment.getEntityAt(row, col));
                    }
                });

                cells[i][j] = cell;
                add(cell);
            }
        }
    }


    /**
     * מרעננת את כל תאי הרשת הקיימים במקום להרוס ולבנות אותם מחדש
     * המתודה עוברת על כל תא קיים במערך cells ומעדכנת את האייקון שלו בהתאם למצב הנוכחי של המודל, תוך שמירה על הבחירה הנוכחית ועל מאזיני הלחיצה הקיימים
     */
    private void refreshGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                updateCellIcon(cells[i][j], i, j);
            }
        }
    }


    /**
     * מעדכנת את האייקון וטקסט העזרה של תא בודד לפי הישות שנמצאת כרגע במיקום המתאים לו במודל
     * @param cell תא הרשת שיש לעדכן
     * @param row שורת המיקום שממנה שולפים את הישות העדכנית
     * @param col עמודת המיקום שממנה שולפים את הישות העדכנית
     */
    private void updateCellIcon(JLabel cell, int row, int col) {
        AbstractEntity entity = environment.getEntityAt(row, col);
        if (entity != null) {
            cell.setIcon(getImage(entity.getImageName()));
            cell.setToolTipText(entity.toString());
        } else {
            cell.setIcon(getImage("ground"));
            cell.setToolTipText(null);
        }
    }


    /**
     * מתודת עדכון שמופעלת באופן אוטומטי כאשר חל שינוי כלשהו במודל של העולם
     * המתודה מרעננת את האייקונים של כל התאים הקיימים בצורה בטוחה על גבי ה-Event Dispatch Thread של Swing, בלי להרוס ולבנות מחדש את הרשת - כך נשמרת גם בחירת התא הנוכחית
     */
    @Override
    public void onWorldChanged() {
        SwingUtilities.invokeLater(() -> {
            refreshGrid();
            revalidate();
            repaint();
        });
    }
}
