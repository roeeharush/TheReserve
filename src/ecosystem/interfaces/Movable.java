package ecosystem.interfaces;
import ecosystem.core.Environment;

/**
 * ממשק שמגדיר את היכולת של ישות לזוז ממקום למקום בתוך העולם שלנו
 * כל מי שמממש את הממשק הזה חייב לדעת איך לשנות את המיקום שלו על המפה
 */
public interface Movable {
    public boolean move(Environment env);
}
