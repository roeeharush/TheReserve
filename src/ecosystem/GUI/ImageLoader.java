package ecosystem.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * מחלקה אחראית על טעינת תמונות מתיקיית המשאבים.
 * הטעינה מתבצעת פעם אחת בלבד לזיכרון.
 */
public class ImageLoader {

    private static final Map<String, ImageIcon> images = new HashMap<>();
    public static final int CELL_SIZE = 64;

    /**
     * טוען את כל התמונות של הפרויקט.
     * יש לקרוא למתודה זו פעם אחת בתחילת התוכנית.
     */
    public static void loadAll() {
        load("Lion",    "Resources/Lion.jpeg");
        load("Deer",    "Resources/Deer.jpeg");
        load("Rabbit",  "Resources/Rabbit.jpeg");
        load("Flower",  "Resources/Flower.jpeg");
        load("OakTree", "Resources/OkaTree.jpeg");
        load("Rock",    "Resources/Rock.jpeg");
        load("Water",   "Resources/Water.jpeg");
    }

    /**
     * טוען תמונה אחת ושומר אותה במפה.
     * @param name שם מזהה לתמונה
     * @param path נתיב לקובץ התמונה
     */
    private static void load(String name, String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image scaled = icon.getImage()
                    .getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
            images.put(name, new ImageIcon(scaled));
        } catch (Exception e) {
            System.err.println("שגיאה בטעינת תמונה: " + path);
        }
    }

    /**
     * מחזיר תמונה לפי שם.
     * @param name שם התמונה
     * @return התמונה, או null אם לא נמצאה
     */
    public static ImageIcon get(String name) {
        return images.get(name);
    }
}