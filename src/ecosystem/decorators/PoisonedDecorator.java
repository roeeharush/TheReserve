package ecosystem.decorators;

import ecosystem.core.Environment;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Actable;
public class PoisonedDecorator extends EntityDecorator {

    public PoisonedDecorator(LivingEntity decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public boolean act(Environment env) {
        boolean isRemoved = super.act(env);
        if (isRemoved) return true;

        decoratedEntity.act(env);
        ((LivingEntity) decoratedEntity).setEnergy(
                Math.max(0, ((LivingEntity) decoratedEntity).getEnergy() - 5)
        );
        return true;
    }
}