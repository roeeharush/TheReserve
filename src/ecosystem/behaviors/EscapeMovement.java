package ecosystem.behaviors;

import ecosystem.commands.MoveCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.interfaces.Predator;

import java.util.List;

/**
 * מחלקה שמייצגת אסטרטגיה של תנועת בריחה בעולם שלנו
 * הישות מזהה טורפים אמיתיים בסביבה הקרובה שלה ומנסה להתרחק מהם כמה שיותר מהר כדי לשמור על עצמה
 */
public class EscapeMovement implements MovementStrategy {

    /**
     * מבצע את תנועת הבריחה של הישות לפי המיקום של היצורים מסביב
     * הפונקציה סורקת את הסביבה ואם היא מוצאת מטרה היא מחשבת מיקום חדש שנמצא בכיוון ההפוך ממנה ומנסה לעבור אליו רק אם הוא פנוי במפה
     * @param entity הישות שמנסה לברוח עכשיו מהאיום
     * @param env העולם שבו הישות בודקת את הסביבה ומבצעת את התנועה בפועל
     * @return פקודת תנועה בכיוון ההפוך מהאיום, או null אם לא נמצא איום או שהדרך חסומה*/

    @Override
    public WorldCommand buildMoveCommand(AbstractEntity entity, Environment env) {
        List<AbstractEntity> nearbyEntities = env.getNearbyEntities(entity.getPosition());
        Position myPos = entity.getPosition();
        AbstractEntity closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (AbstractEntity e : nearbyEntities) {
            if (e instanceof Predator) {
                int distance = myPos.distanceTo(e.getPosition());
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = e;
                }
            }
        }
        if (closest == null)
            return null;

        Position targetPos = closest.getPosition();
        int myRow = myPos.getRow();
        int myCol = myPos.getCol();
        if (targetPos.getRow() > myPos.getRow())
            myRow--;
        else if (targetPos.getRow() < myPos.getRow())
            myRow++;
        if (targetPos.getCol() > myPos.getCol())
            myCol--;
        else if (targetPos.getCol() < myPos.getCol())
            myCol++;

        Position newPos = new Position(myRow, myCol);
        if (env.isPositionFree(newPos))
            return new MoveCommand(entity, newPos);
        return null;
    }
}


