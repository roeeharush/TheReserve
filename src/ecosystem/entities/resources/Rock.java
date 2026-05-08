package ecosystem.entities.resources;
import ecosystem.core.Position;

public class Rock extends Resource{

    private boolean blocksMovement = true;

    public Rock(Position position){
        super(position,'X',true);
    }

    public boolean getBlocksMovement(){
        return blocksMovement;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o instanceof Rock other){
            return super.equals(o);
        }
        return false;
    }
}
