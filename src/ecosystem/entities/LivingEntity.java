package ecosystem.entities;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Actable;


/**
 * מחלקה אבסטרקטית שמייצגת את כל הישויות החיות בעולם האקולוגי שלנו
 * הישות הזאת יודעת להזדקן ולאבד אנרגיה בכל תור של הסימולציה
 */
public abstract class LivingEntity extends AbstractEntity implements Actable {

    private double maxEnergy;
    private double energy;
    private int age = 0;
    private static final double DEFAULT_MAX_ENERGY =1000.0;


    /**
     * יוצר ישות חיה חדשה עם כל הנתונים של האנרגיה והגיל
     * @param position המיקום של הישות על המפה
     * @param symbol התו שמייצג את הישות
     * @param alive מצב החיות ההתחלתי
     * @param energy כמות האנרגיה ההתחלתית שיש לישות
     * @param maxEnergy האנרגיה המקסימלית שהישות יכולה להגיע אליה
     */
    public LivingEntity(Position position, char symbol, boolean alive,double energy, double maxEnergy) {
        super(position, symbol, alive);
        setMaxEnergy(maxEnergy);
        setEnergy(energy);
    }

    /**
     * מעדכן את האנרגיה המקסימלית שהישות יכולה לצבור
     * @param maxEnergy הערך החדש של האנרגיה המקסימלית
     * @return true אם הערך תקין וחיובי false אם הערך לא תקין והגדרנו ברירת מחדל
     */
    public boolean setMaxEnergy(double maxEnergy) {
        if (maxEnergy > 0) {
            this.maxEnergy = maxEnergy;
            if (this.energy >= this.maxEnergy)
                this.energy = maxEnergy;
            return true;
        }
        this.maxEnergy = DEFAULT_MAX_ENERGY;
        return false;

    }

    /**
     * מעדכן את האנרגיה הנוכחית של הישות
     * @param energy כמות האנרגיה החדשה
     * @return true אם האנרגיה בטווח התקין בין אפס למקסימום false אם חרגנו מהטווח
     */
    public boolean setEnergy(double energy){
        if(energy >= 0 && energy <= maxEnergy){
            this.energy = energy;
            return true;
        }
        this.energy = this.maxEnergy;
        return false;
    }

    /**
     * הפעולה שהישות עושה בכל תור של המערכת
     * הישות מזדקנת בשנה אחת ומאבדת שתי יחידות אנרגיה ואם נגמר הכוח היא מתה
     * @param env הסביבה שבה הישות פועלת
     * @return true אם הישות חיה והצליחה לבצע את הפעולות false אם היא כבר מתה
     */
    @Override
    public boolean act(Environment env){
        if(!this.isAlive())
            return false;
        this.age++;
        this.energy -= 2.0;
        if(this.getEnergy() <= 0.0)
            this.setAlive(false);
        return true;
    }

    /**
     * מחזיר את כמות האנרגיה הנוכחית של הישות
     * @return כמות האנרגיה שיש לישות עכשיו
     */
    public double getEnergy() {
        return this.energy;
    }


    /**
     * מחזיר את הגבול העליון של האנרגיה שהישות יכולה להחזיק
     * @return האנרגיה המקסימלית האפשרית
     */
    public double getMaxEnergy() {
        return this.maxEnergy;
    }


    /**
     * מחזיר את הגיל של הישות לפי מספר התורות שעברו
     * @return הגיל הנוכחי של הישות
     */
    public int getAge() {
        return this.age;
    }


    /**
     * הופך את כל המידע של הישות החיה למחרוזת טקסט כולל נתוני אנרגיה
     * @return תיאור מלא של הישות להדפסה
     */
    @Override
    public String toString(){
        return super.toString() + " Energy: " + this.energy;
    }

    /**
     * בודק אם ישות חיה אחרת זהה לישות הזאת
     * ההשוואה כוללת את כל הנתונים הבסיסיים וגם את הגיל והאנרגיה
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם כל הנתונים זהים לגמרי
     */
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!super.equals(o))
            return false;
        if(o instanceof LivingEntity other){
        return this.age == other.age
                && Double.compare(this.energy, other.energy)==0
                && Double.compare(this.maxEnergy,other.maxEnergy)==0;
        }
        return false;
    }
}

