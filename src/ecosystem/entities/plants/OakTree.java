package ecosystem.entities.plants;

import ecosystem.core.Environment;
import ecosystem.core.Position;

public class OakTree extends Plant {

    public OakTree(Position position){
        super(position,'T',true,80.0,120.0,2.0,0.05);
    }

    @Override
    public boolean reproduce(Environment env){
        double chance = Math.random();
        if(chance <= 0.05 && chance > 0.0){

        }
        return false;
    }

}
