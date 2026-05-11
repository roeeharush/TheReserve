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
        setGrowRate(growthRate);
        setReproductionChance(reproductionChance);
    }

    public boolean setGrowRate(double growthRate) {
        if (growthRate >= 0) {
            this.growthRate = growthRate;
            return true;
        }
        this.growthRate = 1.0;
        return false;
    }

    public boolean setReproductionChance(double reproductionChance) {
        if (reproductionChance >= 0 && reproductionChance <= 1.0) {
            this.reproductionChance = reproductionChance;
            return true;
        }
        this.reproductionChance = 0.1;
        return false;
    }

    @Override
    public boolean act(Environment env) {
        boolean action = super.act(env);
        if (!isAlive())
            return false;
        double updatedEnergy = this.getEnergy() + 2 + this.growthRate;
        if (updatedEnergy > this.getMaxEnergy())
            updatedEnergy = this.getMaxEnergy();
        this.setEnergy(updatedEnergy);

        boolean repr = reproduce(env);
        return action || repr;
    }

    @Override
    public abstract boolean reproduce (Environment env);


    @Override
    public double getNutritionValue () {
        return this.getEnergy();
        }

        @Override
        public boolean onConsumed(){
            return this.setAlive(false);
        }


    @Override
    public String toString() {
       return super.toString();
    }


    @Override
    public boolean equals (Object o){
        if (this == o)
            return true;
        if (o instanceof Plant other) {
            if (!super.equals(o))
                return false;return Double.compare(this.growthRate, other.growthRate) == 0
                        && Double.compare(this.reproductionChance, other.reproductionChance) == 0;
            }
        return false;
    }


}

