package ecosystem.commands;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import java.util.logging.Logger;

/**
 * מחלקה המייצגת פקודת תנועה של ישות למיקום חדש בעולם הסימולציה
 * פקודה זו מנוהלת ומבוצעת על ידי המנוע בצורה מסונכרנת כדי להבטיח שינוי בטוח של מיקומי הישויות במפה ומניעת מצב שבו שתי ישויות תופסות את אותה המשבצת בו זמנית
 */

public class MoveCommand implements WorldCommand{
    private final AbstractEntity entity;
    private final Position newPosition;
    private static final Logger logger = Logger.getLogger(MoveCommand.class.getName());


    /**
     * בונה פקודת תנועה חדשה ומגדירה את הישות המיועדת להזזה ואת מיקולה החדש במפה
     * @param entity הישות שרוצים להזיז במפה
     * @param newPosition המיקום החדש שאליו הישות מנסה להגיע
     */

    public MoveCommand(AbstractEntity entity, Position newPosition) {
        this.entity = entity;
        this.newPosition = newPosition;
    }

    /**
     * מבצעת את פקודת התנועה וההזזה של הישות בתוך העולם בצורה בטוחה ומסונכרנת
     * המתודה מוודאת שהמיקום החדש תקין והישות המדוברת עדיין בחיים ולאחר מכן מעדכנת את המפה ואם הפעולה הצליחה היא רושמת את אירוע התנועה במערכת הלוגים של המנוע
     * @param env סביבת העולם שבה מתבצעת התנועה ומתעדכנים המיקומים במפה
     * @return true אם פעולת ההזזה הצליחה והמיקום החדש היה פנוי או false אם התנאים נכשלו והפעולה לא התבצעה
     */

    @Override
    public boolean execute(Environment env) {
        if (entity == null || newPosition == null || !entity.isAlive())
            return false;
        boolean moved = env.moveEntity(entity, newPosition);
        if (moved) {
            logger.info("Moved: " + entity.getClass().getSimpleName() + " To Position " + newPosition);
        }
        return moved;
    }


    /**
     * מחזירה את המיקום החדש שאליו הפקודה מתכננת להזיז את הישות
     * @return המיקום היעד של פקודת התנועה
     */
    public Position getNewPosition() {
        return newPosition;
    }
}
