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

    /**
     * בודק אם אובייקט אחר הוא משאב שזהה למשאב הנוכחי
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר באותה ישות false אחרת
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    /**
     * הופך את פרטי המשאב למחרוזת שאפשר להדפיס
     * @return תיאור של סוג המשאב והמיקום שלו במפה
     */
    @Override
    public String toString(){
        return super.toString();
    }

}
