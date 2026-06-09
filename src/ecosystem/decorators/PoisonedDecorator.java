package ecosystem.decorators;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Actable;

public class PoisonedDecorator extends EntityDecorator {

    public PoisonedDecorator(Actable decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public boolean act(Environment env) {
        boolean isRemoved = super.act(env);
        if (isRemoved) return true;
        boolean result = decoratedEntity.act(env);
        if (decoratedEntity instanceof LivingEntity living) {
            double currentEnergy = living.getEnergy();
            living.setEnergy(Math.max(0.0, currentEnergy - 5.0));
            if (living.getEnergy() <= 0) {
                living.setAlive(false);
                this.setAlive(false);
                env.removeEntity(this);
            }
        }
        return result;
    }
}