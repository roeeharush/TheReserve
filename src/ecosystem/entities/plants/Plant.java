package ecosystem.entities.plants;
import ecosystem.core.Position;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Consumable;
import ecosystem.interfaces.EdibleByHerbivore;
import ecosystem.interfaces.Reproducible;

public abstract class Plant extends LivingEntity implements Consumable, Reproducible, EdibleByHerbivore {
    private double growthRate;
    private int reproductionChance;

    public Plant(Position position, char symbol, boolean alive, int energy, int maxEnergy, double growthRate, int reproductionChance) {
        super(position, symbol, alive, energy, maxEnergy);
        if(!setGrowRate(growthRate))
            this.growthRate = 1;
        if(!setReproductionChance(reproductionChance))
            this.reproductionChance = 10;

    }

    public boolean setGrowRate(double growthRate){
        if(growthRate >= 0) {
            this.growthRate = growthRate;
            return true;
        }
        return false;
    }

    public boolean setReproductionChance(int reproductionChance){
        if(reproductionChance <= 100 && reproductionChance >= 0)
        {
            this.reproductionChance = reproductionChance;
            return true;
        }
        return false;
    }
}
