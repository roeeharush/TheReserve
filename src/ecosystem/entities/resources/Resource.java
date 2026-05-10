package ecosystem.entities.resources;

import ecosystem.core.Position;
import ecosystem.entities.StaticEntity;

public abstract class Resource extends StaticEntity {

    public Resource(Position position, char symbol, boolean alive){
        super(position, symbol, alive);
    }


    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
