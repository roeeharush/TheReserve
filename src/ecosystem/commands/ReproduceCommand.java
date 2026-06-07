package ecosystem.commands;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import java.util.logging.Logger;

/**
 * מחלקה המייצגת פקודת רבייה והוספת ישות חדשה לעולם הסימולציה
 * פקודה זו מנוהלת ומבוצעת על ידי המנוע בצורה מסונכרנת כדי להבטיח הוספה בטוחה של צאצאים או ישויות חדשות למפה ומניעת קונפליקטים בגישה למבני הנתונים המשותפים
 */

public class ReproduceCommand  implements WorldCommand{
    private final AbstractEntity newEntity;
    private static final Logger logger = Logger.getLogger(ReproduceCommand.class.getName());

    /**
     * בונה פקודת רבייה חדשה ומגדירה את הישות החדשה שיש להוסיף לעולם
     * @param newEntity הישות החדשה או הצאצא שנוצר וצריך להיוולד במפה
     */
    public ReproduceCommand(AbstractEntity newEntity) {
        this.newEntity = newEntity;
    }


    /**
     * מבצעת את פקודת הרבייה והוספת הישות לתוך העולם בצורה בטוחה ומסונכרנת
     * המתודה מוודאת שהישות החדשה קיימת ולאחר מכן מנסה להכניס אותה למפה ואם הפעולה הצליחה היא רושמת את אירוע היצירה במערכת הלוגים של המנוע
     * @param env סביבת העולם שבה מתבצעת ההוספה ומתעדכנת רשימת הישויות
     * @return true אם פעולת ההוספה הצליחה והמיקום של הישות החדשה היה פנוי או false אם התנאים נכשלו והפעולה לא התבצעה
     */

    @Override
    public boolean execute(Environment env) {
        if (newEntity == null)
            return false;
        boolean added = env.addEntity(newEntity);
        if (added) {
            logger.info("Created: " + newEntity.getClass().getSimpleName()
                    + " In Position " + newEntity.getPosition());
        }
        return added;
    }
}

