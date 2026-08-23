package ecosystem.decorators;
import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

/**
 * מחלקה המייצגת אפקט רעל דינמי העוטף ישות חיה במערכת
 * הדקורטור מפעיל את לוגיקת הפעולה המקורית של הישות ומיד לאחר מכן מפחית ממנה כמות אנרגיה קבועה בכל פעימה כקנס על הרעלת האובייקט
 * Pattern: Decorator
 */

public class PoisonedDecorator extends EntityDecorator {

    /**
     * בונה דקורטור רעל חדש סביב ישות חיה מוגדרת ומעביר אותה למחלקת האם לצורך אתחול המעטפת והעתקת הנתונים
     * @param decoratedEntity הישות החיה המקורית שעליה מוחל אפקט הרעל הנוכחי
     */

    public PoisonedDecorator(LivingEntity decoratedEntity) {
        super(decoratedEntity);
    }

    /**
     * מפעילה את פעימת הזמן של אפקט הרעל ומנהלת את הפחתת האנרגיה של הישות העטופה
     * המתודה קוראת קודם לוגיקת מחלקת האם כדי לבדוק את משך חיים של האפקט ואם הוא פג היא עוצרת ואילו בזמן שהאפקט פעיל היא מריצה את פעולת הישות המקורית ומחסירה ממנה חמש יחידות אנרגיה נוספות תוך שמירה שלא לרדת מתחת לאפס
     * @param env סביבת העולם שבה הישות המורעלת פועלת ומאבדת אנרגיה
     * @return true אם הפעולה והחלת קנס הרעל בוצעו בהצלחה
     */

    @Override
    public boolean act(Environment env) {
        boolean isRemoved = super.act(env);
        if (isRemoved) return true;

        decoratedEntity.act(env);
        ((LivingEntity) decoratedEntity).setEnergy(
                Math.max(0, ((LivingEntity) decoratedEntity).getEnergy() - 5)
        );
        return true;
    }
}