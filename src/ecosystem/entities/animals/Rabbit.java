package ecosystem.entities.animals;

import ecosystem.behaviors.*;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.Reproducible;

import java.util.Random;

public class Rabbit extends Animal implements Reproducible {

    public Rabbit( Position position) {
        MovementStrategy MS = new RandomMovement();
        FeedingBehavior FB = new HerbivoreBehavior();
        super(position, 'R', true, 50, 50,FB ,MS);

    }
    @Override
    public boolean act(Environment env){
        super.act(env);

    }



    @Override
    public boolean reproduce(Environment env) {
        Random rand = new Random();
        double chance = rand.nextDouble();

        Position position = this.getPosition();
        if(this.getEnergy()> 30 && chance <= 0.3){

            Position option1 = new Position(position.getRow() - 1, position.getCol());
            Position option2 = new Position(position.getRow() + 1, position.getCol());
            Position option3 = new Position(position.getRow(), position.getCol() - 1);
            Position option4 = new Position(position.getRow(), position.getCol() + 1);
            Position[] options = {option1, option2, option3, option4};

            for( Position op : options){
                if (env.isPositionFree(op)){
                    Rabbit newRabbit = new Rabbit(op);
                    env.addEntity(newRabbit);
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public String toString(){
        return this.toString();
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }
}


