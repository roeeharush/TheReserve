package ecosystem.entities.plants;
import ecosystem.commands.ReproduceCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        this(position, INITIAL_ENERGY);
    }


    /**
     * בונה פרח חדש במיקום מוגדר וקובע לו רמת אנרגיה התחלתית מותאמת אישית
     * בנאי זה משמש בעיקר בעת יצירת צאצאים חדשים במערכת ושומר על ערכי קצב הגדילה הגבוהים וסיכויי הפריצה הקבועים של הפרח
     * @param position המיקום שבו הפרח החדש ייוולד על גבי המפה
     * @param energy כמות האנרגיה ההתחלתית שאיתה הפרח מתחיל את חייו
     */

    public Flower(Position position, double energy) {
        super(position, 'F', true, energy, MAX_ENERGY, GROW_RATE, REPRODUCTION_CHANCE);
    }


    /**
     * המנגנון שגורם לפרח להתרבות וליצור פרחים חדשים
     * יש סיכוי של עשרים אחוז שבכל תור הפרח ינסה לייצר בין אחד לשלושה צאצאים
     * הפרח מחפש מקומות פנויים מסביבו במרחק של עד שני צעדים ויוצר שם פרחים חדשים
     * @param env הסביבה שבה הפרח נמצא ובודק מקומות פנויים
     * @return true אם הפרח הצליח לייצר לפחות צאצא אחד חדש במפה
     */
    @Override
    public boolean reproduce(Environment env) {
      return false;
    }


    /**
     * אוספת את כל פקודות הפעולה ובקשות הרבייה המרובות של הפרח עבור מנוע הסימולציה המקבילי
     * המתודה מפעילה את מנגנון איסוף הפקודות הבסיסי ובנוסף מחשבת סיכויי התפשטות של עשרים אחוזים ואם התנאים מתאימים היא מגרילה כמות צאצאים מבוקשת סורקת משבצות פנויות ברדיוס מנהטן של עד שני צעדים ומייצרת פקודות רבייה ייעודיות המוזרקות לתור המשותף
     * @param env סביבת העולם המשמשת לבדיקת זמינות משבצות פנויות עבור הפרחים החדשים
     * @return רשימה המכילה את כל פקודות הרבייה שהפרח מבקש לבצע בתור הנוכחי
     */
    @Override
    public List<WorldCommand> collectCommands(Environment env) {
        List<WorldCommand> commands = super.collectCommands(env);
        if (rand.nextDouble() <= REPRODUCTION_CHANCE) {
            int childrenToCreate = rand.nextInt(3) + 1;
            Position myPos = this.getPosition();
            List<Position> candidates = new ArrayList<>();

            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    Position p = new Position(myPos.getRow() + i, myPos.getCol() + j);
                    int dist = myPos.distanceTo(p);
                    if (dist > 0 && dist <= 2 && env.isPositionFree(p))
                        candidates.add(p);
                }
            }

            Collections.shuffle(candidates, rand);
            int createdCount = 0;
            for (Position p : candidates) {
                if (createdCount == childrenToCreate)
                    break;
                commands.add(new ReproduceCommand(new Flower(p)));
                createdCount++;
            }
        }
        return commands;
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
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() {
        return "Flower";
    }
}

