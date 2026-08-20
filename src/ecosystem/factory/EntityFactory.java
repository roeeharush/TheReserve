package ecosystem.factory;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Deer;
import ecosystem.entities.animals.Lion;
import ecosystem.entities.animals.Rabbit;
import ecosystem.entities.plants.Flower;
import ecosystem.entities.plants.OakTree;
import ecosystem.entities.resources.Rock;
import ecosystem.entities.resources.Water;

/**
 * מחלקה ייעודית האחראית על יצירת מופעים של כל סוגי הישויות והמשאבים במערכת הסימולציה
 * המחלקה מיישמת את תבנית העיצוב פקטורי ומנתקת את התלות של רכיבי הממשק הגרפי או הבקרים ביצירה ישירה של אובייקטים קונקרטיים
 * Pattern: Factory Method
 */

public class EntityFactory {


    /**
     * מייצרת ומחזירה מופע קונקרטי חדש של ישות או משאב בהתאם לסוג המבוקש
     * המתודה משתמשת במנגנון בחירה פנימי כדי למנוע את פיזור הפקודה ניו ברחבי הממשק הגרפי ומאפשרת להרחיב את סוגי היצורים בעתיד מבלי לשנות את קוד חלונות הדיאלוג
     * @param type מחרוזת טקסט המייצגת את שם סוג הישות המבוקשת לייצור
     * @param pos המיקום הגאוגרפי ההתחלתי המיועד עבור הישות על גבי המפה
     * @param initialEnergy כמות האנרגיה ההתחלתית שתוקצה לישות במידה ומדובר ביצור חי
     * @return מופע חדש של הישות שנוצרה המורחב ממחלקת הבסיס האבסטרקטית
     */

    public static AbstractEntity createEntity(String type, Position pos, double initialEnergy) {
        switch (type) {
            case "Lion" -> { return new Lion(pos, initialEnergy); }
            case "Deer" -> { return new Deer(pos, initialEnergy); }
            case "Rabbit" -> { return new Rabbit(pos, initialEnergy); }
            case "Flower" -> { return new Flower(pos, initialEnergy); }
            case "OakTree" -> { return new OakTree(pos, initialEnergy); }
            case "Water" -> { return new Water(pos); }
            case "Rock" -> { return new Rock(pos); }
            default -> throw new IllegalArgumentException("Unknown entity type: " + type);
        }
    }
}

