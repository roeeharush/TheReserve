package ecosystem.entities;
import ecosystem.core.Position;

/**
 * מחלקה אבסטרקטית שמייצגת את הישויות הסטטיות בעולם שלנו
 * מדובר בדברים כמו סלעים או מים שלא זזים ולא משתנים לאורך זמן
 */
public abstract class StaticEntity extends AbstractEntity {

    /**
     * בונה ישות סטטית חדשה במיקום שנבחר עם הסימול המתאים
     * המתודה קוראת למחלקת האם כדי להגדיר את הנתונים הבסיסיים
     * @param position המיקום של הישות על המפה
     * @param symbol התו שמייצג את הישות
     * @param alive האם הישות קיימת במערכת
     */
    public StaticEntity(Position position, char symbol, boolean alive) {
        super(position, symbol, alive);
    }


    /**
     * הופך את פרטי הישות למחרוזת טקסט ומוסיף שהיא חסרת אנרגיה כי היא דוממת
     * @return תיאור של הישות שכולל את המיקום ואת העובדה שאין לה אנרגיה
     */
    @Override
    public String toString() {
        return super.toString() + " Energy: do not exist ";
    }


    /**
     * בודק אם ישות סטטית אחרת זהה לישות הנוכחית
     * הבדיקה מוודאת שמדובר באותו סוג של אובייקט ושהנתונים הבסיסיים שלו זהים
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר בישות סטטית זהה false אחרת
     */
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if (!super.equals(o))
            return false;
        return o instanceof StaticEntity;
    }
}
