package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;

public class Deer extends Animal {
    public Deer( Position position) {
        MovementStrategy MS = new EscapeMovement();
        FeedingBehavior FB = new HerbivoreBehavior();
        super(position, 'D', true, 70, 70,FB ,MS);

    }
}
