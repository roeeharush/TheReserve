package ecosystem.entities;
import ecosystem.core.Position;

import java.util.logging.Logger;

/**
 * מחלקה אבסטרקטית שהיא הבסיס לכל דבר שקיים בעולם שלנו
 * היא מחזיקה את המידע הכי בסיסי כמו איפה הישות נמצאת איך היא נראית ואם היא חיה
 */
public abstract class  AbstractEntity {
    private volatile Position position;
    private char symbol;
    private volatile boolean alive = true;
    private static final String symbolsValid = "LRDTFXW";
    private static final Logger logger = Logger.getLogger(AbstractEntity.class.getName());

    /**
     * בונה ישות חדשה ובודק שהנתונים שהבאנו תקינים
     * אם המיקום לא טוב הוא שם אותה בנקודה אפס אפס ואם הסימול לא מוכר הוא נותן לה את האות אן
     * @param position המיקום ההתחלתי במפה
     * @param symbol התו שמייצג את הישות
     * @param alive מצב החיות ההתחלתי
     */
    public AbstractEntity(Position position, char symbol, boolean alive) {
        if (!setPosition(position)) {
            logger.warning("Received invalid position (null) - defaulting to (0,0)");
            this.position = new Position(0,0);
        }
        if (!setSymbol(symbol)) {
            logger.warning("Received invalid symbol: '" + symbol + "' - defaulting to 'N'");            this.symbol = 'N';
        }
        this.alive = alive;
    }

    /**
     * מחזיר את התו שמייצג את הישות בהדפסה
     * @return התו של הישות
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * מחזיר את המיקום הנוכחי של הישות על המפה
     * @return אובייקט מיקום עם שורה ועמודה
     */
      public Position getPosition() {
        return new Position(this.position.getRow(),this.position.getCol());
    }

    /**
     * בודק אם הישות עדיין חיה או שהיא כבר מחוץ למשחק
     * @return true אם הישות חיה
     */
    public boolean isAlive() {
        return alive;
    }


    /**
     * מעדכן את המיקום של הישות במיקום חדש
     * @param position המיקום החדש שרוצים לקבוע
     * @return true אם המיקום תקין וההשמה הצליחה
     */
    public boolean setPosition(Position position) {
        if (position == null)
            return false;
        this.position = new Position(position.getRow(),position.getCol());
        return true;
    }

    /**
     * בודק אם התו שהבאנו נמצא ברשימת התווים המותרים ומעדכן אותו
     * @param symbol התו החדש שרוצים לבדוק ולקבוע
     * @return true אם התו מופיע ברשימת המותרים
     */
    protected boolean setSymbol(char symbol) {
        int index = symbolsValid.indexOf(symbol);
        if (index != -1) {
            this.symbol = symbol;
            return true;
        }
        return false;
    }

    /**
     * מעדכן את מצב החיות של הישות
     * המתודה מונעת "החייאה" של ישות שכבר מתה ומאפשרת רק מעבר ממצב חי למצב מת או השארה באותו מצב
     * @param alive המצב החדש שרוצים לקבוע
     * @return true אם השינוי בוצע בהצלחה, false אם נעשה ניסיון להחיות מחדש ישות שכבר מתה
     */
    public boolean setAlive(boolean alive){
        if (!this.alive && alive)
            return false;
        this.alive = alive;
        return true;
    }

    /**
     * בונה מחרוזת שמציגה את כל הפרטים של הישות בצורה ברורה
     * @return טקסט שכולל סוג מיקום ומצב חיות
     */
    @Override
    public String toString() {
        return "Entity type: " + getSymbol() +" Position:" + getPosition().toString()  + " Alive state " + isAlive();
    }


    /**
     * בודק אם ישות אחרת היא בדיוק כמו הישות הזאת
     * ההשוואה בודקת אם יש להן אותו מיקום אותו סימול ואותו מצב חיות
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם כל הנתונים זהים לחלוטין
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof AbstractEntity other)
            return this.alive == other.alive && this.position.equals(other.position) && this.symbol == other.symbol;
        return false;
    }



    public abstract String getImageName();
}
