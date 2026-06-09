package ecosystem.states;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;



public class IdleState implements EntityState {
    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.max(0, e.getEnergy() - 1));

        if (env.isAtCorner(e.getPosition())) {
            e.setState(new SleepingState());
        } else if (e.getEnergy() < e.getMaxEnergy() * 0.3) {
            e.setState(new HungryState());
        }
    }

    public boolean canMove() { return true; }
}