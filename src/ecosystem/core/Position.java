package ecosystem.core;

/**
 * מחלקה שמייצגת מיקום של ישות על המפה בעזרת שורה ועמודה
 * המיקום הזה עוזר לנו לדעת איפה כל דבר נמצא ואיך לחשב מרחקים
 */
public class Position {
    private int row;
    private int col;


    /**
     * בונה מיקום חדש לפי השורה והעמודה שקיבלנו
     * @param row מספר השורה במפה
     * @param col מספר העמודה במפה
     */
    public Position(int row, int col) {
        if (!setRow(row))
            this.row = 0;
        if (!setCol(col))
            this.col = 0;
    }


    /**
     * מעדכן את מספר השורה ובודק שהערך לא שלילי
     * @param row השורה החדשה שרוצים לקבוע
     * @return true אם המספר תקין false אם הוא שלילי
     */
    public boolean setRow(int row) {
        if ((row >= 0)) {
            this.row = row;
            return true;
        }
        return false;
    }


    /**
     * מעדכן את מספר העמודה ובודק שהערך לא שלילי
     * @param col העמודה החדשה שרוצים לקבוע
     * @return true אם המספר תקין false אם הוא שלילי
     */
    public boolean setCol(int col) {
        if ((col >= 0)) {
            this.col = col;
            return true;
        }
        return false;
    }


    /**
     * מעדכן גם את השורה וגם את העמודה בבת אחת ובודק תקינות
     * @param row השורה החדשה
     * @param col העמודה החדשה
     * @return true אם שני המספרים תקינים
     */
    public boolean setCoordinates(int row, int col) {
        if ((col >= 0 && row >= 0)) {
            this.row = row;
            this.col = col;
            return true;
        }
        return false;
    }


    /**
     * מחזיר את מספר השורה הנוכחי
     * @return מספר השורה
     */
    public int getRow(){return this.row;}


    /**
     * מחזיר את מספר העמודה הנוכחי
     * @return מספר העמודה
     */
    public int getCol(){return this.col;}


    /**
     * מחשב את מרחק מנהטן בין המיקום הזה למיקום אחר
     * החישוב מתבצע על ידי חיבור ההפרשים בין השורות והעמודות
     * @param other המיקום השני שרוצים למדוד אליו מרחק
     * @return המרחק במספר שלם
     * @throws IllegalArgumentException אם המיקום השני הוא null
     */
    public int distanceTo(Position other) {
        if (other == null)
            throw new IllegalArgumentException("Cannot compute distance to a null position");
        return Math.abs((other.col - this.col)) + Math.abs((other.row - this.row));
    }


    /**
     * בודק אם מיקום אחר הוא בדיוק אותו דבר כמו המיקום הזה
     * הבדיקה מוודאת שגם השורה וגם העמודה זהות לחלוטין
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם המיקומים זהים לגמרי
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Position other) {
            return (other.col == this.col) && (other.row == this.row);
        }
        return false;
    }



    /**
     * הופך את המיקום למחרוזת טקסט כדי שאפשר יהיה להדפיס אותו בנוחות
     * @return המיקום בפורמט של סוגריים עם שורה ועמודה
     */
    @Override
    public String toString(){
        return "(" + this.row + "," + this.col + ")";
    }
}


