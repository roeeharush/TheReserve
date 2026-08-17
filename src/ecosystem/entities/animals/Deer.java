package ecosystem.entities.animals;
import ecosystem.behaviors.*;
import ecosystem.core.Position;


/**
 * מחלקה שמייצגת צבי בעולם האקולוגי שלנו
 * הצבי הוא חיה צמחונית שנוטה לברוח כשמתקרבים אליה
 */
public class Deer extends Animal {
    private static final double MAX_ENERGY = 300;
    private static final double DEFAULT_ENERGY = 70;


    /**
     * בונה צבי חדש במיקום שביקשנו
     * הבנאי מגדיר לצבי את הסימן די ואת כל הנתונים שלו כמו אנרגיה התחלתית של שבעים ואנרגיה מקסימלית של שלוש מאות
     * הוא גם קובע שהצבי אוכל עשב ובורח מאיומים
     * @param position המיקום שבו הצבי מתחיל את המשחק במפה
     */
    public Deer(Position position) {
        this(position, DEFAULT_ENERGY);
    }


    /**
     * בונה צבי חדש במיקום מוגדר וקובע לו רמת אנרגיה התחלתית מותאמת אישית
     * בנאי זה משמש בעיקר בעת יצירת צאצאים חדשים במערכת ומגדיר את אסטרטגיות התנועה והתזונה של החיה
     * @param position המיקום שבו הצבי החדש נמצא על גבי המפה
     * @param energy כמות האנרגיה ההתחלתית שאיתה הצבי מתחיל את חייו
     */
    public Deer(Position position, double energy) {
        super(position, 'D', true, energy, MAX_ENERGY, new HerbivoreBehavior(), new EscapeMovement());
    }


    /**
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() { return "Deer"; }



}
