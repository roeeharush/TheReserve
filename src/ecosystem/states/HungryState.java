package ecosystem.states;
import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

/**
 * מחלקה המייצגת את מצב הרעב הפעיל של ישות חיה במערכת
 * במצב זה היצור צורך כמות אנרגיה מוגברת בכל פעימה ומסוגל לנוע בחופשיות כדי לחפש מזון כאשר הוא מנטר באופן רציף את רמת האנרגיה שלו ואת מיקומו הגיאוגרפי כדי לבצע מעברי מצב דינמיים לשינה או למנוחה
 * Pattern: State Concrete
 */

public class HungryState implements EntityState {
    private static final double IDLE_THRESHOLD = 0.8;


    /**
     * מפעילה את הלוגיקת התנהגות של היצור בזמן רעב ומנהלת את מעברי המצבים שלו
     * המתודה מפחיתה חמש יחידות אנרגיה בכל תור ובודקת בצורה פולימורפית האם היצור הגיע לאחת מפינות הלוח כדי להעביר אותו למצב שינה ואם רמת האנרגיה שלו גבוהה משמונים אחוזים היא מעבירה אותו למצב מנוחה
     * @param e הישות החיה שנמצאת כעת במצב רעב ומאבדת אנרגיה
     * @param env סביבת העולם המשמשת לבדיקת הגעה לפינות המפה
     */

    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.max(0, e.getEnergy() - 5));

        if (env.isAtCorner(e.getPosition())) {
            e.setState(new SleepingState());
        } else if (e.getEnergy() > e.getMaxEnergy() * IDLE_THRESHOLD) {
            e.setState(new IdleState());
        }
    }

    /**
     * מחזירה אישור תנועה גורף עבור היצור החי במערכת הסימולציה
     * מתודה זו קובעת כי בזמן שהיצור נמצא במצב רעב הוא מחויב להישאר נייד כדי שיוכל להמשיך לתפקד ולחפש משאבי מזון על גבי הלוח
     * @return true באופן קבוע כדי לאפשר ליצור לנוע בתור הנוכחי
     */

    @Override
    public boolean canMove() { return true; }
}
