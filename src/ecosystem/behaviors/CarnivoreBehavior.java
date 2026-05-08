package ecosystem.behaviors;

import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;

import java.util.List;

public class CarnivoreBehavior implements FeedingBehavior{

    @Override
    public boolean eat(Animal eater, List<AbstractEntity> nearby) {
        return false;
    }
}
