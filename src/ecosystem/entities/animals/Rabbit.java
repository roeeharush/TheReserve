package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.Reproducible;

public class Rabbit extends Animal implements Reproducible {

    public Rabbit( Position position) {
        MovementStrategy MS = new RandomMovement();
        FeedingBehavior FB = new HerbivoreBehavior();
        super(position, 'R', true, 50, 50,FB ,MS);

    }
}


