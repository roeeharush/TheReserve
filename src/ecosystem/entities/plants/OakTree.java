package ecosystem.entities.plants;

import ecosystem.commands.ReproduceCommand;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.core.Position;

import java.util.List;
import java.util.Random;

/**
 * מחלקה שמייצגת עץ אלון בעולם האקולוגי שלנו
 * עץ אלון הוא צמח עם הרבה אנרגיה שגדל לאט ומחזיק מעמד הרבה זמן
 */
public class OakTree extends Plant {
    private static final double INITIAL_ENERGY = 80.0;
    private static final double MAX_ENERGY = 120.0;
    private static final double GROW_RATE = 2.0;
    private static final double REPRODUCTION_CHANCE = 0.05;
    private static final Random rand = new Random();


    /**
     * יוצר עץ אלון חדש במיקום שנבחר
     * הבנאי מגדיר לעץ את הסימן T ואת כל ערכי האנרגיה והגדילה המיוחדים שלו
     * @param position המקום שבו העץ נשתל על המפה
     */
    public OakTree(Position position){
        super(position,'T',true,INITIAL_ENERGY,MAX_ENERGY,GROW_RATE,REPRODUCTION_CHANCE);
    }


    /**
     * בונה עץ אלון חדש במיקום מוגדר וקובע לו רמת אנרגיה התחלתית מותאמת אישית
     * בנאי זה משמש בעיקר בעת יצירת צאצאים חדשים במערכת ושומר על ערכי הגדילה וסיכויי הרבייה הקבועים של האילן
     * @param position המיקום שבו עץ האלון החדש ייוולד על גבי המפה
     * @param energy כמות האנרגיה ההתחלתית שאיתה העץ מתחיל את חייו
     */

    public OakTree(Position position , double energy ){
        super(position,'T',true ,energy ,MAX_ENERGY,GROW_RATE,REPRODUCTION_CHANCE);
    }

    /**ז
     * מנגנון הרבייה של עץ האלון
     * יש סיכוי של חמישה אחוז לייצר עץ חדש בכל תור
     * העץ מחפש מקום פנוי באחד מארבעת הכיוונים הצמודים אליו במרחק של צעד אחד ויוצר שם עץ חדש
     * @param env העולם שבו העץ מנסה להפיץ את עצמו
     * @return true אם נוצר עץ חדש באחד התאים השכנים
     */
    @Override
    public boolean reproduce(Environment env){
        return false;
    }


    /**
     * אוספת את כל פקודות הפעולה ובקשות הרבייה של עץ האלון עבור מנוע הסימולציה המקבילי
     * המתודה מפעילה את מנגנון איסוף הפקודות הבסיסי ובנוסף מחשבת את סיכויי הפצת הזרעים של העץ ואם התנאים מתאימים היא מאתרת משבצת שכנה פנויה ומייצרת פקודת רבייה ייעודית המוזרקת לתור המשותף
     * @param env סביבת העולם המשמשת לבדיקת זמינות משבצות שכנות עבור העצים החדשים
     * @return רשימה המכילה את פקודות הרבייה שהעץ מבקש לבצע בתור הנוכחי
     */

    @Override
    public List<WorldCommand> collectCommands(Environment env) {
        List<WorldCommand> commands = super.collectCommands(env);

        if(rand.nextDouble() <= REPRODUCTION_CHANCE){
            Position position = this.getPosition();
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if(env.isPositionFree(op)){
                    commands.add(new ReproduceCommand(new OakTree (op)));
                    return commands;
                }
            }
        }
        return commands;
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

    /**
     * מחזיר את שם הישות לצורך טעינת התמונה המתאימה בממשק הגרפי
     * @return מחרוזת הטקסט המייצגת את שם החיה
     */
    @Override
    public String getImageName() {
        return "OakTree";
    }


}
