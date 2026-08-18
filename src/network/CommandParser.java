package network;
import ecosystem.core.Environment;


/**
 * מחלקה האחראית על פענוח הודעות טקסטואליות שמתקבלות מהרשת והמרתן לאובייקטי פקודה מתאימים
 * המחלקה מיישמת את שלב הפענוח בתבנית העיצוב קומנד, ומפרידה בין קבלת הטקסט הגולמי לבין ביצוע הפעולה בפועל
 */
public class CommandParser {

    /**
     * מפענחת הודעת טקסט שהתקבלה מהרשת ובונה אובייקט פקודה מתאים לפי סוג הפעולה המבוקשת
     * @param message ההודעה הגולמית שהתקבלה מהרשת, מופרדת בפסיקים
     * @param env סביבת העולם שאליה הפקודה תתייחס בעת ביצועה
     * @return אובייקט פקודה מוכן לביצוע, המתאים לסוג הפעולה שזוהתה בהודעה
     * @throws IllegalArgumentException אם סוג הפעולה בהודעה אינו מוכר למערכת
     */
    public static NetworkCommand parse(String message, Environment env) {
        String[] parts = message.split(",");
        String action = parts[0];

        switch (action) {
            case "SPAWN": {
                String type = parts[1];
                double energy = Double.parseDouble(parts[2]);
                int row = Integer.parseInt(parts[3]);
                int col = Integer.parseInt(parts[4]);
                return new SpawnEntityCommand(type, row, col, energy ,env);
            }
            default:
                throw new IllegalArgumentException("Unrecognized command: " + action);        }
    }
}
