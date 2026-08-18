package ecosystem.commands;
import ecosystem.core.Environment;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;
import java.util.logging.Logger;

/**
 * מחלקה המייצגת פקודת תקיפה ואכילה של חיה בעולם הסימולציה
 * פקודה זו נכנסת לתור הפעולות ומבוצעת על ידי המנוע בצורה מסונכרנת כדי להבטיח שרק חיה אחת יכולה לאכול טרף ספציפי באותו הזמן
 */

public class AttackCommand implements WorldCommand{
    private final Animal animal;
    private final Consumable target;
    private static final Logger logger = Logger.getLogger(AttackCommand.class.getName());

    /**
     * בונה פקודת תקיפה חדשה ומקשרת בין החיה התוקפת לטרף שלה
     * @param animal החיה המבצעת את פעולת התקיפה והאכילה
     * @param target הישות הנאכלת המממשת את ממשק הישויות הניתנות לצריכה
     */
    public AttackCommand(Animal animal , Consumable target){
        this.animal = animal;
        this.target = target;
    }


    /**
     * מבצעת את פקודת התקיפה והאכילה על העולם בצורה בטוחה ומסונכרנת
     * המתודה מוודאת שהחיה התוקפת והטרף עדיין בחיים ולאחר מכן מפעילה את לוגיקת האכילה ואם הפעולה הצליחה היא רושמת את האירוע במערכת הלוגים של המנוע
     * @param env סביבת העולם שבה מתבצעת הפעולה ומתעדכנים מבני הנתונים
     * @return true אם פעולת האכילה הצליחה והטרף נצרך או false אם אחד התנאים נכשל והפעולה לא התבצעה
     */
    @Override
    public boolean execute(Environment env) {
        if (animal == null || target == null || !animal.isAlive() )
            return false;
        if (!target.isAlive())
            return false;
        boolean ate = animal.eat(target);
        if (ate)
            logger.info("Ate: " + animal.getClass().getSimpleName() + " Ate " + target.getClass().getSimpleName());
        return ate;
    }
}
