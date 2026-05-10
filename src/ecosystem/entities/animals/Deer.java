package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;

public class Deer extends Animal {
    public Deer( Position position) {
        MovementStrategy MS = new EscapeMovement();
        FeedingBehavior FB = new HerbivoreBehavior();
        super(position, 'D', true, 70, 70,FB ,MS);

    }

    @Override
    public String toString(){
        return this.toString();
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }


}
