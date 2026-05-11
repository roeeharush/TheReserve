package ecosystem.entities.plants;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.animals.Rabbit;

import java.util.Random;

public class OakTree extends Plant {
    private static final double INITINAL_ENERGY = 80.0;
    private static final double MAX_ENERGY = 120.0;
    private static final double GROW_RATE = 2.0;
    private static final double REPRODUCTION_CHANCE = 0.05;



    public OakTree(Position position){
        super(position,'T',true,INITINAL_ENERGY,MAX_ENERGY,GROW_RATE,REPRODUCTION_CHANCE);
    }

    @Override
    public boolean reproduce(Environment env){
        Random chance = new Random();
        double result = chance.nextDouble();


        Position position = this.getPosition();
        if(result <= REPRODUCTION_CHANCE){
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if(env.isPositionFree(op)){
                    OakTree newOakTree = new OakTree(op);
                    env.addEntity(newOakTree);
                    return true;
                }
            }
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


    @Override
    public String toString() {
        return super.toString();
    }


}
