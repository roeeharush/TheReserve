package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;


public class Lion extends Animal {
    private static final double MAX_ENERGY = 500;

    public Lion(Position position) {
        super(position, 'L', true, 100, MAX_ENERGY, new CarnivoreBehavior(),new ChaseMovement());
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }


}

