package ecosystem.behaviors;
import ecosystem.commands.AttackCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;
import java.util.List;

/**
 * מחלקה שמגדירה איך מתנהגת חיה צמחונית כשהיא רעבה
 * המחלקה הזו דואגת שהחיה תחפש רק דברים שאוכל עשב באמת יכול לעכל כמו צמחים למשל
 */
public class HerbivoreBehavior implements FeedingBehavior{

    /**
     * מפעיל את מנגנון האכילה של החיה הצמחונית
     * הפונקציה עוברת על כל הישויות שנמצאות מסביב לחיה ומחפשת יצור שמתאים למאכל של צמחוניים ואם היא מוצאת אחד כזה היא גורמת לחיה לאכול אותו
     * @param eater החיה שרוצה לאכול עכשיו
     * @param nearby רשימה של כל היצורים שנמצאים קרוב לחיה בסביבה
     * @return true אם החיה מצאה משהו מתאים ואכלה אותו false אם לא היה שום דבר צמחוני בסביבה
     */

    @Override
    public WorldCommand buildEatCommand(Animal eater, List<AbstractEntity> nearby) {
        for (AbstractEntity e : nearby){
            if (e instanceof EdibleByHerbivore)
                return new AttackCommand(eater, (Consumable) e);
        }
        return null;
    }
}
