package network;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.factory.EntityFactory;


/**
 * מחלקה המייצגת פקודת רשת ליצירת ישות חדשה בעולם הסימולציה
 * הפקודה הזו נוצרת על ידי CommandParser מתוך הודעת רשת, ומכילה את כל הנתונים הדרושים ליצירת הישות באמצעות EntityFactory
 * Pattern: Command
 */
public class SpawnEntityCommand implements NetworkCommand {
    private final Environment environment;
    private final String type;
    private final int row;
    private final int col;
    private final double energy;


    /**
     * בונה פקודת יצירת ישות חדשה עם כל הנתונים שחולצו מהודעת הרשת
     * @param type סוג הישות שיש ליצור, כמו "Lion" או "Rabbit"
     * @param row שורת המיקום שבו הישות תיוולד על המפה
     * @param col עמודת המיקום שבו הישות תיוולד על המפה
     * @param energy כמות האנרגיה ההתחלתית של הישות החדשה
     * @param env סביבת העולם שאליה יש להוסיף את הישות החדשה
     */
    public SpawnEntityCommand(String type, int row, int col, double energy ,Environment env) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.energy = energy;
        this.environment = env;
    }


    /**
     * מבצעת את פקודת היצירה בפועל, בונה את הישות באמצעות EntityFactory ומוסיפה אותה לעולם
     */

    @Override
    public void execute() {
        AbstractEntity entity = EntityFactory.createEntity(type, new Position(row, col), energy);
        environment.addEntity(entity);
    }
}
