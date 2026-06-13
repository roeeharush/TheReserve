package ecosystem.decorators;

import ecosystem.commands.WorldCommand;
import ecosystem.core.Environment;
import ecosystem.entities.AbstractEntity;
import ecosystem.entities.LivingEntity;
import ecosystem.interfaces.Actable;

import java.util.List;

public abstract class EntityDecorator extends AbstractEntity implements Actable {
    protected final Actable decoratedEntity;
    protected int duration = 10;

    public EntityDecorator(Actable decoratedEntity) {
        super(
                ((AbstractEntity) decoratedEntity).getPosition(),
                ((AbstractEntity) decoratedEntity).getSymbol(),
                ((AbstractEntity) decoratedEntity).isAlive()
        );
        this.decoratedEntity = decoratedEntity;
    }

    @Override
    public boolean act(Environment env){
        if (!((AbstractEntity) decoratedEntity).isAlive()) {
            this.setAlive(false);
            return false;
        }
        if (!isAlive())
            return false;

        duration--;
        if (duration <= 0) {
            removeDecoratorAndRestoreOriginal(env);
            return true;
        }
        return false;
    }

    protected void removeDecoratorAndRestoreOriginal(Environment env){
        AbstractEntity original = (AbstractEntity) this.decoratedEntity;
        original.setPosition(this.getPosition());
        env.removeEntity(this);
        env.addEntity(original);
    }

    @Override
    public List<WorldCommand> collectCommands(Environment env) {
        return decoratedEntity.collectCommands(env);
    }

    @Override
    public String getImageName() {
        return ((AbstractEntity) decoratedEntity).getImageName();
    }

    public Actable getDecoratedEntity() {
        return decoratedEntity;
    }

    }




