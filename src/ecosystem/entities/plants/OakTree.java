package ecosystem.entities.plants;

import ecosystem.core.Environment;
import ecosystem.core.Position;

public class OakTree extends Plant {
    private static final double initialEnergy = 80.0;
    private static final double maxEnergy = 120.0;
    private static final double growRate = 2.0;
    private static final double reproductionChance = 0.05;



    public OakTree(Position position){
        super(position,'T',true,initialEnergy,maxEnergy,growRate,reproductionChance);
    }

    @Override
    public boolean reproduce(Environment env){
        double chance = Math.random();
        if(chance <= 0.05 && chance > 0.0){

        }
        return false;
    }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof OakTree))
            return false;
        return super.equals(o);
    }

}
