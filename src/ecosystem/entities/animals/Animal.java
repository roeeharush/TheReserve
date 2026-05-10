package ecosystem.entities.animals;

import ecosystem.behaviors.FeedingBehavior;
import ecosystem.behaviors.MovementStrategy;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.*;
import java.util.List;


public abstract class Animal extends LivingEntity implements Movable, Eater, Sensory, EdibleByCarnivore , Consumable {
    private  FeedingBehavior feedingBehavior;
    private  MovementStrategy movementStrategy;
    private final int visionRange = 2;

    public Animal(Position position, char symbol, boolean alive,
                  int energy, int maxEnergy, FeedingBehavior feedingBehavior , MovementStrategy movementStrategy){
        super(position ,symbol, alive,energy,maxEnergy );
        this.feedingBehavior = feedingBehavior;
        this.movementStrategy = movementStrategy;

    }

    public boolean act(Environment env){
        super.act(env);
        if (!isAlive())
            return false;

        List<AbstractEntity> nearbyEntities = this.sense(env);
        boolean moved = move(env);
        boolean ate = this.feedingBehavior.eat(this, nearbyEntities);
        return moved || ate;


    }

    public double getNutritionValue(){
        return this.getEnergy()*0.8;
    }

    public boolean onConsumed(){
         return this.setAlive(false);
    }

    public List<AbstractEntity> sense(Environment env) {
       return env.getNearbyEntities(this.getPosition());
    }

    public boolean move(Environment env) {
        return this.movementStrategy.move(this, env);
    }

    public boolean eat(Consumable target){
        if (target != null) {
            double newEnergy = Math.min(getEnergy() + target.getNutritionValue(), getMaxEnergy());
            this.setEnergy( newEnergy);
            target.onConsumed();
            return true;
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











