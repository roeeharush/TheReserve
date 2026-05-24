package ecosystem.entities.plants;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;
import ecosystem.interfaces.Reproducible;


/**
 * מחלקה אבסטרקטית שמייצגת את כל הצמחים בעולם שלנו.
 * הצמחים הם יצורים חיים שגדלים (מעלים אנרגיה) ויכולים להתרבות.
 **/

public abstract class Plant extends LivingEntity implements Consumable, Reproducible, EdibleByHerbivore {
    private double growthRate;
    private double reproductionChance;

    /**
     * בונה צמח חדש עם כל הנתונים הבסיסיים
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
        setGrowRate(growthRate);
        setReproductionChance(reproductionChance);
    }

    /**
     * מעדכן את קצב הגדילה של הצמח
     * @param growthRate קצב הגדילה החדש חייב להיות חיובי
     * @return true אם הנתון תקין false אם שמנו ערך דיפולטי בגלל קלט רע
     */
    public boolean setGrowRate(double growthRate) {
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
     * הפעולה שהצמח עושה בכל תור של הסימולציה.
     * הצמח מפצה על איבוד האנרגיה הבסיסי גדל לפי הקצב שלו ומנסה להתרבות.
     * * @param env העולם שבו הצמח חי.
     * @return true אם הצמח הצליח לבצע פעולה גדילה או רבייה.
     */
    @Override
    public boolean act(Environment env) {
        boolean action = super.act(env);
        if (!isAlive())
            return false;
        double updatedEnergy = this.getEnergy() + 2 + this.growthRate;
        if (updatedEnergy > this.getMaxEnergy())
            updatedEnergy = this.getMaxEnergy();
        this.setEnergy(updatedEnergy);

        boolean repr = reproduce(env);
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
     * הופך את הצמח למחרוזת שאפשר להדפיס
     * @return תיאור של הצמח המיקום והאנרגיה שלו
     */
    @Override
    public String toString() {
       return super.toString();
    }


    /**
     * בודק אם שני צמחים הם בעצם אותו דבר
     * @param o האובייקט שמשווים אליו
     * @return true אם הם זהים בכל הפרמטרים מיקום אנרגיה קצב גדילה וכו
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

