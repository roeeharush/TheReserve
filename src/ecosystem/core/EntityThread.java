package ecosystem.core;
import java.util.List;
import ecosystem.commands.WorldCommand;
import ecosystem.interfaces.Actable;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

/**
 * מחלקה המייצגת תהליכון עצמאי עבור ישות פעילה בסימולציה
 * כל מחלקה כזו מריצה ישות בלולאה רציפה המבקשת פעולות מהעולם בכל פרק זמן מוגדר ומכניסה אותן לתור הפקודות המרכזי בצורה בטוחה ומקבילית
 */
public class EntityThread extends Thread{
    private final Actable entity;
    private final Environment environment;
    private final BlockingQueue<WorldCommand> commandQueue;
    private final Random random = new Random();
    private final AtomicBoolean running = new AtomicBoolean(true);
    private static final Logger logger = Logger.getLogger(EntityThread.class.getName());


    /**
     * בונה תהליכון חדש עבור ישות ומקשר אותו לסביבת העולם ולתור הפקודות המשותף
     * @param entity הישות הפעילה הנסמכת על תהליכון זה לצורך קבלת החלטות וביצוע פעולות
     * @param environment סביבת הסימולציה שממנה הישות שואבת את נתוני המפה והשכנים
     * @param commandQueue תור הפקודות החסום והמשותף שאליו מוגשות בקשות הפעולה של הישויות
     */
    public EntityThread(Actable entity, Environment environment,BlockingQueue<WorldCommand> commandQueue) {
        super(entity.getClass().getSimpleName() + "-Thread");
        this.entity = entity;
        this.environment = environment;
        this.commandQueue = commandQueue;
    }


    /**
     * לולאת הריצה הראשית של תהליכון הישות המבצעת פעולות במחזוריות קבועה
     * המתודה מנוחה את התהליכון למשך זמן אקראי שבין חצי שנייה לשנייה וחצי אוספת את כל פקודות הפעולה שהישות החליטה לבצע ומכניסה אותן בצורה חסומה ובטוחה לתוך תור הפקודות המרכזי
     */
    @Override
    public void run() {
        while (running.get()) {
            try {
                Thread.sleep(500 + random.nextInt(1000));
                List<WorldCommand> commands = entity.collectCommands(environment);
                for (WorldCommand cmd : commands)
                    commandQueue.put(cmd);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                logger.severe("Error in entity thread: " + e.getMessage());            }
        }
    }


    /**
     * עוצרת את פעולת התהליכון בצורה בטוחה ומסודרת ללא השארת תהליכונים תקועים בזיכרון
     * המתודה מעדכנת את דגל הבקרה האטומי ושולחת אות התראה כדי להעיר את התהליכון משנתו ולסיים את הלולאה מיד
     */
    public void stopThread() {
        running.set(false);
        interrupt();
    }


    /**
     * מחזירה את הישות הפעילה המשויכת ומנוהלת על ידי תהליכון זה
     * @return האובייקט של הישות המממשת את ממשק הפעולות
     */
    public Actable getEntity() {
        return entity;
    }
}
