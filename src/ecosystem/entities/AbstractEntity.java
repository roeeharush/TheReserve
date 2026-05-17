package ecosystem.entities;
import ecosystem.core.Position;

/**
 * מחלקה אבסטרקטית שהיא הבסיס לכל דבר שקיים בעולם שלנו
 * היא מחזיקה את המידע הכי בסיסי כמו איפה הישות נמצאת איך היא נראית ואם היא חיה
 */
public abstract class  AbstractEntity {
    private Position position;
    private char symbol;
    private boolean alive = true;
    private static final String symbolsValid = "LRDTFXW";

    /**
     * בונה ישות חדשה ובודק שהנתונים שהבאנו תקינים
     * אם המיקום לא טוב הוא שם אותה בנקודה אפס אפס ואם הסימול לא מוכר הוא נותן לה את האות אן
     * @param position המיקום ההתחלתי במפה
     * @param symbol התו שמייצג את הישות
     * @param alive מצב החיות ההתחלתי
     */
    public AbstractEntity(Position position, char symbol, boolean alive) {
        if (!setPosition(position))
            this.position = new Position(0,0);

         if(!setSymbol(symbol))
             this.symbol = 'N'; // none

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
        return position;
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
        this.position =   new Position(position.getRow(),position.getCol());
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
     * @param alive המצב החדש שרוצים לקבוע
     * @return false
     */
    public boolean setAlive(boolean alive){
        if (!alive)
            this.alive= false;
        return false;
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
