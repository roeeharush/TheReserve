package ecosystem.interfaces;
import java.util.List;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

/**
 * ממשק שמגדיר את היכולת של ישות להרגיש את הסביבה שלה
 * היכולת הזו היא הבסיס לכל קבלת ההחלטות של החיות בעולם שלנו כי היא מאפשרת להן לראות מה קורה מסביב
 */
public interface Sensory {
    public List<AbstractEntity> sense(Environment env);
}
