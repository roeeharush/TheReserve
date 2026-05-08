package ecosystem.entities.animals;

import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.*;

import java.util.List;


public abstract class Animal extends LivingEntity implements Movable, Eater, Sensory, EdibleByCarnivore , Consumable {
    private FeedingBehavior feedingBehavior;
    private MovementStrategy movementStrategy;
    private final int visionRange = 2;

    public Animal(Position position, char symbol, boolean alive,
                  int energy, int maxEnergy, FeedingBehavior feedingBehavior , MovementStrategy movementStrategy){
        super(position ,symbol, alive,energy,maxEnergy );
        this.feedingBehavior =feedingBehavior;
        this.movementStrategy= movementStrategy;

    }

    public double getNutritionValue(){
        return getEnergy()*0.8;
    }

    public boolean onConsumed(){
         return this.setAlive(false);
    }

    public List<AbstractEntity> sense(Environment env) {
       return env.getNearbyEntities(this.getPosition());
    }

    public boolean move(Environment env){

    }











}
