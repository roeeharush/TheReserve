package ecosystem.behaviors;

import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

/**
 * ממשק שמגדיר את החוקים לכל סוגי התנועה בעולם שלנו
 * הממשק הזה מאפשר לנו לקבוע לכל חיה דרך אחרת לזוז בלי לשנות את הקוד של החיה עצמה
 */
public interface MovementStrategy {
    /**
     * הפעולה הבסיסית שכל אסטרטגיית תנועה חייבת לממש
     * @param entity הישות שצריכה לזוז עכשיו
     * @param env העולם שבו הישות נמצאת ובודקת לאן אפשר ללכת
     * @return true אם התנועה הצליחה והישות באמת זזה למקום חדש
     */
    WorldCommand buildMoveCommand(AbstractEntity entity, Environment env);
}
