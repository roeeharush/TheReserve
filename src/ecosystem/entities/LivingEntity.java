package ecosystem.entities;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Actable;

public abstract class LivingEntity extends AbstractEntity implements Actable {

    private double maxEnergy;
    private double energy;
    private int age = 0;

    public LivingEntity(Position position, char symbol, boolean alive,double energy, double maxEnergy) {
        super(position, symbol, alive);
        if(!setMaxEnergy(maxEnergy)){
            this.maxEnergy = 1000.0;
        }
        if(!setEnergy(energy)){
            this.energy = this.maxEnergy;
        }
    }

    public boolean setMaxEnergy(double maxEnergy){
        if(maxEnergy >0 && maxEnergy >= this.energy){
            this.maxEnergy = maxEnergy;
            return true;
        }
        return false;
    }

    public boolean setEnergy(double energy){
        if(energy >= 0 && energy <= maxEnergy){
            this.energy = energy;
            return true;
        }
        return false;
    }

    @Override
    public boolean act(Environment env){
        if(!this.isAlive())
            return false;
        this.age++;
        this.energy -= 2.0;
        if(this.getEnergy() <= 0.0)
            this.setAlive(false);
        return true;
    }

    public double getEnergy() {
        return this.energy;
    }

    public double getMaxEnergy() {
        return this.maxEnergy;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString(){
        return super.toString() + " Energy: " + this.energy;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!super.equals(o))
            return false;
        if(o instanceof LivingEntity other){
        return this.age == other.age
                && Double.compare(this.energy, other.energy)==0
                && Double.compare(this.maxEnergy,other.maxEnergy)==0;
        }
        return false;
    }

}

