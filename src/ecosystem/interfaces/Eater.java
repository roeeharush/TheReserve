package ecosystem.interfaces;

/**
 * ממשק שמגדיר את היכולת של ישות לאכול משהו בעולם שלנו
 * כל מי שמממש את הממשק הזה חייב לדעת איך לקבל מטרה ולהוציא ממנה אנרגיה
 */
public interface Eater {
    public boolean eat(Consumable target);
}
