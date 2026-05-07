package ecosystem.entities;
import ecosystem.core.Environment;
import ecosystem.core.Position;
import ecosystem.interfaces.Actable;

public abstract class LivingEntity extends AbstractEntity implements Actable {

    private int maxEnergy;
    private int energy;
    private int age = 0;




    public LivingEntity(Position position, char symbol, boolean alive,int energy, int maxEnergy) {
        super(position, symbol, alive);
        this.age = 0;
        if(!setMaxEnergy(maxEnergy)){
            this.maxEnergy = 100;
        }

        if(!setEnergy(energy)){
            this.energy = this.maxEnergy;
        }
    }

    public boolean setMaxEnergy(int maxEnergy){
        if(maxEnergy > 0){
            this.maxEnergy = maxEnergy;
            return true;
        }
        return false;
    }

    public boolean setEnergy(int energy){
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
        this.energy -= 2;
        if(this.getEnergy() <= 0)
            this.setAlive(false);
        return true;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString(){
        return super.toString() + " Energy: " + this.energy;
    }

}
