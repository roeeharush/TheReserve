package network;

/**
 * ממשק המייצג פקודת רשת הניתנת לביצוע לאחר קליטה מהשרת
 * ממשק זה מהווה חלק מתבנית העיצוב קומנד ומאפשר להוסיף סוגי פקודות רשת חדשים בעתיד בלי לשנות את קוד השרת עצמו
 * Pattern: Command
 */
public interface NetworkCommand {
        void execute();
}
