package ecosystem.entities.animals;
import ecosystem.behaviors.*;
import ecosystem.core.Position;
import ecosystem.interfaces.Predator;

/**
 * מחלקה שמייצגת אריה בעולם האקולוגי שלנו
 * האריה הוא טורף חזק שנמצא בראש שרשרת המזון ורודף אחרי הטרף שלו
 */
public class Lion extends Animal implements Predator {
    private static final double MAX_ENERGY = 500;
    private static final double DEFAULT_ENERGY = 100;


    /**
     * בונה אריה חדש במיקום שביקשנו
     * הבנאי מגדיר לאריה את הסימן אל ואת כל הנתונים שלו כמו אנרגיה התחלתית של מאה ואנרגיה מקסימלית של חמש מאות
     * הוא גם קובע שהאריה הוא טורף שרודף אחרי הישויות שהוא רוצה לאכול
     * @param position המיקום שבו האריה מתחיל את המשחק במפה
     */
    public Lion(Position position) {
        this(position, DEFAULT_ENERGY);
    }

    /**
     * בונה אריה חדש במיקום מוגדר וקובע לו רמת אנרגיה התחלתית מותאמת אישית
     * בנאי זה משמש בעיקר בעת יצירת צאצאים חדשים במערכת ומגדיר את אסטרטגיות המרדף והתזונה הטורפת של החיה
     * @param position המיקום שבו האריה החדש ייוולד על גבי המפה
     * @param energy כמות האנרגיה ההתחלתית שאיתה האריה מתחיל את חייו
     */
    public Lion(Position position, double energy) {
        super(position, 'L', true, energy, MAX_ENERGY, new CarnivoreBehavior(), new ChaseMovement());
    }


    /**
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() { return "Lion"; }
}

