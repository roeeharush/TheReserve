package ecosystem.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * מחלקה שמייצגת את מסך הפתיחה הראשי של המשחק
 * החלון מציג תמונת רקע חגיגית וכפתור כניסה ומספק למשתמש את נקודת המפגש הראשונית עם הסימולציה לפני הגדרת המפה
 */
public class SplashScreen extends JFrame {

    /**
     * בונה ומציג את חלון מסך הפתיחה של הסימולציה
     * הבנאי קובע את כותרת חלון המשחק מונע אפשרות לשינוי הגודל מאתחל את התמונה והכפתורים ומציג את החלון במרכז המסך עם הגדרת סגירה מלאה של האפליקציה
     */
    public SplashScreen(){
        super("Welcome to The Reserve!");
        setLayout(new BorderLayout());
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * מאתחלת ומסדרת את התמונה ואת כפתור ההתחלה בתוך מסך הפתיחה
     * המתודה שולפת את תמונת הפתיחה מתוך מנהל התמונות וממקמת אותה במרכז ומגדירה מאזין לכפתור הסטארט שיפתח את חלון הגדרת המפה ויסגור את מסך הפתיחה הנוכחי
     */

    private void initComponents() {
        ImageIcon splashImage = ImageLoader.getImage("start");
        JLabel imageLabel = new JLabel(splashImage);
        add(imageLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Start(SplashScreen.this);
                dispose();
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }
}
