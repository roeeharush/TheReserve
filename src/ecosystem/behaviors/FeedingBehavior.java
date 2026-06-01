package ecosystem.behaviors;

import ecosystem.commands.WorldCommand;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import java.util.List;


/**
 * ממשק שמגדיר את הכללים לאיך חיות אוכלות בעולם שלנו
 * הממשק הזה מאפשר לנו להחליף את צורת האכילה של החיה בלי לשנות את הקוד שלה עצמה
 */
public interface FeedingBehavior {
    /**
     * הפעולה הבסיסית שכל אסטרטגיית אכילה חייבת לממש
     * @param eater החיה שרוצה לאכול עכשיו
     * @param nearby רשימה של כל היצורים שנמצאים קרוב לחיה בסביבה
     * @return true אם החיה מצאה משהו לאכול והצליחה לבצע את הפעולה
     */
    WorldCommand buildEatCommand(Animal eater, List<AbstractEntity> nearby);
}


