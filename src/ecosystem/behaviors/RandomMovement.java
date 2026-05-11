package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;

import java.util.Random;


/**
 * מחלקה שמייצגת אסטרטגיה של תנועה אקראית לגמרי בעולם שלנו
 * הישות פשוט בוחרת כיוון אחד מארבעה ומנסה ללכת לשם בלי לחשוב יותר מדי
 */
public class RandomMovement implements MovementStrategy{
    private Random rd = new Random();


    /**
     * מבצע את התנועה של הישות בצורה רנדומלית לפי הלוגיקה בקוד
     * הפונקציה מגדירה ארבעה כיוונים אפשריים סביב הישות בוחרת אחד מהם באקראי ומנסה להזיז את הישות לשם רק אם המקום פנוי במפה
     * @param entity הישות שרוצה לזוז עכשיו במפה
     * @param env העולם שבו בודקים אם המשבצת פנויה ומבצעים את התזוזה בפועל
     * @return true אם התנועה הצליחה והישות עברה למקום החדש false אם המקום שבחרנו היה תפוס
     */
    @Override
    public boolean move(AbstractEntity entity, Environment env) {
        Position position = entity.getPosition();

        Position option1 = new Position(position.getRow() - 1, position.getCol());
        Position option2 = new Position(position.getRow() + 1, position.getCol());
        Position option3 = new Position(position.getRow(), position.getCol() - 1);
        Position option4 = new Position(position.getRow(), position.getCol() + 1);


        Position[] options = {option1, option2, option3, option4};
        int index = rd.nextInt(options.length);
        Position choice = options[index];

        if (env.isPositionFree(choice)) {
            System.out.println( "im moving!");
            env.moveEntity(entity, choice);
            return true;
        }
         return false;
    }

}
