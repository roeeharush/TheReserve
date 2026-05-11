package ecosystem.entities.resources;
import ecosystem.core.Position;

/**
 * מחלקה שמייצגת אבן בעולם האקולוגי שלנו
 * האבן היא משאב סטטי שחוסם תנועה של ישויות אחרות במפה
 */
public class Rock extends Resource {
    private final boolean blocksMovement = true;

    /**
     * בונה אבן חדשה במיקום שביקשנו
     * מגדיר לה את התו איקס ומוודא שהיא נחשבת קיימת במערכת
     * @param position המקום שבו האבן נמצאת על המפה
     */
    public Rock(Position position) {
        super(position, 'X', true);
    }


    /**
     * מחזיר אם המכשול הזה חוסם מעבר של חיות
     * @return true כי אבנים תמיד חוסמות תנועה לפי חוקי הסימולציה
     */
    public boolean getBlocksMovement() {
        return blocksMovement;
    }


    /**
     * הופך את פרטי האבן למחרוזת שאפשר להדפיס
     * @return תיאור של האבן והמיקום שלה
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * בודק אם אובייקט אחר הוא אבן שזהה לאבן הזו
     * @param o האובייקט שמשווים אליו
     * @return true אם הנתונים זהים false אחרת
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }
}
