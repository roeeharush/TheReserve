package ecosystem.decorators;


import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Actable;

public class SpeedBoostDecorator extends EntityDecorator {

    public SpeedBoostDecorator(LivingEntity decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public boolean act(Environment env) {
        boolean isRemoved = super.act(env);
        if (isRemoved) return true;

        boolean firstAct = decoratedEntity.act(env);
        boolean secondAct = false;
        if (((AbstractEntity) decoratedEntity).isAlive()) {
            secondAct = decoratedEntity.act(env);
        }
        return firstAct || secondAct;
    }
}
