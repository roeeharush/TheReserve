package ecosystem.states;
import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;


/**
 * מחלקה המייצגת את מצב ההתנהגות הקבוע של צמחים במערכת
 * בניגוד לחיות, צמחים אינם משתתפים במכונת המצבים של רעב מנוחה ושינה מכיוון שכל מנגנון האנרגיה שלהם מנוהל באופן עצמאי ומלא בתוך המתודה act של מחלקת הצמח עצמה
 * מצב זה קיים כדי לנתק את הצמחים ממכונת המצבים שתוכננה במקור עבור חיות בלבד ולמנוע מהם לאבד אנרגיה בטעות דרך מנגנון הרעב
 * Pattern: State Concrete
 */
public class PlantGrowthState implements EntityState {


    /**
     * לא מבצעת שום פעולה מכיוון שהאנרגיה של הצמח מנוהלת במלואה בתוך Plant.act ואינה תלויה במכונת המצבים
     * @param e הישות החיה הנמצאת במצב זה
     * @param env סביבת העולם
     */
    @Override
    public void doAction(LivingEntity e, Environment env){

    }


    /**
     * צמחים אינם נעים על גבי המפה ולכן אין להם שום יכולת תנועה במצב זה
     * @return false באופן קבוע
     */
    @Override
    public boolean canMove(){
        return false;
    }
}
