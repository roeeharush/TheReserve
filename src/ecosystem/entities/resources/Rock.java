package ecosystem.entities.resources;
import ecosystem.core.Position;

public class Rock extends Resource {

    private final boolean blocksMovement = true;

    public Rock(Position position) {
        super(position, 'X', true);
    }

    public boolean getBlocksMovement() {
        return blocksMovement;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }
}
