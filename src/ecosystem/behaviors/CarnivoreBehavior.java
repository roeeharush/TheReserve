package ecosystem.behaviors;

import ecosystem.commands.AttackCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.EdibleByCarnivore;

import java.util.List;


/**
 * מחלקה שמייצגת התנהגות של טורף בעולם האקולוגי שלנו
 * המחלקה הזו דואגת שהחיה תחפש רק ישויות שניתנות למאכל על ידי טורפים כמו חיות אחרות למשל
 */
public class CarnivoreBehavior implements FeedingBehavior{

    /**
     * מפעיל את מנגנון האכילה של הטורף
     * הפונקציה סורקת את רשימת היצורים הקרובים ומחפשת מטרה שמתאימה לתפריט של טורפים ואם היא מוצאת כזו היא גורמת לטורף לאכול אותה
     * @param eater החיה הטורפת שמבצעת את הפעולה עכשיו
     * @param nearby רשימת היצורים שנמצאים בטווח הראייה של הטורף מסביב
     * @return true אם הטורף מצא טרף מתאים ואכל אותו בהצלחה false אם לא נמצא שום דבר בשרי בסביבה
     */

    @Override
    public WorldCommand buildEatCommand(Animal eater, List<AbstractEntity> nearby) {
        for (AbstractEntity e : nearby) {
            if (e instanceof EdibleByCarnivore target)
                return new AttackCommand(eater, target);
        }
        return null;
    }
}
