package ecosystem.entities.plants;

import ecosystem.core.Position;

public class Flower extends Plant {
    private static final double initialEnergy = 10.0;
    private static final double maxEnergy = 70.0;
    private static final double growRate = 5.0;
    private static final double reproductionChance = 0.2;

    public Flower(Position position){
        super(position,'F',true,initialEnergy,maxEnergy,growRate,reproductionChance);
    }


    @Override
    public boolean onConsumed(){
        this.setAlive(false);
        return true;
    }

    /*
    @Override
    public boolean reproduce(){

    }

     */


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Flower))
            return false;
        return super.equals(o);
    }
}
