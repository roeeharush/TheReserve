package ecosystem.states;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;

public class SleepingState implements EntityState {

    public static final int DURATION_SLEEP = 5;
    private int remainingTicks = DURATION_SLEEP;

    @Override
    public void doAction(LivingEntity e, Environment env) {
        e.setEnergy(Math.min(e.getMaxEnergy(), e.getEnergy() + 10));
        remainingTicks--;

        if (remainingTicks <= 0) {
            e.setState(new IdleState());
        }
    }

    public boolean canMove() { return false; }
}