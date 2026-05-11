package ecosystem.entities.resources;
import ecosystem.core.Position;
import ecosystem.interfaces.Consumable;

/**
 * מחלקה שמייצגת מים בעולם האקולוגי שלנו
 * המים הם משאב סטטי שחיות יכולות לשתות ממנו כדי למלא אנרגיה
 */
public class Water extends Resource implements Consumable {
    private static final double NUTRITION_VALUE = 100.0;

    /**
     * בונה מקור מים חדש במיקום שנבחר
     * מגדיר למים את הסימן W ומוודא שהם קיימים במפה
     * @param position המקום שבו המים נמצאים על המפה
     */
    public Water(Position position){
        super(position,'W',true);
    }

    /**
     * מחזיר את הערך התזונתי של המים
     *מים נותנים מאה יחידות אנרגיה למי ששותה אותם
     * @return כמות האנרגיה שהמים מספקים
     */
    @Override
    public double getNutritionValue(){
        return NUTRITION_VALUE;
    }

    /**
     * מה קורה למים כשישות שותה מהם
     * המים לא נעלמים ופשוט מחזירים שהפעולה עברה
     * @return true תמיד כי הפעולה הצליחה והמים נשארים במקום
     */
    @Override
    public boolean onConsumed(){return true;}


    /**
     * הופך את פרטי המים למחרוזת שאפשר להדפיס
     * @return תיאור של המים והמיקום שלהם במפה
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * בודק אם אובייקט אחר הוא מקור מים שזהה לזה
     * @param o האובייקט שמשווים אליו
     * @return true אם מדובר באותם מים עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

}
