package ecosystem.entities.plants;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.animals.Rabbit;

import java.util.Random;

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
        Random chance = new Random();
        double result = chance.nextDouble();


        Position position = this.getPosition();
        if(result <= 0.05){
            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if(env.isPositionFree(op)){
                    OakTree NewoakTree = new OakTree(op);
                    env.addEntity(NewoakTree);
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
