package ecosystem.behaviors;

import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;

public interface MovementStrategy {
    boolean move(AbstractEntity entity, Environment env);
}
