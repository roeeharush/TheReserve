package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;

import java.util.Random;

public class RandomMovement implements MovementStrategy{
    private Random rd = new Random();

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
