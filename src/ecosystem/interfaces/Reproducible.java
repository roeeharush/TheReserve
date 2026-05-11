package ecosystem.interfaces;

import ecosystem.core.Environment;

/**
 * ממשק שמגדיר את היכולת של ישות להתרבות וליצור צאצאים חדשים בעולם שלנו
 * כל מי שמממש את הממשק הזה חייב להגדיר את התנאים שבהם הוא יוצר יצור חדש במפה
 */
public interface Reproducible {
    public boolean reproduce(Environment env);

}
