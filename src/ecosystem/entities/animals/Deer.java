package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Position;

public class Deer extends Animal {
    private static final double MAX_ENERGY = 300;


    public Deer( Position position) {
        super(position, 'D', true, 70, MAX_ENERGY,new HerbivoreBehavior() , new EscapeMovement());

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
