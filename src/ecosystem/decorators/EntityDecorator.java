package ecosystem.decorators;
import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.commands.MoveCommand;
import ecosystem.interfaces.Actable;
import java.util.ArrayList;
import java.util.List;

/**
 * מחלקה מופשטת המהווה את הבסיס עבור עיטוף ישויות והחלת אפקטים דינמיים עליהן בזמן ריצה
 * המחלקה מיישמת את תבנית העיצוב דקורטור ומאפשרת להרחיב את התנהגות הישויות מבלי לשנות את קוד המקור שלהן
 * Pattern: Decorator
 */

public abstract class EntityDecorator extends AbstractEntity implements Actable {
    protected final Actable decoratedEntity;
    private int duration = 10;

    /**
     * בונה מעטפת אפקט חדשה סביב ישות קיימת ומעתיקה את כל נתוניה הבסיסיים כגון מיקום תו ייצוג ומצב חיות
     * @param decoratedEntity הישות המקורית שאותה עוטפים וממנה גוזרים את הנתונים ההתחלתיים
     */

    public EntityDecorator(Actable decoratedEntity) {
        super(((AbstractEntity) decoratedEntity).getPosition(),
                ((AbstractEntity) decoratedEntity).getSymbol(),
                ((AbstractEntity) decoratedEntity).isAlive()
        );
        this.decoratedEntity = decoratedEntity;
    }

    /**
     * מפעילה את לוגיקת האפקט בכל פעימת זמן ומנהלת את משך החיים הנותר של הדקורטור הנוכחי
     * המתודה מוודאת שהישות העטופה עדיין בחיים ומורידה יחידת זמן אחת ממדד משך האפקט ואם הזמן הגיע לאפס היא מתחילה את תהליך הסרת האפקט והחזרת הישות המקורית למפה
     * @param env סביבת העולם שבה האפקט מתעדכן ופועל
     * @return true אם הפעולה התבצעה בהצלחה או false אם הישות או האפקט אינם פעילים יותר
     */

    @Override
    public boolean act(Environment env){
        if (!((AbstractEntity) decoratedEntity).isAlive()) {
            this.setAlive(false);
            return false;
        }
        if (!isAlive())
            return false;

        duration--;
        if (duration <= 0) {
            removeDecoratorAndRestoreOriginal(env);
            return true;
        }
        return false;
    }

    /**
     * מסירה את האפקט הנוכחי מהמפה ומחזירה את האובייקט המקורי שנשמר בהכלה אל מיקומו הנוכחי בעולם
     * המתודה מבצעת סנכרון מלא של המיקום הנוכחי של הדקורטור אל האובייקט המקורי שולפת את הדקורטור מהמודל ומחזירה את הישות המקורית למשבצת שלה
     * @param env סביבת העולם שבה מתבצעת החלפת האובייקטים והסנכרון מחדש
     */

    protected void removeDecoratorAndRestoreOriginal(Environment env){
        AbstractEntity original = (AbstractEntity) this.decoratedEntity;
        original.setPosition(this.getPosition());
        env.removeEntity(this);
        env.addEntity(original);
    }

    /**
     * אוספת ומחזירה את רשימת פקודות העולם של הישות העטופה על ידי הפניית הקריאה אליה בצורה ישירה
     * מתודה זו שומרת על עקרון הפולימורפיזם ומבטיחה שכל פעולות הסימולציה המתוכננות של הישות המקורית ימשיכו לתפקד כרגיל גם תחת השפעת האפקט
     * @param env סביבת העולם המשמשת לבדיקת תנאי המשבצות והסביבה של הישות
     * @return רשימה המכילה את פקודות העולם שהישות העטופה מבקשת לבצע
     */

    @Override
    public List<WorldCommand> collectCommands(Environment env) {
        ((AbstractEntity) decoratedEntity).setPosition(this.getPosition());
        List<WorldCommand> rawCommands = decoratedEntity.collectCommands(env);
        List<WorldCommand> fixedCommands = new ArrayList<>();
        for (WorldCommand cmd : rawCommands) {
            if (cmd instanceof MoveCommand move)
                fixedCommands.add(new MoveCommand(this, move.getNewPosition()));
            else
                fixedCommands.add(cmd);
        }
        return fixedCommands;
    }


    /**
     * מחזירה את שם קובץ התמונה של הישות הפנימית העטופה לצורך טעינה נכונה בממשק הגרפי
     * המתודה מבצעת המרה דינמית של הישות המוכלת למחלקת הבסיס ושולפת את שם התמונה המקורי שלה כדי שהגרפיקה של האפקט על המסך תישאר זהה למראה הישות המקורית
     * @return מחרוזת טקסט המייצגת את שם התמונה של הישות המקורית
     */

    @Override
    public String getImageName() {
        return ((AbstractEntity) decoratedEntity).getImageName();
    }


    /**
     * שולפת ומחזירה את הישות המקורית העטופה והמוכלת בתוך הדקורטור הנוכחי
     * מתודה זו נדרשת עבור רכיבי הממשק הגרפי או לצורך פירוק שרשראות אפקטים מרובים במערכת הסימולציה [cite: 33, 34]
     * @return הישות הפנימית העטופה על ידי האפקט [cite: 34, 35]
     */

    public Actable getDecoratedEntity() {
        return decoratedEntity;
    }

    }




