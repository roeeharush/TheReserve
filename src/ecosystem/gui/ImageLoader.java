package ecosystem.gui;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * מחלקה שאחראית על טעינה וניהול של כל קבצי התמונות והאייקונים בפרויקט
 * המחלקה שומרת את התמונות בזיכרון בתוך מפה ומאפשרת לשלוף אותן במהירות לפי השם של הישות
 */
public class ImageLoader {
    private static final Map<String, ImageIcon> images = new HashMap<>();


    /**
     * טוענת את כל התמונות של הישויות והלוגו מתוך תיקיית המשאבים של הפרויקט
     * המתודה עוברת על רשימת השמות של הישויות מתאימה לכל אחת תמונה בגודל של שישים וארבע על שישים וארבע פיקסלים ומכניסה אותה למפה ואז טוענת גם את הלוגו של המשחק
     */

    public static void loadImage(){
        String[] entities = {"Lion", "Deer", "Rabbit", "Flower", "OakTree", "Water", "Rock", "ground"};
        for (String e : entities){
            try {
                ImageIcon icon = new ImageIcon("Resources/" + e + ".jpeg");
                Image scaled = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                images.put(e,new ImageIcon(scaled));
            } catch (Exception ex) {
                System.err.println(" error in load image  " + e);
            }
        }

        try {
            ImageIcon logo = new ImageIcon("Resources/logo.png");
            Image scaled = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            images.put("logo", new ImageIcon(scaled));
        } catch (Exception ex) {
            System.err.println("Error loading logo");
        }

        try {
            ImageIcon splash = new ImageIcon("Resources/start.png");
            Image scaled = splash.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            images.put("start", new ImageIcon(scaled));
        } catch (Exception ex) {
            System.err.println("error loading splash");
        }
    }



    /**
     * שולפת מתוך הזיכרון את האייקון המוקטן והמוכן של הישות על פי שמה
     * @param name שם הישות או המשאב שאת התמונה שלו מבקשים לשלוף
     * @return אובייקט של אייקון המיועד להצגה ברכיבי הממשק הגרפי או null אם התמונה לא קיימת במפה
     */
    public static ImageIcon getImage(String name){
        return images.get(name);
    }

}
