package ecosystem.core;
import ecosystem.gui.WorldObserver;
import ecosystem.entities.AbstractEntity;
import java.util.ArrayList;
import java.util.List;


/**
 * מחלקה שמנהלת את כל העולם והמפה שלנו
 * היא האחראית לדעת איפה כל יצור נמצא ולדאוג שהכל יעבוד לפי הכללים של המערכת
 */
public class Environment  {
    private final List<AbstractEntity> entities;
    private final AbstractEntity[][] map;
    private final int rows;
    private final int cols;
    private final List<WorldObserver> observers = new ArrayList<>();
    private int ticks = 0;


    /**
     * בונה עולם חדש עם מספר שורות ועמודות שביקשנו
     * אם הגודל שנתנו קטן מדי המערכת קובעת גודל מינימלי של עשר על עשר באופן אוטומטי
     * @param rows מספר השורות בעולם
     * @param cols מספר העמודות בעולם
     */
    public Environment(int rows, int cols) {
        if (rows < 10) rows = 10;
        if (cols < 10) cols = 10;
        this.rows = rows;
        this.cols = cols;
        this.map = new AbstractEntity[rows][cols];
        this.entities = new ArrayList<>();
    }

    /**
     * מחזירה את מספר השורות במפת העולם
     * @return מספר השורות הקיים במפה
     */
    public int getRows() { return rows; }


    /**
     * מחזירה את מספר העמודות במפת העולם
     * @return מספר העמודות הקיים במפה
     */
    public int getCols() { return cols; }


