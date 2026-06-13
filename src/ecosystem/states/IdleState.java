package ecosystem.states;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;


/**
 * מחלקה המייצגת את מצב המנוחה הרגיל של ישות חיה במערכת
 * במצב זה היצור צורך כמות אנרגיה מינימלית בלבד בכל פעימה ומסוגל לנוע בחופשיות כאשר הוא מנטר את רמות האנרגיה שלו כדי לזהות נפילה אל מתחת לשלושים אחוזים המצריכה מעבר למצב רעב פעיל
 * Pattern: State Concrete
 */

public class IdleState implements EntityState {

    /**
     * מפעילה את לוגיקת ההתנהגות של היצור בזמן מנוחה ומנהלת את מעברי המצבים שלו במערכת
     * המתודה מפחיתה יחידת אנרגיה אחת בלבד בכל תור ובודקת האם היצור הגיע לפינת הלוח כדי להעביר אותו למצב שינה או לחלופין האם מדד האנרגיה שלו צנח מתחת לשלושים אחוזים מהאנרגיה המקסימלית מה שמחייב מעבר מיידי למצב רעב
     * @param e הישות החיה שנמצאת כעת במצב מנוחה ומאבדת אנרגיה מינימלית
     * @param env סביבת העולם המשמשת לבדיקת תנאי המיקום והגעה לפינות הלוח
     */

    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.max(0, e.getEnergy() - 1));

        if (env.isAtCorner(e.getPosition())) {
            e.setState(new SleepingState());
        } else if (e.getEnergy() < e.getMaxEnergy() * 0.3) {
            e.setState(new HungryState());
        }
    }

    /**
     * מחזירה אישור תנועה עבור היצור החי בזמן מנוחה במערכת הסימולציה
     * מתודה זו קובעת כי גם כאשר היצור אינו רעב באופן אקטיבי הוא עדיין שומר על ניידות מלאה ומורשה לשנות את מיקומו על גבי המפה בכל פעימת זמן
     * @return true באופן קבוע כדי לאפשר ליצור לנוע בתור הנוכחי
     */

    public boolean canMove() { return true; }
}