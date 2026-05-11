package ecosystem.entities.plants;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import java.util.Random;

/**
 * מחלקה שמייצגת עץ אלון בעולם האקולוגי שלנו
 * עץ אלון הוא צמח עם הרבה אנרגיה שגדל לאט ומחזיק מעמד הרבה זמן
 */
public class OakTree extends Plant {
    private static final double INITINAL_ENERGY = 80.0;
    private static final double MAX_ENERGY = 120.0;
    private static final double GROW_RATE = 2.0;
    private static final double REPRODUCTION_CHANCE = 0.05;


    /**
     * יוצר עץ אלון חדש במיקום שנבחר
     * הבנאי מגדיר לעץ את הסימן T ואת כל ערכי האנרגיה והגדילה המיוחדים שלו
     * @param position המקום שבו העץ נשתל על המפה
     */
    public OakTree(Position position){
        super(position,'T',true,INITINAL_ENERGY,MAX_ENERGY,GROW_RATE,REPRODUCTION_CHANCE);
    }

    /**
     * מנגנון הרבייה של עץ האלון
     * יש סיכוי של חמישה אחוז לייצר עץ חדש בכל תור
     * העץ מחפש מקום פנוי באחד מארבעת הכיוונים הצמודים אליו במרחק של צעד אחד ויוצר שם עץ חדש
     * @param env העולם שבו העץ מנסה להפיץ את עצמו
     * @return true אם נוצר עץ חדש באחד התאים השכנים
     */
    @Override
    public boolean reproduce(Environment env){
        Random chance = new Random();
        double result = chance.nextDouble();


        Position position = this.getPosition();
        if(result <= REPRODUCTION_CHANCE){
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if(env.isPositionFree(op)){
                    OakTree newOakTree = new OakTree(op);
                    env.addEntity(newOakTree);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * בודק אם אובייקט אחר הוא עץ אלון שזהה לעץ הזה
     * @param o האובייקט שמשווים אליו
     * @return true אם מדובר באותו עץ עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof OakTree))
            return false;
        return super.equals(o);
    }

    /**
     * מוציא את הפרטים של העץ למחרוזת טקסט
     * @return תיאור שכולל את סוג הישות המיקום והאנרגיה שלה
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
