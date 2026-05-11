package ecosystem.interfaces;

/**
 * ממשק שמגדיר ישויות שאפשר לצרוך אותן בתוך המערכת שלנו
 * הממשק הזה מאפשר לחיות לקבל אנרגיה ממשאבים או מיצורים אחרים שהן אוכלות
 */
public interface Consumable {
    public double getNutritionValue();
    public boolean onConsumed();
}