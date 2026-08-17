package ecosystem.entities.plants;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;
import ecosystem.interfaces.Reproducible;
import ecosystem.states.PlantGrowthState;
import java.util.logging.Logger;


/**
 * מחלקה אבסטרקטית שמייצגת את כל הצמחים בעולם שלנו.
 * הצמחים הם יצורים חיים שגדלים (מעלים אנרגיה) ויכולים להתרבות.
 **/

public abstract class Plant extends LivingEntity implements Consumable, Reproducible, EdibleByHerbivore {
    private double growthRate;
    private double reproductionChance;
    private static final Logger logger = Logger.getLogger(Plant.class.getName());



    /**
     * בונה צמח חדש עם כל הנתונים הבסיסיים
     * מגדיר לצמח מצב התנהגות ייעודי המנתק אותו ממכונת המצבים של רעב מנוחה ושינה, המיועדת לחיות בלבד
     * @param position איפה הצמח יושב על המפה
     * @param symbol התו שמייצג אותו בהדפסה הגרפית
     * @param alive האם הוא חי כרגע בדרך כלל true
     * @param energy כמה כוח יש לו עכשיו
     * @param maxEnergy הכי הרבה כוח שהוא יכול לצבור
     * @param growthRate כמה אנרגיה הוא מקבל בכל פעימה
     * @param reproductionChance מה הסיכוי שלו להוציא צאצא בכל תור
     */
    public Plant(Position position, char symbol, boolean alive, double energy, double maxEnergy, double growthRate, double reproductionChance) {
        super(position, symbol, alive, energy, maxEnergy);
        setState(new PlantGrowthState());
        if (!setGrowthRate(growthRate))
            logger.warning("Invalid growth rate: " + growthRate + " - defaulting to 1.0");

        if (!setReproductionChance(reproductionChance))
            logger.warning("Invalid reproduction chance: " + reproductionChance + " - defaulting to 0.1");    }


    /**
     * מעדכן את קצב הגדילה של הצמח
     * @param growthRate קצב הגדילה החדש חייב להיות חיובי
     * @return true אם הנתון תקין false אם שמנו ערך דיפולטי בגלל קלט רע
     */
    public boolean setGrowthRate(double growthRate) {
        if (growthRate >= 0) {
            this.growthRate = growthRate;
            return true;
        }
        this.growthRate = 1.0;
        return false;
    }


    /**
     * מעדכן את סיכוי הרבייה של הצמח
     * @param reproductionChance סיכוי בין 0 ל 1
     * @return true אם הסיכוי בטווח התקין false אחרת
     */
    public boolean setReproductionChance(double reproductionChance) {
        if (reproductionChance >= 0 && reproductionChance <= 1.0) {
            this.reproductionChance = reproductionChance;
            return true;
        }
        this.reproductionChance = 0.1;
        return false;
    }


    /**
     * הפעולה שהצמח עושה בכל תור של הסימולציה
     * הצמח גדל בהתאם לקצב הגדילה שלו ומנסה להתרבות, ואינו כפוף למכונת המצבים של רעב מנוחה ושינה בניגוד לחיות
     * @param env העולם שבו הצמח חי
     * @return true אם הצמח הצליח לבצע פעולה גדילה או רבייה
     */
    @Override
    public boolean act(Environment env) {
        boolean action = super.act(env);
        if (!isAlive())
            return false;
        double updatedEnergy = this.getEnergy() + this.growthRate;
        if (updatedEnergy > this.getMaxEnergy())
            updatedEnergy = this.getMaxEnergy();
        this.setEnergy(updatedEnergy);

        boolean repr = false;
        if (Math.random() <= this.reproductionChance)
            repr = reproduce(env);
        return action || repr;
    }


    /**
     * מנסה לייצר צמח חדש בסביבה
     * @param env העולם שבו מנסים להוסיף את הישות החדשה
     * @return true אם הצלחנו להוסיף צאצא למפה false אחרת
     */
    @Override
    public abstract boolean reproduce (Environment env);



    /**
     * מחזיר את הערך התזונתי של הצמח כמה אנרגיה יקבל מי שיאכל אותו
     * @return כמות האנרגיה הנוכחית של הצמח
     */
    @Override
    public double getNutritionValue () {
        return this.getEnergy();
        }


    /**
     * מה קורה לצמח כשאוכלים אותו הוא פשוט מפסיק לחיות
     * @return true אם העדכון של ה alive הצליח
     */
        @Override
        public boolean onConsumed(){
            return this.setAlive(false);
        }


    /**
     * בודקת אם אובייקט אחר הוא צמח הזהה לחלוטין לצמח הנוכחי
     * המתודה משווה את נתוני האם הבסיסיים של הישות החיה ולאחר מכן מוודאת זהות מלאה גם בערכי קצב הגדילה וסיכויי הרבייה הספציפיים של הצמח
     * @param o האובייקט המיועד להשוואה מול הצמח הנוכחי
     * @return true אם האובייקטים זהים לחלוטין בכל הפרמטרים או false בכל מקרה אחר
     */
    @Override
    public boolean equals (Object o){
        if (this == o)
            return true;
        if (o instanceof Plant other){
            if (!super.equals(o))
                return false;return Double.compare(this.growthRate, other.growthRate) == 0
                        && Double.compare(this.reproductionChance, other.reproductionChance) == 0;
            }
        return false;
    }
}

