package ecosystem.entities;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Actable;
import ecosystem.states.EntityState;
import ecosystem.states.HungryState;

import java.util.logging.Logger;


/**
 * מחלקה אבסטרקטית שמייצגת את כל הישויות החיות בעולם האקולוגי שלנו
 * הישות הזאת יודעת להזדקן ולאבד אנרגיה בכל תור של הסימולציה
 */
public abstract class LivingEntity extends AbstractEntity implements Actable {

    private volatile double maxEnergy;
    private volatile double energy;
    private volatile int age = 0;
    private static final double DEFAULT_MAX_ENERGY = 1000.0;
    private volatile EntityState currentState = new HungryState();
    private static final Logger logger = Logger.getLogger(LivingEntity.class.getName());


    /**
     * יוצר ישות חיה חדשה עם כל הנתונים של האנרגיה והגיל
     * @param position המיקום של הישות על המפה
     * @param symbol התו שמייצג את הישות
     * @param alive מצב החיות ההתחלתי
     * @param energy כמות האנרגיה ההתחלתית שיש לישות
     * @param maxEnergy האנרגיה המקסימלית שהישות יכולה להגיע אליה
     */
    public LivingEntity(Position position, char symbol, boolean alive, double energy, double maxEnergy) {
        super(position, symbol, alive);

        if (!setMaxEnergy(maxEnergy))
            logger.warning("Received invalid max energy: " + maxEnergy + " - defaulting to " + DEFAULT_MAX_ENERGY);

        if (!setEnergy(energy))
            logger.warning("Received invalid initial energy: " + energy);    }

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
        if (energy < 0) {
            this.energy = 0;
            this.setAlive(false);
            return false;
        }
        if (energy > maxEnergy) {
            this.energy = maxEnergy;
            return false;
        }
        this.energy = energy;
        if (this.energy <= 0)
            this.setAlive(false);
        return true;
    }


    /**
     * מעדכנת ומחליפה את מצב ההתנהגות הנוכחי של הישות החיה במכונת המצבים
     * מתודה זו מאפשרת למצבים השונים להעביר את הישות מצב בצורה דינמית ועיוורת במהלך פעימות הסימולציה
     * @param state אובייקט המצב החדש שאליו הישות החיה עוברת כעת
     */
    public void setState(EntityState state) {
        if (state != null)
            this.currentState = state;
    }


    /**
     * הפעולה שהישות עושה בכל תור של המערכת
     * הישות מזדקנת בשנה אחת ומאצילה את ביצוע התנהגותה האקולוגית למצב הפנימי הנוכחי שלה מבלי לדעת מהו המצב הספציפי או הלוגיקה הפנימית המופעלת בו
     * @param env הסביבה שבה הישות פועלת ומבצעת את החלטותיה
     * @return true אם הישות חיה והצליחה להפעיל את מצב ההתנהגות שלה או false אם היא כבר מתה
     */
    @Override
    public boolean act(Environment env){
        if(!this.isAlive())
            return false;
        this.age++;
        if (currentState != null)
            currentState.doAction(this, env);
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


    /**
     * בודקת האם הישות החיה רשאית לבצע תנועה על גבי המפה בתור הנוכחי בהתאם למצבה הפנימי
     * המתודה פונה למצב הנוכחי ומאפשרת למנוע הסימולציה או לישות עצמה לקבל החלטה פולימורפית מבלי לבצע בדיקות סוג קשיחות
     * @return true אם המצב הנוכחי מאפשר לישות לנוע או false אם המצב מונע תנועה כגון במצב שינה
     */
    public boolean canMove() {
        return currentState != null && currentState.canMove();
    }
}

