package ecosystem.behaviors;

import ecosystem.commands.MoveCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.interfaces.EdibleByCarnivore;

import java.util.List;

/**
 * מחלקה שמייצגת אסטרטגיה של תנועת מרדף בעולם שלנו
 * הישות מחפשת טרף פוטנציאלי בסביבה שלה ומנסה לצמצם את המרחק אליו כדי לתפוס אותו
 */
public class ChaseMovement implements MovementStrategy{
    /**
     * מבצע את תנועת המרדף של הישות לכיוון המטרה
     * הפונקציה סורקת את הישויות הקרובות ואם היא מוצאת יצור שניתן למאכל היא מחשבת את הצעד הבא לכיוון שלו ומנסה לעבור אליו רק אם המשבצת פנויה
     * @param entity הישות שרודפת אחרי המטרה
     * @param env העולם שבו הישות בודקת את הסביבה ומבצעת את התנועה בפועל
     * @return true אם הישות זיהתה מטרה והצליחה להתקרב אליה בצעד אחד false אם לא נמצא טרף או שהדרך חסומה
     */

    @Override
    public WorldCommand buildMoveCommand(AbstractEntity entity, Environment env) {
        List<AbstractEntity>  nearbyEntities = env.getNearbyEntities(entity.getPosition());
        for (AbstractEntity e : nearbyEntities){
            if (e instanceof EdibleByCarnivore){
                Position myPos = entity.getPosition();
                Position targetPos = e.getPosition();

                int myRow = myPos.getRow();
                int myCol = myPos.getCol();

                if (targetPos.getRow() > myPos.getRow())
                    myRow++;
                else if (targetPos.getRow() < myPos.getRow())
                    myRow--;
                if (targetPos.getCol() > myPos.getCol())
                    myCol++;
                else if (targetPos.getCol() < myPos.getCol())
                    myCol--;

                Position newPos = new Position(myRow, myCol);
                if (env.isPositionFree(newPos))
                    return new MoveCommand(entity,newPos);
            }
        }
        return null;

    }
}
