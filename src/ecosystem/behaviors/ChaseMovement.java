package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.interfaces.EdibleByCarnivore;

import java.util.List;

public class ChaseMovement implements MovementStrategy{

    @Override
    public boolean move(AbstractEntity entity, Environment env) {
        List<AbstractEntity>  nearbyEntities = env.getNearbyEntities(entity.getPosition());
        for (AbstractEntity e : nearbyEntities){
            if (e instanceof EdibleByCarnivore){
                Position myPos = entity.getPosition();
                Position targetPos = e.getPosition();

                int myRow = myPos.getRow();
                int myCol = myPos.getCol();

                if (targetPos.getRow() > myPos.getRow())
                    myRow++;
                else if (targetPos.getRow() < myPos.getRow())
                    myRow--;
                if (targetPos.getCol() > myPos.getCol())
                    myCol++;
                else if (targetPos.getCol() < myPos.getCol())
                    myCol--;

                Position newPos = new Position(myRow, myCol);
                if (env.isPositionFree(newPos)) {
                    entity.setPosition(newPos);
                    return true;
                }
                return false;
            }
        }
       return false;
    }
}
