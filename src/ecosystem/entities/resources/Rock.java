package ecosystem.entities.resources;
import ecosystem.core.Position;

/**
 * מחלקה שמייצגת אבן בעולם האקולוגי שלנו
 * האבן היא משאב סטטי שחוסם תנועה של ישויות אחרות במפה
 */
public class Rock extends Resource {

    /**
     * בונה אבן חדשה במיקום שביקשנו
     * מגדיר לה את התו איקס ומוודא שהיא נחשבת קיימת במערכת
     * @param position המקום שבו האבן נמצאת על המפה
     */
    public Rock(Position position) {
        super(position, 'X', true);
    }


    /**
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() {
        return "Rock";
    }
}
