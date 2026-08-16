package ecosystem.entities.animals;
import ecosystem.behaviors.*;
import ecosystem.commands.ReproduceCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Reproducible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * מחלקה שמייצגת ארנב בעולם האקולוגי שלנו
 * הארנב הוא חיה צמחונית שזזה בצורה אקראית ויכולה להתרבות מהר
 */
public class Rabbit extends Animal implements Reproducible {
    private static final double MAX_ENERGY = 200;
    private static final double DEFAULT_ENERGY = 50;
    private static final double REPRODUCTION_ENERGY_THRESHOLD = 30;
    private static final double REPRODUCTION_CHANCE = 0.3;
    private static final Random RANDOM = new Random();

    /**
     * בונה ארנב חדש במיקום שנבחר
     * הבנאי מגדיר לארנב את הסימן אר ואת כל הנתונים שלו כמו אנרגיה התחלתית של חמישים ואנרגיה מקסימלית של מאתיים
     * הוא גם קובע שהארנב אוכל עשב וזז בצורה רנדומלית במפה
     * @param position המיקום שבו הארנב מתחיל את המשחק במפה
     */
    public Rabbit(Position position) {
        this(position, DEFAULT_ENERGY);
    }


    /**
     * בונה ארנב חדש במיקום מוגדר וקובע לו רמת אנרגיה התחלתית מותאמת אישית
     * בנאי זה משמש בעיקר בעת יצירת צאצאים חדשים במערכת ומגדיר את אסטרטגיות התנועה האקראית והתזונה הצמחונית של החיה
     * @param position המיקום שבו הארנב החדש ייוולד על גבי המפה
     * @param energy כמות האנרגיה ההתחלתית שאיתה הארנב מתחיל את חייו
     */
    public Rabbit(Position position, double energy) {
        super(position, 'R', true, energy, MAX_ENERGY, new HerbivoreBehavior(), new RandomMovement());
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
        return false;
    }


    /**
     * אוספת את כל פקודות הפעולה של הארנב כולל פקודות תנועה תזונה ובקשות רבייה ומיומנויות
     * המתודה קוראת לפעולת איסוף הפקודות הבסיסית של חיה ובנוסף בוחנת תנאי רבייה על בסיס רמת האנרגיה הנוכחית של הארנב וחישוב סיכויים אקראי ואם התנאים מתאימים היא מאתרת משבצת שכנה פנויה ומייצרת פקודת רבייה ייעודית המוזרקת לתור המשותף
     * @param env סביבת העולם המשמשת לקבלת החלטות ובדיקת זמינות משבצות שכנות עבור הצאצאים החדשים
     * @return רשימה המכילה את כל פקודות הפעולה והרבייה שהארנב מבקש לבצע בתור הנוכחי
     */
    @Override
    public List<WorldCommand> collectCommands(Environment env) {
        List<WorldCommand> commands = super.collectCommands(env);
        double chance = RANDOM.nextDouble();

        Position position = this.getPosition();
        if (this.getEnergy() > REPRODUCTION_ENERGY_THRESHOLD && chance <= REPRODUCTION_CHANCE) {
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);

            List<Position> options = new ArrayList<>(List.of(option1, option2, option3, option4));
            Collections.shuffle(options, RANDOM);

            for (Position op : options) {
                if (env.isPositionFree(op)) {
                    commands.add(new ReproduceCommand(new Rabbit(op)));
                    break;
                }
            }
        }
        return commands;
    }


    /**
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() { return "Rabbit"; }
}


