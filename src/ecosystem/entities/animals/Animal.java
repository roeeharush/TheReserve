package ecosystem.entities.animals;

import ecosystem.behaviors.FeedingBehavior;
import ecosystem.behaviors.MovementStrategy;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * מחלקה אבסטרקטית שמייצגת את כל החיות במערכת שלנו
 * החיות האלה יודעות לזוז לאכול ולהרגיש את הסביבה שלהן בעזרת אסטרטגיות שונות שנקבעות לכל סוג חיה
 */
public abstract class Animal extends LivingEntity implements Movable, Eater, Sensory, EdibleByCarnivore , Consumable {
    private  FeedingBehavior feedingBehavior;
    private  MovementStrategy movementStrategy;
    private final int visionRange = 2;

    /**
     * בונה חיה חדשה עם כל הנתונים הבסיסיים וגם מגדיר לה איך היא זזה ומה היא אוכלת
     * @param position המיקום ההתחלתי של החיה במפה
     * @param symbol התו שמייצג את החיה כמו אל לאריה או אר לארנב
     * @param alive האם החיה מתחילה את המשחק כשהיא חיה
     * @param energy כמות האנרגיה שיש לחיה כשהיא נוצרת
     * @param maxEnergy הכי הרבה אנרגיה שהחיה יכולה לצבור
     * @param feedingBehavior ההתנהגות שקובעת איך ומה החיה אוכלת
     * @param movementStrategy האסטרטגיה שקובעת איך החיה זזה במרחב
     */
    public Animal(Position position, char symbol, boolean alive,
                  double energy, double maxEnergy, FeedingBehavior feedingBehavior , MovementStrategy movementStrategy){
        super(position ,symbol, alive,energy,maxEnergy );
        this.feedingBehavior = feedingBehavior;
        this.movementStrategy = movementStrategy;

    }

    /**
     * הפעולה המרכזית שהחיה עושה בכל תור של הסימולציה
     * החיה קודם כל מזדקנת ומאבדת אנרגיה ואז אם היא עדיין חיה היא בודקת מה קורה סביבה זזה ומנסה לאכול
     * @param env הסביבה שבה החיה נמצאת ופועלת
     * @return true אם החיה הצליחה לזוז או לאכול משהו באותו תור
     */
    public boolean act(Environment env){
        return super.act(env);
    }


    public List<WorldCommand> collectCommands(Environment env){
        List<WorldCommand> commands = new ArrayList<>();
        if (!isAlive())
            return commands;

        WorldCommand move = movementStrategy.buildMoveCommand(this, env);
        if (move != null)
            commands.add(move);

        WorldCommand eat = feedingBehavior.buildEatCommand(this, sense(env));
        if (eat != null)
            commands.add(eat);

        return commands;
    }




    /**
     * מחזיר כמה אנרגיה חיה אחרת תקבל אם היא תאכל את החיה הזאת
     * הערך הוא $0.8$ מהאנרגיה הנוכחית שיש לחיה באותו רגע
     * @return כמות האנרגיה הזמינה למי שיאכל את החיה
     */
    public double getNutritionValue(){
        return this.getEnergy()*0.8;
    }


    /**
     * מה קורה לחיה כשאוכלים אותה
     * הפעולה מעדכנת שהחיה מתה ומפסיקה להיות חלק פעיל במערכת
     * @return true אם העדכון של מצב החיות הצליח
     */
    public boolean onConsumed(){
         return this.setAlive(false);
    }


    /**
     * מאפשר לחיה להרגיש ולזהות את כל הישויות שנמצאות קרוב אליה במפה
     * @param env העולם שבו החיה מחפשת שכנים קרובים
     * @return רשימה של כל הישויות שנמצאות בטווח הראייה של החיה
     */
    public List<AbstractEntity> sense(Environment env) {
       return env.getNearbyEntities(this.getPosition());
    }


    /**
     * מבצע את התנועה של החיה לפי הדרך שנקבעה לה מראש
     * @param env העולם שבו החיה מנסה למצוא לאן לזוז
     * @return true אם החיה הצליחה לעבור למקום חדש במפה
     */


    /**
     * גורם לחיה לנסות לאכול מטרה מסוימת שהיא מצאה
     * החיה מעלה את האנרגיה שלה בהתאם למה שהיא אכלה וגורמת למטרה להיאכל
     * @param target הישות שהחיה מנסה לאכול עכשיו
     * @return true אם האכילה הצליחה והאנרגיה התעדכנה
     */
    public boolean eat(Consumable target){
        if (target != null) {
            double newEnergy = Math.min(getEnergy() + target.getNutritionValue(), getMaxEnergy());
            this.setEnergy( newEnergy);
            target.onConsumed();
            return true;
        }
        return false;
    }


    /**
     * הופך את כל המידע של החיה למחרוזת טקסט שאפשר להציג
     * @return תיאור שכולל סוג מיקום מצב חיות ואנרגיה
     */
    @Override
    public String toString(){
        return super.toString();
    }


    /**
     * בודק אם אובייקט אחר הוא חיה שזהה לחיה הזאת
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם מדובר באותה חיה עם אותם נתונים
     */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }



    }











