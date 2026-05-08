package ecosystem.behaviors;

import ecosystem.entities.AbstractEntity;
import ecosystem.entities.animals.Animal;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;

import java.util.List;

public class HerbivoreBehavior implements FeedingBehavior{
    @Override
    public boolean eat(Animal eater, List<AbstractEntity> nearby) {
        for (AbstractEntity e : nearby){
            if (e instanceof EdibleByHerbivore)
                return eater.eat((Consumable)e);
        }
        return false;
    }

}
