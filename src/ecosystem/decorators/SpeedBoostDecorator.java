package ecosystem.decorators;


import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.interfaces.Actable;

public class SpeedBoostDecorator extends EntityDecorator {

    public SpeedBoostDecorator(Actable decoratedEntity){
        super(decoratedEntity);
    }

    @Override
    public boolean act(Environment env){
        super.act(env);
        boolean firstAct = decoratedEntity.act(env);
        boolean secondAct = false;
        if(((AbstractEntity) decoratedEntity).isAlive()){
            secondAct = decoratedEntity.act(env);
        }
        return firstAct || secondAct;
    }
}
