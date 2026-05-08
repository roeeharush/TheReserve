package ecosystem.behaviors;

import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByCarnivore;

import java.util.List;

public class CarnivoreBehavior implements FeedingBehavior{

    @Override
    public boolean eat(Animal eater, List<AbstractEntity> nearby) {
        for (AbstractEntity e : nearby){
            if (e instanceof EdibleByCarnivore)
                return eater.eat((Consumable)e);
        }
        return false;
    }
}
