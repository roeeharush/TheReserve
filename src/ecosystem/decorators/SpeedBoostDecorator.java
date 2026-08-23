package ecosystem.decorators;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;

/**
 * מחלקה המייצגת אפקט האצה דינמי העוטף ישות חיה במערכת
 * הדקורטור מפעיל את מתודת הפעולה של הישות המקורית פעמיים באותה פעימה מה שגורם ליצור לפעול לנוע ולצרוך אנרגיה במהירות כפולה
 * Pattern: Decorator
 */

public class SpeedBoostDecorator extends EntityDecorator {

    /**
     * בונה דקורטור האצה חדש סביב ישות חיה מוגדרת ומעביר אותה למחלקת האם לצורך אתחול המעטפת והעתקת הנתונים
     * @param decoratedEntity הישות החיה המקורית שעליה מוחל אפקט ההאצה הנוכחי
     */

    public SpeedBoostDecorator(LivingEntity decoratedEntity) {
        super(decoratedEntity);
    }

    /**
     * מפעילה את פעימת הזמן של אפקט ההאצה ומנהלת את הקידום הכפול של הישות העטופה
     * המתודה בודקת קודם את משך חיים של האפקט ואם הוא פג היא עוצרת ואילו בזמן שהאפקט פעיל היא מפעילה את פעולת הישות המקורית פעם ראשונה ובודקת שהיא עדיין בחיים כדי להפעיל אותה פעם שנייה ברצף
     * @param env סביבת העולם שבה הישות המואצת מבצעת את פעולותיה הכפולות
     * @return true אם לפחות אחת מהפעלות הישות המקורית בוצעה בהצלחה
     */

    @Override
    public boolean act(Environment env) {
        boolean isRemoved = super.act(env);
        if (isRemoved) return true;

        boolean firstAct = decoratedEntity.act(env);
        boolean secondAct = false;
        if (((AbstractEntity) decoratedEntity).isAlive()) {
            secondAct = decoratedEntity.act(env);
        }
        return firstAct || secondAct;
    }
}
