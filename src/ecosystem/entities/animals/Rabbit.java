package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.Reproducible;

import java.util.Random;

/**
 * מחלקה שמייצגת ארנב בעולם האקולוגי שלנו
 * הארנב הוא חיה צמחונית שזזה בצורה אקראית ויכולה להתרבות מהר
 */
public class Rabbit extends Animal implements Reproducible {
    private static final double MAX_ENERGY = 200;

    /**
     * בונה ארנב חדש במיקום שנבחר
     * הבנאי מגדיר לארנב את הסימן אר ואת כל הנתונים שלו כמו אנרגיה התחלתית של חמישים ואנרגיה מקסימלית של מאתיים
     * הוא גם קובע שהארנב אוכל עשב וזז בצורה רנדומלית במפה
     * @param position המיקום שבו הארנב מתחיל את המשחק במפה
     */
    public Rabbit( Position position ) {
        super(position, 'R', true, 50, MAX_ENERGY,new HerbivoreBehavior() ,new RandomMovement());

    }

    /**
     * הפעולה שהארנב עושה בכל תור של הסימולציה
     * הארנב מבצע את כל הפעולות הרגילות של חיה ואז מנסה להתרבות וליצור ארנב חדש
     * @param env הסביבה שבה הארנב פועל
     * @return true אם הארנב הצליח לבצע פעולה או להתרבות
     */
    @Override
    public boolean act(Environment env){
        if (!isAlive())
            return false;

        boolean animalAction = super.act(env);
        boolean reproduced = this.reproduce(env);
        return animalAction || reproduced;
    }

    /**
     * מנגנון הרבייה הייחודי של הארנב
     *  אם לארנב יש יותר משלושים אנרגיה יש סיכוי של שלושים אחוז שהוא ייצר ארנב חדש
     * הארנב מחפש מקום פנוי באחד מארבעת הכיוונים הצמודים אליו ויוצר שם צאצא חדש
     * @param env העולם שבו הארנב מנסה להתרבות
     * @return true אם נוצר ארנב חדש והתווסף למפה בהצלחה
     */
    @Override
    public boolean reproduce(Environment env) {
        Random rand = new Random();
        double chance = rand.nextDouble();

        Position position = this.getPosition();
        if(this.getEnergy()> 30 && chance <= 0.3){
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if (env.isPositionFree(op)){
                    Rabbit newRabbit = new Rabbit(op);
                    env.addEntity(newRabbit);
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * מחזיר מחרוזת טקסט עם כל הפרטים של הארנב להדפסה
     * @return תיאור שכולל סוג מיקום מצב חיות ואנרגיה
     */
    @Override
    public String toString(){
        return super.toString();
    }


    /**
     * בודק אם אובייקט אחר הוא ארנב שזהה בדיוק לארנב הזה
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר באותה ישות עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String getImageName() { return "Rabbit"; }

}


