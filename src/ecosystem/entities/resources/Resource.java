package ecosystem.entities.resources;

import ecosystem.core.Position;
import ecosystem.entities.StaticEntity;

public abstract class Resource extends StaticEntity {

    public Resource(Position position, char symbol, boolean alive){
        super(position, symbol, alive);
    }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o instanceof Resource other)
            return super.equals(o);
        return false;
    }

    @Override
    public String toString(){
        return "Entity Type: " + getSymbol() + " Position: " + getPosition().toString() +" Amount of Energy: " + "0.0 " + "is exist: " + isAlive();
    }
}
