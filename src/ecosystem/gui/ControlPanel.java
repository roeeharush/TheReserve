package ecosystem.gui;
import javax.swing.*;
import java.awt.*;


/**
 * מחלקה שמייצגת את פאנל כפתורי השליטה בממשק הגרפי של הסימולציה
 * הפאנל מכיל כפתורים שמסודרים בשורה ומאפשרים למשתמש להריץ לעצור לאתחל או להוסיף ישויות לעולם בזמן אמת
 */
    public class ControlPanel extends JPanel {

        private JButton tickButton;
        private JButton runButton;
        private JButton stopButton;
        private JButton resetButton;
        private JButton addEntityButton;
        private JButton sendToPortalButton;


    /**
     * בונה לוח בקרה חדש ומעצב אותו
     * הבנאי קובע את סגנון הסידור של הכפתורים בשורה מגדיר צבע רקע אפור בהיר ומפעיל את יצירת הכפתורים
     */
    public ControlPanel() {
            setLayout(new FlowLayout( ));
            setBackground(new Color(225, 225, 225));
            initButtons();
        }


    /**
     * מייצרת את כל כפתורי השליטה של המערכת ומכניסה אותם לתוך הפאנל
     * הכפתורים שנוצרים הם הרצת פעימה אחת הרצה רציפה עצירה אתחול והוספת ישות חדשה
     */
        private void initButtons() {
            tickButton = new JButton("Tick");
            runButton = new JButton("Run");
            stopButton = new JButton("Stop");
            resetButton = new JButton("Reset");
            addEntityButton = new JButton("Add Entity");
            sendToPortalButton = new JButton("Spawn");

            add(tickButton);
            add(runButton);
            add(stopButton);
            add(resetButton);
            add(addEntityButton);
            add(sendToPortalButton);
        }


    /**
     * מחזיר את כפתור הפעלת פעימת הזמן הבודדת
     * @return כפתור הטיק
     */
        public JButton getTickButton(){
            return tickButton;
        }


    /**
     * מחזיר את כפתור ההרצה הרציפה של הסימולציה
     * @return כפתור הראן
     */
        public JButton getRunButton() {
            return runButton;
        }


    /**
     * מחזיר את כפתור עצירת הסימולציה
     * @return כפתור הסטופ
     */
        public JButton getStopButton() {
            return stopButton;
        }


    /**
     * מחזיר את כפתור אתחול העולם מחדש
     * @return כפתור הריסט
     */
        public JButton getResetButton() {
            return resetButton;
        }


    /**
     * מחזיר את כפתור פתיחת חלון הוספת ישות חדשה
     * @return כפתור הוספת הישות
     */
     public JButton getAddEntityButton() {
            return addEntityButton;
        }


    /**
     * מחזיר את כפתור שיגור הישות לפורטל הרשת
     * @return כפתור השיגור לפורטל
     */
     public JButton getSendToPortalButton() {
         return sendToPortalButton;
     }
    }