    /**
     * מחזירה את הישות הנמצאת במיקום הספציפי במפה לפי שורה ועמודה
     * המתודה בודקת שהאינדקסים בתוך גבולות הלוח ומחזירה null אם המיקום חורג או ריק
     * @param row אינדקס השורה במפה
     * @param col אינדקס העמודה במפה
     * @return הישות הקיימת במיקום, או null אם התא ריק או חורג מגבולות המפה
     */
    public AbstractEntity getEntityAt(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols)
            return null;
        return map[row][col];
    }


    /**
     * מחזירה העתק של רשימת כל היצורים והחפצים שנמצאים כרגע בתוך העולם
     * @return רשימה של כל הישויות הקיימות
     */
    public synchronized List<AbstractEntity> getEntities() {
        return new ArrayList<>(entities);
    }


    /**
     * בודק אם משבצת מסוימת במפה פנויה
     * המתודה מוודא שהמיקום נמצא בתוך גבולות המפה ושאין שם כבר מישהו אחר
     * @param pos המיקום שרוצים לבדוק
     * @return true אם המקום פנוי ואפשר להיכנס אליו
     */
    public boolean isPositionFree(Position pos) {
        if(pos == null) return false;
        if (pos.getCol() < 0 || pos.getCol() >= this.cols || pos.getRow() < 0 || pos.getRow() >= this.rows)
            return false;
        return map[pos.getRow()][pos.getCol()] == null;
    }

    /**
     * מנסה להכניס יצור חדש לתוך העולם
     * הפעולה בודקת שהמקום פנוי ומעדכנת גם את הרשימה וגם את המפה הגרפית
     * @param entity היצור שרוצים להוסיף
     * @return true אם ההוספה הצליחה והמקום היה פנוי
     */
    public synchronized boolean addEntity(AbstractEntity entity) {
        if (entity == null || !isPositionFree(entity.getPosition()))
            return false;

        entities.add(entity);
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = entity;
        notifyObservers();
        return true;
    }

    /**
     * מוציא יצור מהעולם כשהוא מת או נאכל
     * הפעולה מנקה אותו מהרשימה ומוחקת אותו מהמפה
     * @param entity היצור שצריך למחוק
     * @return true אם היצור נמצא ונמחק בהצלחה
     */
    public synchronized boolean removeEntity(AbstractEntity entity) {
        if (entity == null || !entities.contains(entity) || entity.getPosition() == null) {
            return false;
        }
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
        entities.remove(entity);
        notifyObservers();
        return true;
    }


    /**
     * מזיז יצור מהמיקום הישן שלו למיקום חדש במפה
     * הפעולה מנקה את המשבצת הישנה ומעדכנת את החדשה רק אם היא פנויה תוך שמירה על עקביות הנתונים ועדכון המצבים והאפקטים העוטפים את הישות
     * @param entity היצור שרוצים להזיז
     * @param newPos המיקום החדש שאליו הוא הולך
     * @return true אם התנועה הצליחה והמקום החדש היה פנוי
     */
    public synchronized boolean moveEntity(AbstractEntity entity, Position newPos) {
        if (!isPositionFree(newPos) || entity == null || entity.getPosition() == null)
            return false;
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
        entity.setPosition(newPos);
        map[newPos.getRow()][newPos.getCol()] = entity;
        notifyObservers();
        return true;
    }


    /**
     * מחפש את כל השכנים שנמצאים קרוב למיקום מסוים במפה
     * הפונקציה מוצאת את כל הישויות שנמצאות במרחק של עד שני צעדים לפי מרחק מנהטן
     * @param pos המיקום שסביבו מחפשים
     * @return רשימה של כל היצורים שנמצאים בטווח הקרוב
     */
    public synchronized List<AbstractEntity> getNearbyEntities(Position pos) {
        if(pos == null) return new ArrayList<>();
        List<AbstractEntity> entitiesNew = new ArrayList<>();
        for (AbstractEntity e : entities) {
            if (e == null || e.getPosition() == null) continue;
            int distance = e.getPosition().distanceTo(pos);
            if (distance > 0 && distance <= 2)
                entitiesNew.add(e);
        }
        return entitiesNew;
    }


    /**
     * מוסיפה מאזין חדש לרשימת המאזינים של העולם
     * המאזין יקבל התראה אוטומטית בכל פעם שיש שינוי במפה כמו תנועה אכילה או הוספת ישות
     * @param observer הרכיב הגרפי שרוצה להאזין לשינויים בעולם
     */
    public void addObserver(WorldObserver observer) {
        if(observer == null) return;
        synchronized (this) {
            if (!observers.contains(observer))
                observers.add(observer);
        }
    }

    /**
     * מעדכנת את כל המאזינים הרשומים שמשהו במודל העולם השתנה
     * המתודה עוברת על רשימת הצופים ומפעילה את מתודת עדכון הממשק כדי לסנכרן בין המודל המקבילי לגרפיקה
     */
    public void notifyObservers() {
        List <WorldObserver> observersCopy;
        synchronized (this){
            observersCopy = new ArrayList<>(observers);
        }
        for (WorldObserver observer : observersCopy) {
            observer.onWorldChanged();
        }
    }

    /**
     * מקדמת את מונה פעימות הזמן הכללי של הסימולציה בצעד אחד קדימה
     */
    public synchronized void nextTick() {
        this.ticks++;
    }


    /**
     * מחזירה את מספר פעימות הזמן שעברו מתחילת הריצה של הסימולציה
     * @return מספר הטיקים הנוכחי המייצג את גיל העולם
     */
    public synchronized int getTicks(){
        return this.ticks;
    }


    /**
     * מאתחלת את העולם ומנקה את כל הישויות שנמצאות בו בצורה בטוחה
     * המתודה מרוקנת את רשימת היצורים מאפסת את מונה הטיקים ומוחקת את כל האובייקטים מהמטריצה הדו ממדית תוך שליחת התראת רענון לממשק המשתמש
     */
    public synchronized void reset() {
        entities.clear();
        ticks = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                map[i][j] = null;
        notifyObservers();
    }


    /**
     * בודקת האם מיקום מסוים במפה מהווה את אחת מארבע הפינות הקיצוניות של הלוח
     * מתודה זו משמשת את מכונת המצבים כדי לקבוע האם ישות חיה רשאית לעבור למצב שינה במערכת
     * @param p המיקום על גבי המפה המיועד לבדיקת הפינות
     * @return true אם המיקום הוא אחת מארבע הפינות או false בכל מקרה אחר
     */
    public boolean isAtCorner(Position p) {
        if (p == null) return false;
        int maxRow = getRows() - 1;
        int maxCol = getCols() - 1;

        return (p.getRow() == 0 && p.getCol() == 0) ||
                (p.getRow() == 0 && p.getCol() == maxCol) ||
                (p.getRow() == maxRow && p.getCol() == 0) ||
                (p.getRow() == maxRow && p.getCol() == maxCol);
    }

    /**
     * בודק אם עולם אחר הוא בדיוק כמו העולם הזה
     * ההשוואה בודקת את הגודל ואת כל היצורים שנמצאים בתוך המפה
     * @param o האובייקט שרוצים להשוות אליו
     * @return true אם שני העולמות זהים לחלוטין
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof Environment other) {
            return this.rows == other.rows
                    && this.cols == other.cols
                    && this.entities.equals(other.entities)
                    && java.util.Arrays.deepEquals(this.map, other.map);
        }
        return false;
    }


    /**
     * בונה תמונה של כל המפה בעזרת תווים כדי שנוכל לראות את העולם
     * @return מחרוזת טקסט שמציגה את המפה עם כל הסימולים של היצורים
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            sb.append("|");
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == null) {
                    sb.append(" ");
                } else {
                    sb.append(map[i][j].getSymbol());
                }
                sb.append("|");
            }
            sb.append("\n");

        }
        return sb.toString();
    }
}
