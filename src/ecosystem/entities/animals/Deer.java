package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;


/**
 * מחלקה שמייצגת צבי בעולם האקולוגי שלנו
 * הצבי הוא חיה צמחונית שנוטה לברוח כשמתקרבים אליה
 */
public class Deer extends Animal {
    private static final double MAX_ENERGY = 300;


    /**
     * בונה צבי חדש במיקום שביקשנו
     * הבנאי מגדיר לצבי את הסימן די ואת כל הנתונים שלו כמו אנרגיה התחלתית של שבעים ואנרגיה מקסימלית של שלוש מאות
     * הוא גם קובע שהצבי אוכל עשב ובורח מאיומים
     * @param position המיקום שבו הצבי מתחיל את המשחק במפה
     */
    public Deer( Position position) {
        super(position, 'D', true, 70, MAX_ENERGY,new HerbivoreBehavior() , new EscapeMovement());

    }

    /**
     * מחזיר מחרוזת טקסט עם כל הפרטים של הצבי להדפסה
     * @return תיאור של הצבי המיקום שלו והאנרגיה שנשארה לו
     */
    @Override
    public String toString(){
        return this.toString();
    }

    /**
     * בודק אם אובייקט אחר הוא צבי שזהה בדיוק לצבי הזה
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר באותה ישות עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String getImageName() { return "Deer"; }



}
