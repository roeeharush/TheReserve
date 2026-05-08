package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;


public class Lion extends Animal {
    public Lion(Position position) {
        MovementStrategy MS = new ChaseMovement();
        FeedingBehavior FB = new CarnivoreBehavior();

        super(position, 'L', true, 100, 100, FB,MS);
    }


}

