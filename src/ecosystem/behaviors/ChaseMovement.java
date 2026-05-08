package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

public class ChaseMovement implements MovementStrategy{

    @Override
    public boolean move(AbstractEntity entity, Environment env) {
        return false;
    }
}
