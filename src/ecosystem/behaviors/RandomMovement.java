package ecosystem.behaviors;

import ecosystem.commands.MoveCommand;
import ecosystem.commands.WorldCommand;
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
     * בונה פקודת תנועה אקראית עבור הישות על בסיס מצב העולם הנוכחי
     * המתודה מגדירה את ארבעת כיווני התנועה האפשריים שלוש שורות ועמודות סביב הישות בוחרת כיוון אחד באקראי ואם המיקום פנוי היא מייצרת ומחזירה פקודת תנועה חדשה המתאימה לתור הפעולות
     * @param entity הישות שרוצה לבצע את התנועה האקראית
     * @param env סביבת העולם שבה נבדקת זמינות המשבצת הנבחרת
     * @return פקודת תנועה מוכנה לביצוע בתור הפעולות או null אם המיקום שנבחר אינו פנוי
     */
    @Override
    public WorldCommand buildMoveCommand(AbstractEntity entity, Environment env) {
        Position position = entity.getPosition();

        Position option1 = new Position(position.getRow() - 1, position.getCol());
        Position option2 = new Position(position.getRow() + 1, position.getCol());
        Position option3 = new Position(position.getRow(), position.getCol() - 1);
        Position option4 = new Position(position.getRow(), position.getCol() + 1);


        Position[] options = {option1, option2, option3, option4};
        int index = rd.nextInt(options.length);
        Position choice = options[index];

        if (env.isPositionFree(choice)) {
            return new MoveCommand(entity, choice);
        }
        return null;



    }

}
