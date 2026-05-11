package ecosystem.entities.plants;
import ecosystem.core.Environment;
import ecosystem.core.Position;

import java.util.Random;

/**
 * מחלקה שמייצגת פרח במערכת האקולוגית
 * הפרח הוא סוג של צמח שמתפתח מהר ויכול להפיץ את עצמו במפה
 */
public class Flower extends Plant {
    private static final double INITIAL_ENERGY = 10.0;
    private static final double MAX_ENERGY = 70.0;
    private static final double GROW_RATE = 5.0;
    private static final double REPRODUCTION_CHANCE = 0.2;
    private static final Random rand = new Random();


    /**
     * יוצר פרח חדש במיקום שנבחר
     * הבנאי מגדיר לפרח את הסימן F ואת כל ערכי האנרגיה והגדילה שנקבעו מראש
     * @param position המקום שבו הפרח יתחיל את החיים שלו במפה
     */
    public Flower(Position position) {
        super(position, 'F', true, INITIAL_ENERGY, MAX_ENERGY, GROW_RATE, REPRODUCTION_CHANCE);
    }

    /**
     * מה קורה כשיצור אחר אוכל את הפרח
     * המתודה מעדכנת שהפרח מת על ידי קריאה לפעולה של מחלקת האם
     * @return true אם העדכון של מצב החיות עבד כמו שצריך
     */
    @Override
    public boolean onConsumed() {
        return super.onConsumed();
    }

    /**
     * המנגנון שגורם לפרח להתרבות וליצור פרחים חדשים
     * לפי הקוד יש סיכוי של עשרים אחוז שבכל תור הפרח ינסה לייצר בין אחד לשלושה צאצאים
     * הפרח מחפש מקומות פנויים מסביבו במרחק של עד שני צעדים ויוצר שם פרחים חדשים
     * @param env הסביבה שבה הפרח נמצא ובודק מקומות פנויים
     * @return true אם הפרח הצליח לייצר לפחות צאצא אחד חדש במפה
     */
    @Override
    public boolean reproduce(Environment env) {
        if (rand.nextDouble() <= 0.20) {
            int childrenToCreate = rand.nextInt(3) + 1;
            int createdCount = 0;
            Position myPos = this.getPosition();

            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    Position p = new Position(myPos.getRow() + i, myPos.getCol() + j);

                    int dist = myPos.distanceTo(p);
                    if (dist > 0 && dist <= 2 && env.isPositionFree(p)) {
                        Flower flower = new Flower(p);
                        env.addEntity(flower);
                        createdCount++;

                        if (createdCount == childrenToCreate)
                            return true;
                    }
                }
            }
            return createdCount > 0;
        }
        return false;
    }

    /**
     * בודק אם אובייקט אחר הוא פרח שזהה לפרח הזה
     * הבדיקה מוודאת שמדובר באותו סוג של יצור עם אותם נתונים
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם הפרחים זהים לחלוטין false אחרת
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Flower))
            return false;
        return super.equals(o);
    }

    /**
     * הופך את כל הנתונים של הפרח לטקסט שאפשר להציג
     * @return מחרוזת שמכילה את הסוג המיקום והאנרגיה של הפרח
     */
    @Override
    public String toString() {
        return super.toString();
    }
}

