package ecosystem.entities.resources;
import ecosystem.core.Position;
import ecosystem.entities.StaticEntity;

/**
 * מחלקה אבסטרקטית שמייצגת את כל המשאבים הדוממים בעולם שלנו
 * המשאבים האלה הם ישויות סטטיות שלא זזות ולא משתנות במהלך הסימולציה
 */
public abstract class Resource extends StaticEntity {

    /**
     * בונה משאב חדש עם הנתונים הבסיסיים שלו
     * @param position המיקום של המשאב על המפה
     * @param symbol התו שמייצג את המשאב בהדפסה
     * @param alive האם המשאב קיים ופעיל במערכת
     */
    public Resource(Position position, char symbol, boolean alive){
        super(position, symbol, alive);
    }
}
