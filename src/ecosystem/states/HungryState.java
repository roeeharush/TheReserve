package ecosystem.states;


import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

public class HungryState implements EntityState{
    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.max(0, e.getEnergy() - 5));

        if (env.isAtCorner(e.getPosition())) {
            e.setState(new SleepingState());
        } else if (e.getEnergy() > e.getMaxEnergy() * 0.8) {
            e.setState(new IdleState());
        }
    }

    public boolean canMove() { return true; }
}
