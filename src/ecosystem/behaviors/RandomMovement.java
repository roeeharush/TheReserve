package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

public class RandomMovement implements MovementStrategy{
    @Override
    public boolean move(AbstractEntity entity, Environment env) {
        return false;
    }
}
