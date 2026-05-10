package ecosystem.entities;

import ecosystem.core.Position;

// לא ממשים act
public abstract class StaticEntity extends AbstractEntity{

    public StaticEntity(Position position, char symbol, boolean alive) {
        super(position, symbol, alive);
    }

    @Override
    public String toString() {
        return super.toString() + " Energy: do not exist ";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if (!super.equals(o))
            return false;
        return o instanceof StaticEntity;
    }
}
