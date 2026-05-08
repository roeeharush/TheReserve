package ecosystem.behaviors;

import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import java.util.List;

public interface FeedingBehavior {
    boolean eat(Animal eater, List<AbstractEntity> nearby);
}


