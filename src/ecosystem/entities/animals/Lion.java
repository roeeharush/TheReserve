package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;


/**
 * מחלקה שמייצגת אריה בעולם האקולוגי שלנו
 * האריה הוא טורף חזק שנמצא בראש שרשרת המזון ורודף אחרי הטרף שלו
 */
public class Lion extends Animal {
    private static final double MAX_ENERGY = 500;

    /**
     * בונה אריה חדש במיקום שביקשנו
     * הבנאי מגדיר לאריה את הסימן אל ואת כל הנתונים שלו כמו אנרגיה התחלתית של מאה ואנרגיה מקסימלית של חמש מאות
     * הוא גם קובע שהאריה הוא טורף שרודף אחרי הישויות שהוא רוצה לאכול
     * @param position המיקום שבו האריה מתחיל את המשחק במפה
     */
    public Lion(Position position ) {
        super(position, 'L', true, 100, MAX_ENERGY, new CarnivoreBehavior(),new ChaseMovement());
    }

    public Lion(Position position ,double energy) {
        super(position, 'L', true, energy , MAX_ENERGY, new CarnivoreBehavior(),new ChaseMovement());
    }

    /**
     * מחזיר מחרוזת טקסט עם כל הפרטים של האריה להדפסה
     * @return תיאור של האריה המיקום שלו והאנרגיה שנשארה לו
     */
    @Override
    public String toString(){
        return super.toString();
    }


    /**
     * בודק אם אובייקט אחר הוא אריה שזהה בדיוק לאריה הזה
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר באותה ישות עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String getImageName() { return "Lion"; }

}

