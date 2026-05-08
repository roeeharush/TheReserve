package ecosystem.entities.plants;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;
import ecosystem.interfaces.Reproducible;

public abstract class Plant extends LivingEntity implements Consumable, Reproducible, EdibleByHerbivore {
    private double growthRate;
    private double reproductionChance;

    public Plant(Position position, char symbol, boolean alive, double energy, double maxEnergy, double growthRate, double reproductionChance) {
        super(position, symbol, alive, energy, maxEnergy);
        if(!setGrowRate(growthRate))
            this.growthRate = 1.0;
        if(!setReproductionChance(reproductionChance))
            this.reproductionChance = 0.1;
    }

    public boolean setGrowRate(double growthRate){
        if(growthRate >= 0) {
            this.growthRate = growthRate;
            return true;
        }
        return false;
    }

    public boolean setReproductionChance(double reproductionChance){
        if(reproductionChance <= 1.0 && reproductionChance >= 0)
        {
            this.reproductionChance = reproductionChance;
            return true;
        }
        return false;
    }

    @Override
    public boolean act(Environment env){
        if(!super.act(env))
            return false;
        setEnergy(getEnergy() + growthRate + 2.0);
        reproduce(env);
        return true;
    }
    @Override
    public abstract boolean reproduce(Environment env);


    @Override
    public double getNutritionValue(){
        return this.getEnergy();

    }

    @Override
    public boolean onConsumed(){
        return this.setAlive(false);
    }

    /*
    @Override
    public String toString(){

    }
*/
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o instanceof Plant other){
            if(!super.equals(o))
                return false;
            return Double.compare(this.growthRate,other.growthRate) ==0
                    && Double.compare(this.reproductionChance,other.reproductionChance)==0;
        }
        return false;
    }

}
