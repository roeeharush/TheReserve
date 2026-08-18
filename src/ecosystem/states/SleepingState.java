package ecosystem.states;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

/**
 * מחלקה המייצגת את מצב השינה של ישות חיה במערכת
 * במצב זה היצור נמצא בחוסר ניידות מוחלט למשך מספר פעימות קבוע מראש ומנצל את הזמן כדי למלא מחדש את רמות האנרגיה שלו עד להתעוררות וחזרה למצב מנוחה
 * Pattern: State Concrete
 */

public class SleepingState implements EntityState {

    public static final int DURATION_SLEEP = 5;
    private int remainingTicks = DURATION_SLEEP;

    /**
     * מפעילה את לוגיקת ההתנהגות של היצור בזמן שינה ומנהלת את מונה פעימות ההתאוששות שלו
     * המתודה מעלה את רמת האנרגיה של היצור בשתי יחידות בכל תור תוך שמירה שלא לחרוג מהרמה המקסימלית ומורידה יחידה אחת ממונה זמן השינה הנותר וכאשר המונה מגיע לאפס היא מעבירה את היצור חזרה למצב מנוחה
     * @param e הישות החיה שנמצאת כעת במצב שינה ומטעינה אנרגיה
     * @param env סביבת העולם שבה הישות ממוקמת בזמן השינה
     */

    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.min(e.getMaxEnergy(), e.getEnergy() + 2));
        remainingTicks--;

        if (remainingTicks <= 0) {
            e.setState(new IdleState());
        }
    }

    /**
     * מחזירה סירוב תנועה גורף עבור היצור החי בזמן שהוא ישן במערכת הסימולציה
     * מתודה זו מבטיחה בצורה פולימורפית כי מנוע הסימולציה או רכיבי התנועה בעולם לא יוכלו להזיז את היצור ממקומו הנוכחי על גבי המפה לאורך כל משך זמן האפקט
     * @return false באופן קבוע כדי למנוע מהיצור לבצע תנועה בתור הנוכחי
     */

    @Override
    public boolean canMove() { return false; }
}