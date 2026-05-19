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

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public AbstractEntity getEntityAt(int row, int col) {
        return map[row][col];
    }


    /**
     * בונה עולם חדש עם מספר שורות ועמודות שביקשנו
     * אם הגודל שנתנו קטן מדי המערכת קובעת גודל מינימלי של עשר על עשר באופן אוטומטי
     * @param rows מספר השורות בעולם
     * @param cols מספר העמודות בעולם
     */
    public Environment(int rows, int cols) {
        if (rows < 10)
            rows = 10;
        if (cols < 10)
            cols = 10;

        this.rows = rows;
        this.cols = cols;
        this.map = new AbstractEntity[rows][cols];
        this.entities = new ArrayList<>();
    }


    /**
     * מחזירה העתק של רשימת כל היצורים והחפצים שנמצאים כרגע בתוך העולם
     * @return רשימה של כל הישויות הקיימות
     */
    public List<AbstractEntity> getEntities() {
        return new ArrayList<>(entities);
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
     * בודק אם משבצת מסוימת במפה פנויה
     * המתודה מוודא שהמיקום נמצא בתוך גבולות המפה ושאין שם כבר מישהו אחר
     * @param pos המיקום שרוצים לבדוק
     * @return true אם המקום פנוי ואפשר להיכנס אליו
     */
    public boolean isPositionFree(Position pos) {
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
    public boolean addEntity(AbstractEntity entity) {
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
    public boolean removeEntity(AbstractEntity entity) {
        if (entity == null || !entities.contains(entity)) {
            return false;
        }
        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
        entities.remove(entity);
        notifyObservers();
        return true;


    }


    /**
     * מחפש את כל השכנים שנמצאים קרוב למיקום מסוים במפה
     * הפונקציה מוצאת את כל הישויות שנמצאות במרחק של עד שני צעדים לפי מרחק מנהטן
     * @param pos המיקום שסביבו מחפשים
     * @return רשימה של כל היצורים שנמצאים בטווח הקרוב
     */
    public List<AbstractEntity> getNearbyEntities(Position pos) {

        List<AbstractEntity> entitiesNew = new ArrayList<>();
        for (AbstractEntity e : entities) {
            int distance = e.getPosition().distanceTo(pos);
            if (distance > 0 && distance <= 2)
                entitiesNew.add(e);
        }
        return entitiesNew;
    }


    /**
     * בונה תמונה של כל המפה בעזרת תווים כדי שנוכל לראות את העולם
     * @return מחרוזת טקסט שמציגה את המפה עם כל הסימולים של היצורים
     */
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


    /**
     * מזיז יצור מהמיקום הישן שלו למיקום חדש במפה
     * הפעולה מנקה את המשבצת הישנה ומעדכנת את החדשה רק אם היא פנויה
     * @param entity היצור שרוצים להזיז
     * @param newPos המיקום החדש שאליו הוא הולך
     * @return true אם התנועה הצליחה והמקום החדש היה פנוי
     */
    public boolean moveEntity(AbstractEntity entity, Position newPos) {
        if (!isPositionFree(newPos))
            return false;

        map[entity.getPosition().getRow()][entity.getPosition().getCol()] = null;
        entity.setPosition(newPos);
        map[newPos.getRow()][newPos.getCol()] = entity;
        notifyObservers();

        return true;
    }

    public void addObserver(WorldObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (WorldObserver observer : observers) {
            observer.onWorldChanged();
        }
    }
}
